/*
 *   This shows a "master-slave" configuration, commonly
 *   used for instance for simulating a variable number
 *   of "agents". Agents may collect information, and the parent
 *   process may consolidate it in the end (which assumes some
 *   inter-process communication through a message queue or shared
 *   memory, not shown here).
 *
 *   Written by S. Faroult
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>

// The number of slaves can be optionally overriden on the command-line
#define DEFAULT_SLAVES 5

void slave_work(int nap) {
    // Just pretend to work
    sleep(nap);
    exit(nap % 5);
}

int main(int argc, char *argv[]) {
    pid_t pid;
    int   status; 
    int   slaves;
    int   finished = 0;
    int   i;
    int   nap;

    if ((argc == 1)
        || (sscanf(argv[1], "%d", &slaves) == 0)
        || (slaves <= 0)) {
      slaves = DEFAULT_SLAVES;
    }

    // First loop to start the slaves
    for (i = 0; i < slaves; i++) {
      // Make slaves sleep for a random number of seconds
      // between 1 and 30
      nap = 1 + random() % 30;
      if ((pid = fork()) != -1) {
         if (pid == 0) {
           // Child
           slave_work(nap);
         }
       } else {
         perror("fork()");
         exit(1);
       }
    } 
    printf("%d slaves started\n", slaves);
    fflush(stdout);
    // Second loop to wait for termination
    // If you kill a slave it should be reported
    while (finished < slaves) {
      // We block until one process terminates
      pid = wait(&status);
      if (WIFEXITED(status)) {
        printf("%d exited with status %d\n", pid, WEXITSTATUS(status));
      } else if WIFSIGNALED(status) {
         printf("Process %d was killed by signal %d\n", pid, WTERMSIG(status));
      } else {
         printf("Process %d : status %d\n", pid, status);
      } 
      fflush(stdout);
      finished++;
    }
    return 0;
}
