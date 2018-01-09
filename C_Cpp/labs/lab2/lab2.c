#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
#include <string.h>

#define MAX_LEN_NAME 35
#define ARRAY_SIZE 1000
#define INPUT_LEN 256


typedef struct City
{
  char cityName[MAX_LEN_NAME];
  char countryName[MAX_LEN_NAME];
  double lat;
  double lon;
} city;

int distance(double lat1, double lon1, double lat2, double lon2);

int main() {

  city cities[ARRAY_SIZE];
  char dummy[50];
  FILE *fp = fopen("world_cities.csv", "r");
  int count = 0;
  int command = 1;


  if (!fp)
  {
    printf("File doesn't exist.\n");
    return 1;
  }

/***Description:

About the user's input, this program allow user to input with space before the name or after it.
Like if the input is "                   santiago ", this program will cast it to "Santiago";
If the input is "           new york    ", it will cast it to "New York" and search the info of arrays read from
csv files.
At the process, I notice some bugs, like if the input is "   santiago de  ", it will cast it to "Santiago De",
which is not like a real name used in the western world, so I handle it.

I know that there is one way to cast the input to lowercase, and so can to cityName from the csv file to match.
But I still prefer this way, which is to capitalize characters as it should be to match the cityName;

****/


  /**
  Match three patterns through the csv file, as I am not so familiar with regex.
  */
  int ret = fscanf(fp, "%[^,],%[^,],%[^,],%lf,%lf\n", cities[count].cityName, dummy, cities[count].countryName,
  &cities[count].lat, &cities[count].lon);

  if (ret == 1)
  {
    fseek(fp, -strlen(cities[count].cityName)-1, SEEK_CUR);
    ret = fscanf(fp, "%[^,],,%[^,],%lf,%lf\n", cities[count].cityName, cities[count].countryName,
    &cities[count].lat, &cities[count].lon);
  }
  else if (ret == 2)
  {
    fseek(fp, -strlen(cities[count].cityName)-strlen(dummy)-2, SEEK_CUR);
    ret = fscanf(fp, "%[^,],%[^,],,%lf,%lf\n", cities[count].cityName, cities[count].countryName,
    &cities[count].lat, &cities[count].lon);
  }


  while (ret != EOF && count < ARRAY_SIZE)
  {
    ret = fscanf(fp, "%[^,],%[^,],%[^,],%lf,%lf\n", cities[count].cityName, dummy, cities[count].countryName,
    &cities[count].lat, &cities[count].lon);

    if (ret == 1)
    {
      fseek(fp, -strlen(cities[count].cityName)-1, SEEK_CUR);
      ret = fscanf(fp, "%[^,],,%[^,],%lf,%lf\n", cities[count].cityName, cities[count].countryName,
      &cities[count].lat, &cities[count].lon);
    }
    else if (ret == 2)
    {
      fseek(fp, -strlen(cities[count].cityName)-strlen(dummy)-2, SEEK_CUR);
      ret = fscanf(fp, "%[^,],%[^,],,%lf,%lf\n", cities[count].cityName, cities[count].countryName,
      &cities[count].lat, &cities[count].lon);
    }

    count++;
  }

  /*for (int i = 0; i < count; i++) {
    printf("%s | %s | %lf | %lf\n", cities[i].cityName, cities[i].countryName, cities[i].lat, cities[i].lon);
  }*/

  /*
  if the file pointer doesn't go through the file, it means some data are missing.
  */

  if (ret != EOF)
  {
    printf("Warning: The date is only partially loaded.\n");
    printf("Only %d cities and their country, latitude and longitude are loaded\n", count);
    printf("To quit the program, simply type 'bye'\n");
    //fclose(fp);
    //return 2;
  }
  else
  {
    printf("All %d date loaded.\n", count);
    printf("To quit the program, simply type 'bye'\n");

  }
   fclose(fp);

   /*
   Go to the loop until the user type 'bye'.
   */
   while (command)
   {
     char qCity[MAX_LEN_NAME];
     char pCity[MAX_LEN_NAME];
     char cmd[INPUT_LEN];
     char input[INPUT_LEN];

     int firstNotGet = 1;
     int secondNotGet = 1;
     double latitude1, latitude2, longitude1, longitude2;

     int pCityMatched;
     int qCityMatched;

     int real_input_len;

     while (firstNotGet)
     {
       printf("Please enter the name of first city: ");
       if(!fgets(input, INPUT_LEN, stdin)) {
         printf("Sorry, the name you put is too long\n");
       }
       else
       {
         sscanf(input, "%[^\n]", cmd);
       }


       //printf("%s\n", cmd);
       /*
       simplify the user's input
       */

       real_input_len = 0;
       for (int i = 0, n = strlen(cmd); i < n; i++)
       {

         if (i == 0)
         {
           if (cmd[i] != ' ')
           {
             cmd[real_input_len] = toupper(cmd[i]);
             real_input_len++;
             continue;
           }
         }

         if (cmd[i] == ' ' && cmd[0] == ' ') {
           continue;
         }

        if (cmd[i - 1] == ' ' && cmd[i] != ' ')
        {
            if (cmd[i] == 'd' && cmd[i+1] == 'e' && (cmd[i+2] == ' ' || cmd[i+2] == '\0')) {
              cmd[real_input_len] = tolower(cmd[i]);
              real_input_len++;
            }
            else
            {
              cmd[real_input_len] = toupper(cmd[i]);
              real_input_len++;
            }

        }
        else
        {
          if (isalpha(cmd[i])) {
            cmd[real_input_len] = tolower(cmd[i]);
            real_input_len++;
          }
          else if (isspace(cmd[i]))
          {
            cmd[real_input_len] = cmd[i];
            real_input_len++;
          }
          else
          {
            cmd[real_input_len] = cmd[i];
            real_input_len++;
          }

        }
       }
       cmd[real_input_len] = '\0';

       while (cmd[real_input_len - 1] == ' ') {
         cmd[real_input_len - 1] = '\0';
         real_input_len--;
       }

       //printf("%s\n", cmd);
       //printf("%d\n", real_input_len);


       if (strlen(cmd) < 3) {
         printf("Sorry, the name you input is too short.\n");
         continue;
       }

       if (strcmp(cmd, "Bye") == 0)
       {
         printf("Thanks for useing this programm, Bye!\n");
         return 0;
       }

       pCityMatched = 0;
       for (int i = 0; i < count; i++)
       {
         if (strncmp(cmd, cities[i].cityName, real_input_len) == 0)
         {
           latitude1 = cities[i].lat;
           longitude1 = cities[i].lon;
           strcpy(pCity, cities[i].cityName);

           if (strlen(cmd) == strlen(cities[i].cityName))
           {
             pCityMatched = 1;
             break;
           }
           else
               pCityMatched++;
         }
       }

       if (pCityMatched == 1)
       {
         firstNotGet = 0;
       }
       else if (pCityMatched > 1)
       {
         printf("A lot of cities match your input. Please type again to select one.\n");
         printf("Matched cities shown below:\n");
         for (int i = 0; i < count; i++)
         {
           if (strncmp(cmd, cities[i].cityName, real_input_len) == 0)
           {
             printf("\t%s\n", cities[i].cityName);
           }
         }

       }
       else
       {
         printf("No cities match your input. Please type again.\n");
       }


      }

      while (secondNotGet)
      {
        printf("Please enter the name of second city: ");
        if(!fgets(input, INPUT_LEN, stdin)) {
          printf("Sorry, the name you put is too long\n");
        }
        else
        {
          sscanf(input, "%[^\n]", cmd);
        }


        real_input_len = 0;
        for (int i = 0, n = strlen(cmd); i < n; i++)
        {

          if (i == 0)
          {
            if (cmd[i] != ' ')
            {
              cmd[real_input_len] = toupper(cmd[i]);
              real_input_len++;
              continue;
            }
          }

          if (cmd[i] == ' ' && cmd[0] == ' ') {
            continue;
          }

          if (cmd[i - 1] == ' ' && cmd[i] != ' ')
          {

            if (cmd[i] == 'd' && cmd[i+1] == 'e' && (cmd[i+2] == ' ' || cmd[i+2] == '\0')) {
              cmd[real_input_len] = tolower(cmd[i]);
              real_input_len++;
            }
            else
            {
              cmd[real_input_len] = toupper(cmd[i]);
              real_input_len++;
            }

          }
         else
         {
           if (isalpha(cmd[i])) {
             cmd[real_input_len] = tolower(cmd[i]);
             real_input_len++;
           }
           else if (isspace(cmd[i]))
           {
             cmd[real_input_len] = cmd[i];
             real_input_len++;
           }
           else
           {
             cmd[real_input_len] = cmd[i];
             real_input_len++;
           }

         }
        }
        cmd[real_input_len] = '\0';
        while (cmd[real_input_len - 1] == ' ') {
          cmd[real_input_len - 1] = '\0';
          real_input_len--;
        }


        if (strlen(cmd) < 3) {
          printf("Sorry, the name you input is too short.\n");
          continue;
        }

        if (strcmp(cmd, "Bye") == 0)
        {
          printf("Thanks for useing this programm, Bye!\n");
          return 0;
        }

        qCityMatched = 0;
        for (int i = 0; i < count; i++)
        {
          if (strncmp(cmd, cities[i].cityName, real_input_len) == 0)
          {
            latitude2 = cities[i].lat;
            longitude2 = cities[i].lon;
            strcpy(qCity, cities[i].cityName);
            if (strlen(cmd) == strlen(cities[i].cityName))
            {
              qCityMatched = 1;
              break;
            }
            else
                qCityMatched++;
          }
        }

        if (qCityMatched == 1)
        {
          secondNotGet = 0;

        }
        else if(qCityMatched > 1)
        {
          printf("A lot of cities match your input. Please type again to select one.\n");
          printf("Matched cities shown below:\n");
          for (int i = 0; i < count; i++)
          {
            if (strncmp(cmd, cities[i].cityName, real_input_len) == 0)
            {
              printf("\t%s\n", cities[i].cityName);
            }
          }

        }
        else {
          printf("No cities match your input. Please type again.\n");
        }

      }


      printf("The distance between %s and %s is %dkm\n", pCity, qCity, distance(latitude1, longitude1, latitude2, longitude2));

   }



  return 0;
}

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
