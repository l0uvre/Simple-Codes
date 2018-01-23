# Assignment definition: 
in postgreSQL
Write a function called arrival_time() that takes departure time (string, format HH:MI) , flight time in 
minutes (int), time zone difference in minutes (int) and returns the arrival time at destination (string). 
The arrival time should be followed by +1 if it's the next day, for instance: 07:35+1 (it's the usual way of 
giving arrival times in schedules and it can also be -1 and the previous day between some places, when 
crossing the international date change line from east to west - rather rare, it must only  some flights 
from Hawai to Guam). 
