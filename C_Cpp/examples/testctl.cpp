#include <stdio.h>
#include <readline/readline.h>
#include <readline/history.h>

#include "ProgCtl.hpp"
#include "cmd.h"

#define LINE_LEN  100

static char *G_line_read = (char *)NULL;

/* Read a string, and return a pointer to it.  Returns NULL on EOF. */
char * rl_gets (const char *prompt) {
   if (G_line_read) {
      free (G_line_read);
      G_line_read = (char *)NULL;
   }

   /* Get a line from the user. */
   G_line_read = readline(prompt ? prompt : "");

   /* If the line has any text in it, save it on the history. */
   if (G_line_read && *G_line_read) {
      add_history(G_line_read);
   }
   return(G_line_read);
}

int main() {
    char     loop = 1;
    char     line[LINE_LEN];
    char    *p;
    int      cmd;
    int      ret;
    int      sig;
    ProgCtl  ctl;

    while (loop) {
       p = rl_gets("> ");
       if (p && *p) {
         strncpy(line, p, LINE_LEN);
         p = strtok(line, " \t");
         if (p) {
            cmd = cmd_search(p);
            switch (cmd) {
                case CMD_MONITOR:
                     if ((p = strtok(NULL, " \t")) != NULL) {
                        ctl.setName(p);
                        printf("Monitoring %s\n", p);
                     } else {
                        printf("Program name expected\n");
                     }
                     break;
                case CMD_SEND:
                     if ((p = strtok(NULL, " \t")) != NULL) {
                        if (sscanf(p, "%d", &sig) == 1) {         
                           ret = ctl.send(sig);
                           switch(ret) {
                              case -1 :
                                   printf("Invalid signal %d\n", sig);
                                   break;
                              case -2 :
                                   printf("Program not running\n");
                                   break;
                              case  0 :
                                   printf("Signal successfully sent\n");
                                   break;
                              default:
                                   printf("Program not running\n");
                                   break;
                           }
                        } else {
                           printf("Signal number must be an integer\n");
                        }
                     } else {
                        printf("Signal number expected\n");
                     }
                     break;
                case CMD_STATUS:
                     if (ctl.alive()) {
                        printf("Program running\n");
                     } else {
                        printf("Program not running\n");
                     }
                     break;
                case CMD_START:
                     ret = ctl.start();
                     switch (ret) {
                        case -1: 
                             printf("Program already running\n");
                             break;
                        case -2: 
                             printf("Unspecified Program\n");
                             break;
                        default:
                             printf("Starting ...\n");
                             break;
                     }
                     break;
                case CMD_QUIT:
                     loop = 0;
                     break;
                default:
                   printf("Unknown command \"%s\"\n", p);
                   break;
            }
         }
       }
    }
    return 0;
}
