#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <signal.h>
#include <unistd.h>
#include <string>
#include <iostream>

#include "ProgCtl.hpp"

#define CMD_LEN    300
#define LINE_LEN   300

using namespace std;

void ProgCtl::setName(string prog) {
    m_program = prog;
}

void ProgCtl::setName(char *prog) {
    m_program = string(prog);
}

void ProgCtl::find_pid() {
    FILE *p;
    char  cmd[CMD_LEN];
    char  line[LINE_LEN];
    char *s;
    char *s2;

    m_pid = -1;
    if (m_program.length() > 0) {
       sprintf(cmd, "ps -e -o pid,comm | grep -w %s",
                    m_program.c_str());
       if ((p = popen(cmd, "r")) != NULL) {
          if (fgets(line, LINE_LEN, p)) {
             if (sscanf(line, "%d", &m_pid) != 1) {
                m_pid = -1;
             } else {
               s = line;
               while (isspace(*s)) {
                 s++;
               }
               // Skip the pid
               while (isdigit(*s)) {
                 s++;
               }
               while (isspace(*s)) {
                 s++;
               }
               s2 = s;
               while (*s2 && !isspace(*s2)) {
                 s2++;
               }
               *s2 = '\0';
               m_fullprogram = string(s);
             }

          }
          pclose(p);
       }
    }
}

int ProgCtl::send(int sig) {
    int ret;

    if (sig < 0 || sig > 31) {
       return -1;
    }
    if (m_pid == -1) {
       find_pid();
       if (m_pid == -1) {
          return -2;
       }
    } 
    ret = kill(m_pid, sig);
    if (ret) {
       m_pid = -1;
    } else {
       // Check whether the signal killed the program!
       if (sig && kill(m_pid, 0)) {
          m_pid = -1;
       }
    }
    return ret;
}

bool ProgCtl::alive() {
    return (send(0) == 0);
}

int ProgCtl::start() {
    int ret;

    if (m_program.length() > 0) {
       if (!alive()) {
          m_pid = fork();
          if (m_pid == 0) {
             if (m_fullprogram.length() > 0) {
               ret = execl(m_fullprogram.c_str(), m_program.c_str(), NULL);
             } else {
               ret = execl(m_program.c_str(), m_program.c_str(), NULL);
             }
             if (ret == -1) {
                cerr << "Failed to start " << m_program << endl;
                exit(1);
             }
          }
       } else {
          return -1;
       }
    } else {
       return -2;
    }
    return 0;
}
