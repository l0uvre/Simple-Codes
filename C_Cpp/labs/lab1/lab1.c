#include <stdio.h>
#include <math.h>
#include <string.h>

#define  EARTHRADIUS 6371
#define  INPUT_LEN 80


int main() {
  char city1[41], city2[41], input[INPUT_LEN];
  float latitude1, longitude1;
  float latitude2, longitude2;
  float distance;

  printf("Please enter the name of first city: ");
  fgets(input, INPUT_LEN, stdin);
  sscanf(input, "%[^\n]", city1);

  printf("Please enter the latitude and longitude of the city with one whitespace: ");
  fgets(input, INPUT_LEN, stdin);
  sscanf(input, "%f %f", &latitude1, &longitude1);


  printf("Please enter the name of second city: ");
  fgets(input, INPUT_LEN, stdin);
  sscanf(input, "%[^\n]", city2);

  printf("Please enter the latitude and longitude of the city with one whitespace: ");
  fgets(input, INPUT_LEN, stdin);
  sscanf(input, "%f %f", &latitude2, &longitude2);

  float phi1 = (90 - latitude1) / 180.0 * M_PI;
  float phi2 = (90 - latitude2) / 180.0 * M_PI;
  float theta1 = longitude1 / 180.0 * M_PI;
  float theta2 = longitude2 / 180.0 * M_PI;
  distance = EARTHRADIUS * acos(sin(phi1)*sin(phi2) * cos(theta1 - theta2) + cos(phi1) * cos(phi2));

  printf("The distance between %s and %s is %f km \n", city1, city2, distance);
  return 0;
}
