
SELECT DISTINCT p.first_name, p.surname, m.title, m.year_released
FROM (SELECT peopleid, movieid FROM credits c WHERE c.credited_as = 'D'
GROUP BY peopleid
HAVING COUNT(*) = 1
)directors
JOIN(SELECT peopleid FROM credits c WHERE c.credited_as = 'A') actors on actors.peopleid = directors.peopleid
JOIN people p on p.peopleid = directors.peopleid
JOIN movies m on m.movieid = directors.movieid
ORDER BY m.year_released;