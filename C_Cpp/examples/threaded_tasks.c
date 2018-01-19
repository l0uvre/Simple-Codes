// Compile with
//    gcc -D_REENTRANT -o threaded_tasks threaded_tasks.c -lpthread
//
// You can experiment with a different number of threads and
// see what is optimal ...
//
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <pthread.h>

#include <errno.h>

#define STACK_SIZE  20

// These variables are seen by all the threads (the main that
// creates the other threads, which will be called "Producer",
// and the threads that process the job, called "Consumers".
// The Producer adds jobs to the stack (which in good logic
// should rather be a queue, but a stack is easier to process
// and it doesn't make much difference here). In real life it 
// would be a structure describing what has to be performed.
// Here it's just an integere identifier. Consumers read from
// the stack, looking for something to process, and when they
// find something to process, they take it off the stack.
// As everybody can modify G_task_cnt (the producer increases it,
// consumers decrease it) it has to be protected by a mutex. The
// condition "G_task_cnt > 0" (in other words "there is something
// to do") is associated with a condition variable.
static int             G_tasks[STACK_SIZE];
static int             G_task_cnt = 0;
static pthread_mutex_t G_mx = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t  G_cond = PTHREAD_COND_INITIALIZER;
// G_stop is a flag set by the main process to tell others that
// there is no longer anything to do and that they can quit.
static char            G_stop = 0;


// Function run by the threads.
void *process_task(void *arg) {
    int job;

    while (1) {
      // Wait for something to happen in the stack
      printf("Consumer - thread %d blocking\n", (int)pthread_self());
      fflush(stdout);
      pthread_mutex_lock(&G_mx);
      if (G_stop && G_task_cnt == 0) {
        // Must stop and nothing to do.
        pthread_mutex_unlock(&G_mx);
        break; // Exit the infinite loop
      }
      // We are waken up from pthread_cond_wait() by the
      // pthread_cond_broadcast() call of the producer. But when
      // we look into the stack, another thread may have been quicker
      // and already grabbed the job, so we must double check while
      // we hold the mutex.
      // pthread_cond_wait() releases the mutex when it starts, then
      // gets it just before it returns.
      while (!G_stop && G_task_cnt == 0) {
        pthread_cond_wait(&G_cond, &G_mx);
      }
      if (G_stop && G_task_cnt == 0) {
        // The next line was forgotten during the lecture demo,
        // the lock was still officially head by a thread that 
        // had terminated and it was preventing the main thread
        // from releasing the mutex at the end of the program.
        pthread_mutex_unlock(&G_mx); // We are holding the lock!
                                     // Release it.
        break; // Exit the infinite loop
      }
      printf("Consumer - thread %d waking up\n", (int)pthread_self());
      fflush(stdout);
      // OK, there is something in the stack and nobody but 
      // this thread can touch it right now. The thread
      // removes it from the stack and releases the lock.
      // You try to release locks as soon as you can.
      G_task_cnt--;
      job = G_tasks[G_task_cnt];
      pthread_mutex_unlock(&G_mx);
      // Now performa the job. No need for a lock here if
      // we aren't sharing any resources with another thread.
      printf("Consumer - Thread %d processing job %d\n",
             (int)pthread_self(), job);
      fflush(stdout);
      // Suppose it takes anything between 1 and 6 seconds to do
      sleep(1 + random() % 6);
    }
    printf("Consumer - thread %d returning\n", (int)pthread_self());
    return NULL;
}

int main(int argc, char **argv) {
    int            i; 
    int            t;
    int            threads = 1;
    pthread_t     *thr = NULL;
    time_t         now;
    int            max_jobs = 0;
    unsigned int   nap;
    unsigned int   n;

    if (argc < 2) {
      fprintf(stderr, "Usage: %s <max jobs> [threads]\n", argv[0]);
      return 1;
    }
    if ((sscanf(argv[1], "%d", &max_jobs) != 1)
        || (max_jobs == 0)) {
      fprintf(stderr, "Usage: %s <max jobs> [threads]\n", argv[0]);
      fprintf(stderr, " max jobs must be a strictly positive integer\n");
      return 1;
    }
    if (argc == 3) {
      if ((sscanf(argv[2], "%d", &threads) != 1)
           || (threads < 1)) {
        fprintf(stderr, "Usage: %s <max jobs> [threads]\n", argv[0]);
        fprintf(stderr,
                " The number of threads must be a strictly positive integer\n");
        return 1;
      }
    }
    if ((thr = (pthread_t *)malloc(sizeof(pthread_t) * threads)) == NULL) {
      perror("malloc()");
      return 1;
    }
    // Initialize the random number generator
    now = time(NULL);
    srandom((unsigned)now);
    // Start the threads
    printf("Creating %d threads\n", threads);
    fflush(stdout);
    for (t = 0; t < threads; t++) {
      if (pthread_create(&(thr[t]), NULL, process_task, NULL)) {
        perror("pthread_create()");
      }
    }
    // Post the jobs into the queue
    i = 0;
    while (i < max_jobs) {
       if (G_task_cnt < STACK_SIZE - 1) {
         i++;
         pthread_mutex_lock(&G_mx);
         // Modify the stack
         G_tasks[G_task_cnt] = i;
         G_task_cnt++;
         printf("Producer - Posting job %d\n", i);
         fflush(stdout);
         // Let everybody know that there is something new
         pthread_cond_broadcast(&G_cond);
         pthread_mutex_unlock(&G_mx);
       } else {
         // Might happen if job requests are arriving faster
         // than consumers can work. If this happens too often
         // you need more consumers.
         printf("Producer - stack full\n");
       }
       // Sleep anywhere between 0.1 and 1 second
       // It simulates the random arrival of request,
       // for instance HTTP "GET" requests received by
       // a HTTP server, or queries received by a database
       // server.
       nap = 100000 + (1 + random() % 10) * 90000;
       printf("Producer - sleeping %.2lf\n", nap / 1000000.0);
       usleep(nap);
    }
    // All jobs have been posted
    printf("Producer - setting stop flag\n");
    fflush(stdout);
    G_stop = 1;
    // Wake up the threads, so that they take a look at G_stop
    pthread_mutex_lock(&G_mx);
    pthread_cond_broadcast(&G_cond);
    pthread_mutex_unlock(&G_mx);
    // Now loop until all of them have returned. Some threads
    // may still be working, we don't want to interrupt them
    // brutally.
    for (t = 0; t < threads; t++) {
      (void)pthread_join(thr[t], (void **)&n);
    }
    // Free resources
    free(thr);
    (void)pthread_mutex_destroy(&G_mx);
    (void)pthread_cond_destroy(&G_cond);
    return 0;
}
