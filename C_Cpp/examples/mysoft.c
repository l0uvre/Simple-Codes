//
// This program demonstrates a number of available techniques for
// finding a data file - a default name can be set that can be
// overriden by options, or you can first try to find where the program
// is located and derive the location of a data file (in a directory nearby,
// or in the same directory) from there, with help from the dirname()
// function.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>

#define LINE_LEN 200
#define DEFAULT_FILE "data.txt"

#define OPTIONS  "df:"

static char G_debug = 0;

int main(int argc, char **argv) {
    FILE *fp;
    char  line[LINE_LEN];
    int   ch;
    char  fname[FILENAME_MAX];
    char  fname2[FILENAME_MAX];
    char *path;
    char *p;

    strncpy(fname, getenv("HOME"), FILENAME_MAX);
    strncat(fname, "/", FILENAME_MAX - strlen(fname));
    strncat(fname, DEFAULT_FILE, FILENAME_MAX - strlen(fname));
    while ((ch = getopt(argc, argv, OPTIONS)) != -1) {
       switch (ch) {
          case 'd':
               G_debug = 1;
               fprintf(stderr, "-- Debugging turned on\n");
               break;
          case 'f':
               strncpy(fname, optarg, FILENAME_MAX);
               break;
          default:
               fprintf(stderr, "Watch what you are typing\n");
               exit(1);
       }
    }
    if (realpath(argv[0], fname2)) {
       printf("*** I'm running from %s\n", fname2);
    } else {
       char disaster = 1;

       if ((p = strchr(argv[0] , '/')) == NULL) {
         if (G_debug) {
           fprintf(stderr, "-- Looking for %s\n", argv[0]);
         }
         if ((p = getenv("PATH")) != NULL) {
            char       *q;
            struct stat buf;
            char        fname3[FILENAME_MAX];
            path = strdup(p);
            p = path;
            while (p) {
              if ((q = strchr(p, ':')) != NULL) {
                *q++ = '\0';
              }
              strncpy(fname3, p, FILENAME_MAX);
              strncat(fname3, "/", FILENAME_MAX - strlen(fname3));
              strncat(fname3, argv[0], FILENAME_MAX - strlen(fname3));
              if (stat(fname3, &buf) != -1) {
                if (G_debug) {
                  fprintf(stderr, "-- found in %s\n", p);
                }
                p = NULL;
                if (realpath(fname3, fname2)) {
                  printf("*** I'm running from %s\n", fname2);
                  disaster = 0;
                }
              } else {
                p = q;
              }
            }
            free(path);
         }
       }
       if (disaster) {
         perror("mysoft: realpath()");
       }
    }
    if (G_debug) {
       fprintf(stderr, "-- optind = %d\n", optind);
    }
    if ((fp = fopen(fname, "r")) != NULL) {
      while (fgets(line, LINE_LEN, fp)) {
         printf("*** %s", line);
      }
      fclose(fp);
    } else {
      perror(fname);
    }

}
