
# Queries on a single table / no aggregate / no nesting
Please note: last page contains assignment plan, please check it and finish it on time.
NOTE: joins not seen yet. It's OK to check table countries to find the codes to use in a query on movies.
Table alt_titles contains some titles in Chinese (in a column called 'title')
1.	List all films from Portugal or Brazil
```sql
(Same language spoken in both countries)
select * from movies 
where country = 'br'
   or country = 'pt'
or
select * from movies
where country in ('br', 'pt')
```
2.	American films that were released the year you were born
```sql
select * from movies 
where country = 'us' 
  and year_released = ...
```
3.	Spanish films that contain neither 'a' (in any case) nor 'o' (in any case) in their title
```sql
select * from movies
where country = 'sp'
  and lower(title) not like '%o%'
  and lower(title) not like '%a%'
or
select * from movies
where country = 'sp' 
  and not (lower(title) like '%o%' or lower(title) like '%a%')
(note that SQLite isn't case sensitive with like, but Oracle or PostgreSQL would require forcing the case)
```
4.	List all "culturally Chinese" films from the 1980s
```sql
select * from movies
where (country = 'cn' or country = 'hk' or country = 'tw' or country = 'mo') 
  and year_released between 1980 and 1989
or
select * from movies
where country in ('cn', 'hk', 'tw', 'mo')
  and year_released between 1980 and 1989
```
(no Macau (mo) film in the database but there might be one)
Things to point out:
•	Parentheses with or, otherwise wrong result (already shown during the lecture, worth repeating)
•	between includes the boundaries. between 1980 and 1990 is wrong because there are a few films from 1990.
•	Alternatively for the year:
```sql 
year_released >= 1980 and year_released < 1990 (or <= 1989)
```
Fancier way of writing it:
```sql
cast(year_released as varchar) like '198_'
```
(add that changing the type of data on the fly can be very bad for performance; why will be explained later in the course) 

5.	List of all people who where born in 1920 or earlier and are still (according to the database) alive
```sql
 select * from people where born <= 1920 and died is null;
```
6.	List all Chinese titles (in table alt_titles) that contain the character for "mountain"
```sql
select * from alt_titles where title like '%山%';
```
7.	List all titles that contain the word "man".
Naive answer:
```sql
select * from movies where title like '%man%'
```
This will return about everything except what we want - for instance films containing "Woman", "Batman", etc.
Point out:
* Case sensitivity: uppercase not the same as lowercase (beware, depends on the DBMS). Normally, all words are capitalized but you can never be sure.
* Some products provide regular expressions for searches but it's not standard
* Isolated word is either preceded by a space, or followed by a space, or preceded by nothing, or followed by nothing.  It can also be followed by a quote (for instance "Dead Man's Chest")!
So:
A little better:
```sql
select * from movies where title like '%Man%'
```
Complete answer:
```sql
select * from movies
where upper(title) like 'MAN %'
   or upper(title) like 'MAN''%'
   or upper(title) like '% MAN %'
   or upper(title) like '% MAN''%'
   or upper(title) like '% MAN';
```
8.	Display the names of the people in the database who died aged 100 or more
```sql 
select * from people where died >= born + 100;
```
9.	Same question as the previous one but include people who are (according to the database) currently one hundred or more
```sql
select * from people 
where died >= born + 100 
 or (died is null and born <= 1917);
(point out that coding "1917" in the query isn't too good; there are functions for finding the current year, functions will be discussed later)
```
10.	Who are the people with a surname that contains a quote
```sql
select * from people where surname like '%''%';
```
11.	What are the European countries with a code that starts with a c like China?
```sql
select * from countries where continent = 'EUROPE' and country_code like 'c%'
'c%' or 'c_'
```
12.	List the people in the database who have the same first character in their first and last names. (For instance, Charlie Chaplin.)
```sql 
select * from people where surname like substr(first_name,1,1)||'%'
```
 
Queries on a single table - aggregate/distinct
13.	Age of the youngest person in the people table
select 2017 - max(born) from people;
Opportunity for explaining that in a program you cannot change the date every year, and that you can use functions:
```sql 
select cast(substr(current_date, 1, 4) as int) - max(born) from people;
```
14.	Number of films per country code for countries that have a code that starts with 'm'.
```sql 
select country, count(*)
from movies
where country like 'm%'
group by country;
```
15.	There are films from how many countries/territories in the database?
```sql 
select count(distinct country) from movies;
```
16.	Year of release of the oldest Chinese film in the database?
```sql 
select min(year_released)
from movies
where country in ('cn', 'tw', 'hk');
```
17.	How many films in the database for 2010?
```sql
 select count(*) from movies where year_released = 2010;
```
18.	What is the average number of films that we have per year, since 1960 (included)?
```sql 
select year_released, count(*) 
from movies
where year_released >= 1960
group by year_released;
```
19.	How many British films in 1949?
```sql 
select count(*) from movies where country='gb' and year_released=1949;
```
20.	Average number of actors per film
Can be improved with round(avg(cnt))
21.	Number of films per number of directors (how many films have one director, how many have 2, etc. - in a single query)
```sql 
select directors, count(movieid) films
from (select movieid, count(*) directors 
      from credits 
      where credited_as='D'
      group by movieid) dir_cnt
group by directors;
```
Note that films for which no director is known don't appear. They could be added but using techniques not seen yet (union)
22.	On the same row, return how many people are recorded in the database, how many are alive and how many are dead.
```sql 
select count(*), 
       count(died) dead, -- null not counted
       sum(case 
           when died is null then 1 
           else 0 
       end) alive
from people;
```
There are sometimes functions other than case ... end that can be used (ifnull(), decode()), but case ... end is standard, other functions aren't.
23.	The most common surname in the database can be found how many times?
24.	How many people have played in a film that they have directed?
```sql 
select count(*)
from (select c.peopleid
      from credits c
      where c.credited_as in ('A', 'D')
      group by movieid, peopleid
      having count(*) = 2) played_and_directed;
```
Assignment:  **#23** question will be there assignment.
