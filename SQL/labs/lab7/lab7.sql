
CREATE FUNCTION arrival_time(f_time VARCHAR, f_mins INT, d_mins INT)
RETURNS VARCHAR
AS $$
DECLARE 
hour INT;
minute INT;
t_minutes INT;
days INT;
BEGIN
	hour := CAST(SUBSTRING(f_time from 1 for 2) AS INT);
	minute := CAST(SUBSTRING(f_time from 4 for 2) AS INT);
	t_minutes := hour * 60 + minute + f_mins + d_mins;
	days := 0;
	IF t_minutes >= 1440 THEN
		t_minutes := t_minutes - 1440;
		days := days + 1;
		WHILE t_minutes >= 1440 LOOP
			t_minutes := t_minutes - 1440;
			days := days + 1;
		END LOOP;
		RETURN TRIM(TO_CHAR(TRUNC(t_minutes/60), '00')) || ':'
				|| TRIM(to_char(t_minutes % 60, '00')) || '+' || CAST(days AS VARCHAR);
	ELSIF t_minutes < 0 THEN
		t_minutes := 1440 + t_minutes;
		days := days + 1;
		WHILE t_minutes < 0 LOOP
			t_minutes := 1440 + t_minutes;
			days := days + 1;
		END LOOP;
		RETURN TRIM(TO_CHAR(TRUNC(t_minutes/60), '00')) || ':'
				|| TRIM(to_char(t_minutes % 60, '00')) || '-' || CAST(days AS VARCHAR);
	ELSE
		RETURN TRIM(TO_CHAR(TRUNC(t_minutes/60), '00')) || ':'
				|| TRIM(to_char(t_minutes % 60, '00'));
	END IF;
END;
$$ language plpgsql;

---1234 7212
