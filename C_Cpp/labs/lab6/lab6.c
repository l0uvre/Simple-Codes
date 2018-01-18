#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
#include <errno.h>
#include <sys/wait.h>

#define SLEEP_TIME  5

int        nap = SLEEP_TIME;
char       whoami[50];

void parentBehavior(pid_t cpid);
void childBehavior();


void handler(int sig) {
  // kill the process in the same group including the child and the father.
   kill(0, SIGKILL);
}

void handler2(int sig) {
   printf("%s\n", whoami);
}
void parentBehavior(pid_t cpid) {
  int        remains = 0;
  pid_t      pid;
  time_t     now;
  struct tm *t;
  int status;

  while (1) {
    // if the child dies.
    if (kill(cpid, 0) != 0) {
      pid = fork();
      if (pid == -1) {
         perror("fork()");
         exit(1);
      } else if (pid == 0) {
        sprintf(whoami, "child (%d)", getpid());
        //(void)signal(SIGTRAP, handler);
        childBehavior();
      } else {
        //(void)signal(SIGTRAP, handler);
        parentBehavior(pid);
      }
    } else {
      if (remains == 0) {
        now = time(NULL);
        t = localtime(&now);
        printf("%s %02d:%02d:%02d beep!\n",
                whoami,
                t->tm_hour, t->tm_min, t->tm_sec);
        remains = sleep(nap);
      } else {
        remains = sleep(remains - 1);
      }
    }

    waitpid(cpid, &status, 0);

  }
}

void childBehavior() {
  pid_t      pid;
  pid_t      ppid;
  time_t     now;
  struct tm *t;

  ppid = getppid();
  while (1) {
    usleep(500000);

    // if the parent process dies;
    if (ppid == 1 || kill(ppid, 0) != 0) {
      pid = fork();
      if (pid == -1) {
         perror("fork()");
         exit(1);
      } else if (pid == 0) {
        sprintf(whoami, "child (%d)", getpid());
        childBehavior();
      } else {
        sprintf(whoami, "parent (%d)", getpid());
        parentBehavior(pid);
      }
    } else {
      // if the parent still alive
      now = time(NULL);
      t = localtime(&now);
      printf("%s %02d:%02d:%02d is checking father(%d) which is alive!\n",
              whoami, t->tm_hour, t->tm_min, t->tm_sec, getppid());
    }
  }
}

int main() {

  pid_t      pid, cpid;

  sprintf(whoami, "parent (%d)", getpid());
  for (int i = 1; i < 32; i++) {
    (void)signal(i, handler2);
  }
  (void)signal(SIGTRAP, handler);

  if ((pid = fork()) == -1) {
     perror("fork()");
     exit(1);
  } else if (pid == 0) {
    sprintf(whoami, "child (%d)", getpid());
    (void)signal(SIGTRAP, handler);

    childBehavior();
  } else {
    cpid = pid;
    (void)signal(SIGTRAP, handler);
    parentBehavior(cpid);
  }
  return 0;
}
