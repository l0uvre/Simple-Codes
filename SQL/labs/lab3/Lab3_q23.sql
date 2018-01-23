SELECT MAX(times) FROM (SELECT surname, COUNT(*) times
      FROM people
      GROUP BY surname) surname_times;