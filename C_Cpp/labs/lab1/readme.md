
#Lab 1

One of the goals of this lab is to make you familiar with C in a Linux (or Linux
like) environment. If you are on a Windows machine you should install
Cygwin, which is available at http://cygwin.com. Cygwin is a free environment
that simulates Linux on a Windows machine. When installing it you must
install the following packages that are development packages and aren't
installed by default:
 </br>**gcc-g++** and ***make***



 You are asked for this lab to write a simple program that, as you will see, may
 not be as simple to write in C as it looks if you want to write robust programs.
 It will allow you to learn about basic input/output. </br>

 This program must prompt the user for the name of a first city, then its
 latitude and longitude, then for the name of a second city with its latitude and
 longitude, then compute the flying distance between the two and display

 **The	distance	between	*first city*	and	*second	city*	is	*result*	km**

 Here is the formula for computing the distance.

 ***Formula (adapted from mathforum.org, provided by Doctor Rob)***

 Assume the Earth is a perfect sphere.  Let all angles be measured in signed degrees (negative latitude means South, negative longitude means West)

Let phi = 90 - latitude. The North Pole has phi = 0, the South Pole has phi = 180, and 0 <= phi <= 180.

Let theta = longitude. Greenwich, England, has theta = 0, and -180 <= theta <= 180.

Let the angles for the two points be (phi1, theta1) and (phi2, theta2). Then compute

c = sin(phi1)*sin(phi2)*cos(theta1-theta2) + cos(phi1)*cos(phi2).

Then the shortest great circle distance between the two points is
d = R*arccos(c)*Pi/180,

where R is the radius of the earth in kilometers, and the arccosine is taken between 0 and 180 degrees, inclusive.





Earth radius: 6,371 km

Some cities for testing:
Shenzhen:  22.55, 114.1

Beijing : 39.9139, 116.3917

New York, USA :  40.7127,  -74.0059

San Francisco, USA : 37.7833, -122.4167

London, UK : 51.5072, -0.1275

Paris, France :  48.8567, 2.3508

Kolkata, India : 22.567, 88.367

Moscow, Russia : 55.7500, 37.6167

Rio de Janeiro, Brazil : -22.9083, -43.1964

Sydney, Australia: -33.865, 151.209444
