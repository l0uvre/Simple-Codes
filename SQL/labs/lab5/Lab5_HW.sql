--Alfred Hitchcock's favorite actor or actress.
SELECT MAX(times), f_act.peopleid, f_act.first_name, f_act.surname 
From (SELECT COUNT(*)times, c.peopleid, p.first_name, p.surname 
	FROM (SELECT c.movieid FROM credits c
			JOIN people p ON c.peopleid = p.peopleid AND p.first_name = "Alfred" AND p.surname = "Hitchcock" 
			WHERE c.credited_as = 'D') H_moive
		JOIN credits c ON H_moive.movieid = c.movieid AND c.credited_as = 'A'
		JOIN people p ON c.peopleid = p.peopleid
		GROUP BY c.peopleid
		ORDER BY times) f_act;
			  