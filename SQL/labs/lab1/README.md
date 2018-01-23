
# Lab1 - Geographical database datebase design with MySQL workbench. 
We want to create a database to store information about world countries and, for the 
biggest countries, the top level of administrative subdivision (states in the US, India, 
Brazil or Germany, provinces in China and Canada, "federal subjects" (oblast, 
republic ...) in Russia, and so forth). Most big countries have variants at the top level 
of administrative subdivisions (autonomous territories, or a special region for a 
federal capital, or everything at once). 

We want to keep track of population, time-zone (we'll ignore daylight saving time - 
DST -, the change of time between winter and summer that occurs in many countries), 
surface, largest city and capital at all levels (national and administrative divisions). 


It might look like a simple database design, but there are some weird cases: 
 - [ ] The capital is not always the largest city (pretty frequent in the US, both at the 
national and state levels) 
 - [ ]  Most countries have a single capital, the Netherlands have two (The Hague 
and Amsterdam) 
 - [ ]   One Indian state (Jammu and Kashmir) also has two capitals, but one capital 
in the summer, and another capital in the winter.
 - [ ]   In India again, some capitals (Chandigarh, Hyderabad) are shared by several 
states 
 - [ ] If the whole of China is in a single time-zone, this isn't the case in the US, 
Canada or Russia. In fact, within Russia, the Sakha Republic (Yakutia) 
spreads over three time zones. There are in the US two different time-zones in 
Arizona, where the Navajo reservation applies a different time (30 minutes 
difference) during a part of the year. 

You are strongly encouraged to spend a little time simply researching the weird cases 
there can be, and thinking about how they can be best, or at least in an acceptable way, 
represented in a database, with as little repetition of data as possible. 
Try to model all this as best as you can ... You will do it using MySQL Workbench, a 
toll frequently used for database modelling, which can be downloaded for free from 
https://dev.mysql.com/downloads/workbench/ (you are at one point prompted for 
registration but you don't need to register, there is a "No, thanks" option). 

