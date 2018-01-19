#include <stdio.h>
#include <string.h>

#include "cmd.h"

static char *G_cmd_words[] = {
    (char *)"monitor",
    (char *)"quit",
    (char *)"send",
    (char *)"start",
    (char *)"status",
    NULL};

extern int cmd_search(char *w) {
  int start = 0;
  int end = CMD_COUNT - 1;
  int mid;
  int pos = CMD_NOT_FOUND;
  int comp;
  while(start<=end){
    mid = (start + end) / 2;
    if ((comp = strcasecmp(G_cmd_words[mid], w)) == 0) {
       pos = mid;
       start = end + 1;
    } else {
      if (comp < 0) {
         start = mid + 1;
      } else {
         end = mid - 1;
      }
    }
  }
  return pos;
}

