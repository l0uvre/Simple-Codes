#ifndef CMD_HEADER

#define CMD_HEADER

#define CMD_NOT_FOUND	-1
#define CMD_MONITOR	  0
#define CMD_QUIT	  1
#define CMD_SEND	  2
#define CMD_START	  3
#define CMD_STATUS	  4

#define CMD_COUNT	5

extern int cmd_search(char *w);

#endif
