----Lab6
create table if not exists fix(peopleid  int not null,
ok_peopleid int not null,
first_name  varchar(30),
surname     varchar(30) not null);
				 
insert into fix(peopleid, ok_peopleid, first_name, surname)
select p.peopleid, x.ok_peopleid, p.first_name, p.surname
from (select max(peopleid) as ok_peopleid,
             trim(coalesce(first_name, ' ')) as first_name,
             trim(surname) as surname,born
      from people
      group by trim(coalesce(first_name, ' ')),trim(surname)
      having count(*) > 1) x
     join people p
       on trim(coalesce(p.first_name, ' ')) = x.first_name
      and  trim(p.surname) = x.surname
      and p.peopleid <> x.ok_peopleid
      and p.born = x.born;
	  
	  
DELETE FROM credits 
WHERE movieid IN(
SELECT w.movieid FROM
(SELECT c.movieid, c.peopleid FROM credits c
JOIN fix f ON c.peopleid = f.peopleid) r 
JOIN 
(SELECT c.movieid, c.peopleid FROM credits c
JOIN fix f ON c.peopleid = f.ok_peopleid) w
ON r.movieid = w.movieid) 
AND 
peopleid IN 
(SELECT w.peopleid FROM
(SELECT c.movieid, c.peopleid FROM credits c
JOIN fix f ON c.peopleid = f.peopleid) r 
JOIN 
(SELECT c.movieid, c.peopleid FROM credits c
JOIN fix f ON c.peopleid = f.ok_peopleid) w
ON r.movieid = w.movieid);



UPDATE credits SET peopleid = (SELECT f.peopleid FROM fix f WHERE credits.peopleid = f.ok_peopleid)
WHERE peopleid IN (SELECT f.ok_peopleid FROM fix f);


DELETE FROM people
WHERE peopleid IN 
(
SELECT f.ok_peopleid FROM fix f
);

UPDATE people SET first_name = TRIM(first_name), surname = TRIM(surname)
WHERE peopleid IN 
(
SELECT f.peopleid FROM fix f
);

DROP TABLE fix;