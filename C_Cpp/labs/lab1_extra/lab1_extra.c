#include <stdio.h>
#include <string.h>

#define START_CMD    0
#define STOP_CMD     1
#define RESTART_CMD  2
#define STATUS_CMD   3
#define EXIT_CMD     4



int main() {
  char *commands[] = {"start", "stop", "restart", "status", "exit"};
  char input[20];
  char cmd[20];
  int command = 6;

  fprintf(stdout, "Type \"%s\", \"%s\", \"%s\", \"%s\" for controls or \"%s\" to quit.  \n", commands[0], commands[1], commands[2], commands[3], commands[4]);

  while (command == 6) {
    printf(">>>");
    fgets(input, 20, stdin);
    sscanf(input, "%[^\n]", cmd);

    for (int i = 0; i < 5; i++)
    {
      if (strcmp(cmd, commands[i]) == 0)
      {
         command = i;
      }
    }
    switch (command) {
      case START_CMD:
        printf("command <%s> recognized\n", commands[START_CMD]);
        command = 6;
        break;
      case STOP_CMD:
        printf("command <%s> recognized\n", commands[STOP_CMD]);
        command = 6;
        break;
      case RESTART_CMD:
        printf("command <%s> recognized\n", commands[RESTART_CMD]);
        command = 6;
        break;
      case STATUS_CMD:
        printf("command <%s> recognized\n", commands[STATUS_CMD]);
        command = 6;
        break;
      case EXIT_CMD:
        command = -1;
        break;
      default:
        command = 6;
        fprintf(stdout, "Invalid command. Type it again.\n");
        break;
    }

  }


  return 0;
}
