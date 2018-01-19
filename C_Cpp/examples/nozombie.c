/*
 *  This program demonstrates how to avoid a zombie process
 *  (you can compare with zombie.c)
 *
 *  Written by S. Faroult
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <errno.h>

// Handler called on SIGCHLD, the signal received
// by a process when a child process has died
void handler(int sig) {
    pid_t pid;
    int   status = 0;

    printf("Tell me why you died ? ");
    pid = wait(&status);
    if (WIFEXITED(status)) {
      printf("%d exited with status %d\n", pid, WEXITSTATUS(status));
    } else if WIFSIGNALED(status) {
       printf("Process %d was killed by signal %d\n", pid, WTERMSIG(status));
    } else {
       printf("Process %d : status %d\n", pid, status);
    }
    printf("Back to bed ...\n");
}

int main(int arg, char *argv[]) {
    pid_t pid;
    int   nap = 60;

    if ((pid = fork()) != -1) {
       if (pid == 0) {
         // Child
         printf("Farewell, cruel world (%d)\n", getpid());
         exit(2);
       } else {
         // Set up signal handling
         (void)signal(SIGCHLD, handler);
         printf("I am a tired father (%d)\n", getpid());
         // sleep() will be interrupted by the signal.
         // In that case, the function returns how many
         // seconds remain from the number of seconds specified
         // when the function was called. The loop ensures
         // that the process will sleep (more or less) for the
         // required time.
         while (nap > 0) {
           nap = sleep(nap);
         }
         printf("Waking up (%d)\n", getpid());
         exit(0);
       }
    } else {
      perror("fork()");
      exit(1);
    } 
    return 0;
}
