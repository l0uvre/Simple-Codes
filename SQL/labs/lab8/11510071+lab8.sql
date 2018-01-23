CREATE FUNCTION merge_people(pid1 INT, pid2 INT)
RETURNS void
AS $$
DECLARE
   v_create_cmd  varchar(100);
   n_rowcount             int; 
   p_count 				  int;
BEGIN

  if pid1 = pid2 THEN
    raise exception 'pid1 shouldn''t be equal to pid2'; 
  END if;
  
  v_create_cmd := 'CREATE table multiple_credits (movieid int, peopleid int, credited_as char)';
  EXECUTE v_create_cmd;
  
  SELECT COUNT(p.peopleid)
	INTO p_count
	FROM (SELECT peopleid FROM people WHERE peopleid = pid1) p;
  IF p_count = 0
  THEN
    RAISE EXCEPTION 'pid1 doesn''t exists'; 
  END IF;
	
  SELECT COUNT(p.peopleid)
	INTO p_count
	FROM (SELECT peopleid FROM people WHERE peopleid = pid2)p;
  IF p_count = 0
  THEN
    RAISE EXCEPTION 'pid2 doesn''t exists'; 
  END IF;
  
  INSERT INTO multiple_credits(movieid, peopleid, credited_as)
  SELECT c.movieid, c.peopleid, c.credited_as
  FROM credits c
  WHERE c.peopleid = pid2 AND 
	EXISTS (SELECT NULL
			FROM credits c2
			WHERE c2.movieid = c.movieid
			  AND c2.credited_as = c.credited_as
			  AND c2.peopleid = pid1)
  ; 
  GET DIAGNOSTICS n_rowcount = ROW_COUNT;
  
  
  
  DELETE FROM credits
  WHERE (movieid, peopleid, credited_as) IN
     (SELECT movieid, peopleid, credited_as
      FROM multiple_credits)
  ;
  
  v_create_cmd := 'drop table multiple_credits';
  EXECUTE v_create_cmd;
  
  UPDATE credits
  SET peopleid = pid1 WHERE peopleid = pid2;
  
  DELETE FROM people WHERE peopleid = pid2;
  
  
END;
$$ language plpgsql;
///
