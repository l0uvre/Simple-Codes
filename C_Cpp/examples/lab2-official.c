#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>
#include <math.h>

#define CITY_NAME_LEN      50
#define COUNTRY_NAME_LEN   50

#define CITY_COUNT       1500
#define UNKNOWN_CITY    (-1 * CITY_COUNT)
#define LINE_LEN          200

typedef struct city {
                 char   name[CITY_NAME_LEN];
                 char   country[COUNTRY_NAME_LEN];
                 double latitude;
                 double longitude;
               } CITY_T;

static CITY_T G_cities[CITY_COUNT];
static int    G_city_cnt = 0;

int distance(double lat1, double lon1, double lat2, double lon2) {
    double phi1 = (90 - lat1) * M_PI / 180;
    double phi2 = (90 - lat2) * M_PI / 180;
    double theta1 = lon1 * M_PI / 180;
    double theta2 = lon2 * M_PI / 180;
    double c;
    int    d;
    c = sin(phi1)*sin(phi2)*cos(theta1-theta2) + cos(phi1)*cos(phi2);
    d = (int)6371*acos(c);
    return d;
}

int solve_ambiguity(int idx, char *name) {
   int  i = -1 * idx;
   int  len;
   int  c;
   int  answ = 0;
   char input[LINE_LEN];

   if ((idx < 0) && name) {
     len = strlen(name);
     printf("\"%s\" is ambiguous.\n", name);
     printf("Do you mean\n");
     printf("    1. %s, %s\n",
            G_cities[i].name,
            G_cities[i].country);
     c = 1;
     while (strncasecmp(G_cities[i+c].name, name, len) == 0) {
       printf(" or %1d. %s, %s\n",
              c + 1,
              G_cities[i+c].name,
              G_cities[i+c].country);
       c++;
     }
     do {
       printf("Please enter the corresponding number: ");
       fgets(input, LINE_LEN, stdin);
     } while ((sscanf(input, "%d", &answ) == 0) || (answ < 1) || (answ > c));
     idx = i + answ - 1;
   }
   return idx;
}

int city_index(char *name) {
    // Very simple sequential search
    int i = 0;
    int cmp = 1;
    int len;

    if (name && (strlen(name) > 2)) {
      len = strlen(name);
      while ((i < G_city_cnt)
              && ((cmp = strncasecmp(name, G_cities[i].name, len)) > 0)) {
        i++;
      }
    }
    if (cmp == 0) {
      // Check whether it can be ambiguous: does the NEXT name
      // also match?
      if ((i < G_city_cnt-1)
         && !strncasecmp(name, G_cities[i+1].name, len)) {
        i *= -1;  // Return a negative value
      }
      return i;
    }
    return UNKNOWN_CITY;
}

char *trim(char *s) {
  char *p = s;
  int   len;

  if (p) {
    while (isspace(*p)) {
      p++;
    }
    len = strlen(p);
    while (len && isspace(p[len-1])) {
      len--;
    }
    p[len] = '\0';
  }
  return p;
}

int main(int argc, char *argv[]) {
   FILE *fp;
   char  line[LINE_LEN];
   char *p;
   char *l;
   int   linecnt = 0;
   char  stop = 0;
   int   c1;
   int   c2;

   if ((fp = fopen("world_cities.csv", "r")) != NULL) {
     while ((G_city_cnt < CITY_COUNT - 1)
            && ((l = fgets(line, LINE_LEN, fp)) != NULL)) {
       linecnt++;
       l = trim(line);
       if ((p = strchr(l, ',')) != NULL) {
         *p++ = '\0';
         strncpy(G_cities[G_city_cnt].name, l, CITY_NAME_LEN);
         l = p;
         if ((p = strchr(l, ',')) != NULL) {
           p++; // Skip province
           l = p;
           if ((p = strchr(l, ',')) != NULL) {
             *p++ = '\0';
             strncpy(G_cities[G_city_cnt].country, l, COUNTRY_NAME_LEN);
             if (sscanf(p, "%lf,%lf",  
                        &(G_cities[G_city_cnt].latitude),
                        &(G_cities[G_city_cnt].longitude)) == 2) {
               G_city_cnt++;
             } else {
               fprintf(stderr,
                       "-- Error reading latitude/longitude line %d\n",
                       linecnt);
             }
           } else {
             fprintf(stderr,
                     "-- Error reading country name line %d\n",
                     linecnt);
           }
         } else {
           fprintf(stderr,
                   "-- Error reading country name line %d\n",
                   linecnt);
         }
       } else {
         fprintf(stderr,
                 "-- Error reading line %d\n",
                 linecnt);
       }
     }
     if (G_city_cnt == CITY_COUNT - 1) {
       fprintf(stderr, "Warning: load stopped after %d cities\n",
               G_city_cnt - 1);
     } else {
       printf("Information about %d cities loaded\n", G_city_cnt);
     }
     fclose(fp);
   } else {
     perror("world_cities.csv");
     return 1;
   }
   printf("Input \"bye\" to exit the program.\n"); 
   while (!stop) {
      c1 = UNKNOWN_CITY;
      c2 = UNKNOWN_CITY;
      while (!stop && (c1 == UNKNOWN_CITY)) {
        printf("Enter two cities> ");
        l = fgets(line, LINE_LEN, stdin);
        if (l) {
          l = trim(line);
          if (strcasecmp(l, "bye") == 0) {
            stop = 1;
          } else {
            // We accept two cities on the same line,
            // separated by a comma
            if ((p = strchr(l, ',')) != NULL) {
              *p++ = '\0';
              p = trim(p);
              if (strcasecmp(p, "bye") == 0) {
                stop = 1;
              }
            }
            if (!stop) {
              c1 = city_index(l);
              if (c1 != UNKNOWN_CITY) {
                if (c1 < 0) { // Ambiguous
                  c1 = solve_ambiguity(c1, l);
                }
                if (p) {
                  c2 = city_index(p);
                  if (c2 == UNKNOWN_CITY) {
                    printf("Unknown city \"%s\"\n", p);
                  } else if (c2 < 0) {
                    c2 = solve_ambiguity(c2, p);
                  }
                }
                while (!stop && (c2 == UNKNOWN_CITY)) {
                  printf("     Second city> ");
                  l = fgets(line, LINE_LEN, stdin);
                  if (l) {
                    l = trim(line);
                    if (strcasecmp(l, "bye") == 0) {
                      stop = 1;
                    } else {
                      c2 = city_index(l);
                      if (c2 == UNKNOWN_CITY) {
                        printf("Unknown city \"%s\"\n", l);
                      }
                    }
                  }
                }
                if (!stop && (c2 < 0)) { // Ambiguous
                  c2 = solve_ambiguity(c2, l);
                }
                if (!stop) {
                  printf("Distance from %s to %s: %d km\n", 
                         G_cities[c1].name,
                         G_cities[c2].name,
                         distance(G_cities[c1].latitude,
                                  G_cities[c1].longitude,
                                  G_cities[c2].latitude,
                                  G_cities[c2].longitude));
                }
              } else {
                printf("Unknown city \"%s\"\n", l);
              }
            }
          }
        }
      }
   }
   return 0;
}

