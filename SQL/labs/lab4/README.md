# Queries Joins

1.	Films directed by John Woo?
```sql
select m.*
from movies m
    join credits c
      on c.movieid = m.movieid
    join people p
      on p.peopleid = c.peopleid
where c.credited_as = 'D'
  and p.first_name = 'John'
  and p.surname = 'Woo'
```
2.		Directors who have directed films in more than one country?
```sql
select p.first_name, p.surname
from (select c.peopleid
      from credits c
           join movies m
           on m.movieid = c.movieid
      where c.credited_as = 'D'
      group by c.peopleid
      having count(distinct m.country) > 1) x
    join people p
      on p.peopleid = x.peopleid;
```

3.		List the cast of the American "Treasure Island" 1950 film (first name, surname, and display "Director" instead of "D" and "Actor" instead of "A"

```sql
select p.first_name, p.surname, 
       case c.credited_as
         when 'A' then 'Actor'
         when 'D' then 'Director'
       end credited_as
from movies m
     join credits c
       on c.movieid = m.movieid
     join people p
       on p.peopleid = c.peopleid
where m.title = 'Treasure Island' -- Beware, for strict comparison SQLite
 and m.country = 'us'            -- IS case sensitive
  and m.year_released = 1950;
```
4.	What is the most common surname in the database, and how many times can we find it?
```sql
select b.surname, b.occurrences
from (select max(cnt) maxcnt
      from (select surname, count(*) cnt 
            from people 
            group by surname)) a
     join (select surname, count(*) occurrences
           from people
           group by surname) b
       on b.occurrences = a.maxcnt;
```
mportant note: when a query appears several times (as the query that counts the number of occurrences of each surname), it's possible to factorize it. "Factorizing" is the operation you perform in mathematics when you say for instance that 
x^2 + x - 2 =  (x -1) (x + 2)
(the right hand side is the factorized expression, the left hand side the expanded expression).
In a similar way, you can in SQL extract a subquery that appears multiple times and give it a name using the with clause, as in the following example that is strictly equivalent to the previous one:
```sql
with name_count as
          (select surname, count(*) cnt
           from people
           group by surname)
select b.surname, b.cnt occurrences
from (select max(cnt) maxcnt
      from name_count) a
     join name_count b
       on b.cnt = a.maxcnt;
```
It's up to the optimizer to re-insert the factorized expression into an expanded query, or to run it separately only once and use its result set.

5.	Films where you can find Humphrey Bogart and Lauren Bacall playing together?

```sql
select m.title, m.country, m.year_released
from (select c.movieid
      from (select peopleid
            from people
            where (first_name = 'Humphrey'
                   and surname = 'Bogart')
               or (first_name = 'Lauren'
                   and surname = 'Bacall')) famous_couple
           join credits c
            on c.peopleid = famous_couple.peopleid
           and c.credited_as = 'A'
      group by c.movieid
      having count(*) = 2) bogart_plus_bacall
     join movies m
     on m.movieid = bogart_plus_bacall.movieid;
```
6.	How many times did John Wayne play in a John Ford film in the database?
```sql
select count(*)
from (select movieid
      from (select peopleid,
                   case surname
                     when 'Ford' then 'D'
                     else 'A'
                   end credited_as
            from people
            where first_name = 'John'
              and surname in ('Wayne', 'Ford')) wayne_ford
           join credits c
             on c.peopleid = wayne_ford.peopleid
            and c.credited_as = wayne_ford.credited_as
      group by movieid
      having count(distinct c.peopleid) = 2) by_ford_with_wayne
               -- distinct because Ford might have
               -- played AND directed and he might appear twice
               -- count(c.peopleid) >= 2 could also work
;
```
7.	List the names of the known directors of 2015 films (no need to display anything about the film). If the film is Chinese, Korean or Japanese, the name should be displayed as surname followed by first name, otherwise it must be first name followed by surname.
```sql
select distinct   -- required, there might be two films for one director
       case m.country
         when 'cn' then surname || ' ' || coalesce(first_name, ' ')
         when 'tw' then surname || ' ' || coalesce(first_name, ' ')
         when 'hk' then surname || ' ' || coalesce(first_name, ' ')
         when 'jp' then surname || ' ' || coalesce(first_name, ' ')
         when 'kr' then surname || ' ' || coalesce(first_name, ' ')
         else coalesce(first_name, ' ') || ' ' || surname
       end as director
from movies m
     join credits c
       on c.movieid = m.movieid
     join people p
       on p.peopleid = c.peopleid
where c.credited_as = 'D'
  and m.year_released = 2015;
```

8.	Display the title, year and country code for films from 2010 or later for which the name of the director is missing from the database.
```sql 
select m.title, m.year_released, m.country
from movies m
     left join credits c
       on c.movieid = m.movieid
      and c.credited_as = 'D'
where m.year_released >= 2010
  and c.movieid is null;   -- Any column from credits works

```
9.	What are the title, country and year of release of remakes in the database ? (films for which an earlier film with the same title exists)
```sql
select distinct r.title, r.country, r.year_released
from movies m  
     join movies r -- remakes
       on r.title = m.title
     and r.year_released > m.year_released
```
**distinct** is required when there is more than one remake (otherwise the third film would appear as remake of the first one and as remake of the second one).
10.	Confusion between Western and Asian names. Display the peopleids and one surname and the matching surname as well as year or birth and year of death for rows in table people where birth year and deat year (if set) are identical, and first_name and surname are swapped. They may be the same person entered twice by mistake.

```sql
select p1.peopleid,
       p2.peopleid,
       p1.first_name,
       p1.surname,
       p1.born,
       p1.died
from people p1
     join people p2
     on p2.first_name = p1.surname
     and p2.surname = p1.first_name
     and p2.born = p1.born
     and coalesce(p2.died, 0) = coalesce(p1.died, 0)
     and p2.peopleid > p1.peopleid -- to avoid duplicates
```
11.	List the people in the database who have the same first character in their first and last names. (For instance, Charlie Chaplin.)
```sql 
select d.first_name,
       d.surname,
       'directed',
       top_films.films_together,
       'films starring',
       a.first_name,
       a.surname
from (select c1.peopleid director,
             c2.peopleid actor,
             count(*) films_together
      from credits c1
           join credits c2
             on c2.movieid = c1.movieid
            and c2.peopleid <> c1.peopleid
      where c1.credited_as = 'D'
        and c2.credited_as = 'A'
      group by c1.peopleid, c2.peopleid
      having count(*) > 1) actor_director
    join (select max(films_together) as films_together
          from (select c1.peopleid director,
                       c2.peopleid actor,
                       count(*) films_together
                from credits c1
                     join credits c2
                       on c2.movieid = c1.movieid
                      and c2.peopleid <> c1.peopleid
                where c1.credited_as = 'D'
                  and c2.credited_as = 'A'
                group by c1.peopleid, c2.peopleid
                having count(*) > 1) actor_director) top_films
        on top_films.films_together = actor_director.films_together
     join people a
       on actor_director.actor = a.peopleid
     join people d
       on actor_director.director = d.peopleid
```
 
This is another case where the query that returns the number of films together of each pair could be factorized.
12.	Display first name, surname, year of death and year of their last film for actors who died more than 20 years after the last film we have with them in the database.
```sql 
select p.first_name, p.surname, a.last_film, p.died
from (select c.peopleid, max(m.year_released) last_film
      from movies m
           join credits c
             on c.movieid = m.movieid
      where credited_as = 'A'
      group by c.peopleid) a
     join people p
       on p.peopleid = a.peopleid
where p.died > 20 + a.last_film

```
13.	Marilyn Monroe appeared in how many 1952 films?
```sql 
select count(*)
from (select m.movieid
      from people p
           join credits c
             on c.peopleid = p.peopleid
           join movies m
             on m.movieid = c.movieid
      where c.credited_as = 'A'
        and p.first_name = 'Marilyn'
        and p.surname = 'Monroe'
        and m.year_released = 1952) a
```
14.	What is in the database the first film in which Jackie Chan starred? 
```sql 
select m.title, m.year_released, m.country
from (select c.peopleid, min(m.year_released) first_film_year
      from people p
           join credits c
             on c.peopleid = p.peopleid
           join movies m
             on m.movieid = c.movieid
      where c.credited_as = 'A'
        and p.first_name = 'Jackie'
        and p.surname = 'Chan') a
     join credits c
       on c.peopleid = a.peopleid
      and c.credited_as = 'A'
     join movies m
       on m.movieid = c.movieid
      and m.year_released = a.first_film_year
```
15.	List the first name and surname, as well as the number of films by Orson Welles where they appear, of all actors, other than Orson Welles himself, who played in an Orson Welles film.
```sql 
select p.first_name, p.surname, count(*) films
from (select p.peopleid ow, c.movieid
      from people p
           join credits c
             on c.peopleid = p.peopleid
      where c.credited_as = 'D'
        and p.first_name = 'Orson'
        and p.surname = 'Welles') ow_films
     join credits c
       on c.movieid = ow_films.movieid
      and c.credited_as = 'A'
      and c.peopleid <> ow_films.ow
     join people p
       on p.peopleid = c.peopleid

```
16.	Actor who directed only one film, display his or her name, film title and the year that film released. This question will be the assignment for lab 4.
