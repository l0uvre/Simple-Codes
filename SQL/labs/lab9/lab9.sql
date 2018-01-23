--
drop trigger people_trg on people;
drop table people_audit;
create table people_audit(auditid         serial,
                          peopleid        int,
                          type_of_change  char(1),
                          changes 		  json,
                          changed_by      varchar(100),
                          time_changed    timestamp);
create or replace function people_audit_fn()
returns trigger
as
$$
begin
  if tg_op = 'UPDATE'
  then
    insert into people_audit(peopleid,
                             type_of_change,
                             changes,
                             changed_by,
                             time_changed)
    select peopleid, 'U', changes,
           current_user||'@'
               || coalesce(cast(inet_client_addr() as varchar),
                                'localhost'),
           current_timestamp
		   FROM (select peopleid, CAST('{'||string_agg(strings, ', ')||'}' AS JSON) changes
			   from (select old.peopleid, '"first_name":["' || coalesce(old.first_name, 'null') ||'", "' || new.first_name||'"]' AS strings
					  where coalesce(old.first_name, '*') <> coalesce(new.first_name, '*')
					  union all
					  select old.peopleid, '"surname":["' ||old.surname ||'", "' || new.surname||'"]' AS strings
							 --json_object('surname', array_agg(old.surname, new.surname)) AS change_json
					  where old.surname <> new.surname
					  union all
					  select old.peopleid, '"born":[' || cast(old.born as varchar) ||', ' || cast(new.born as varchar) ||']' AS strings
							 --json_object('born', array_agg(old.born, new.born)) AS change_json
					  where old.born <> new.born
					  union all
					  select old.peopleid, '"died":[' || coalesce(cast(old.died as varchar), 'null') ||', ' || cast(new.died as varchar) ||']' AS strings
							--json_object('died', array_agg(old.died, new.died)) AS change_json
					  where coalesce(old.died, -1) <> coalesce(new.died, -1)) modified
					  GROUP BY peopleid) json_utable;
  elsif tg_op = 'INSERT' then
    insert into people_audit(peopleid,
                             type_of_change,
							 changes,
                             changed_by,
                             time_changed)
    select peopleid, 'I', changes,  
           current_user||'@'
               || coalesce(cast(inet_client_addr() as varchar),
                                'localhost'),
           current_timestamp
	FROM (SELECT peopleid, CAST('{'||string_agg(strings, ', ')||'}' AS JSON) changes 
		from (select new.peopleid, '"first_name":"' || new.first_name||'"' AS strings
					 --json_object('first_name', new.first_name) AS changes
			  where new.first_name is not null
			  union all
			  select new.peopleid, '"surname":"' || new.surname||'"' AS strings
					 --json_object('surname', new.surname) AS changes
			  union all
			  select new.peopleid, '"born":"' || cast(new.born AS VARCHAR)||'"' AS strings
					 --json_object('born', cast(new.born AS VARCHAR)) AS changes
			  union all
			  select new.peopleid, '"died":"' || cast(new.died AS VARCHAR)||'"' AS strings
					 --json_object('died', cast(new.died AS VARCHAR)) AS changes
			  where new.died is not null) inserted
			  GROUP BY peopleid) json_itable;
  else  
    insert into people_audit(peopleid,
                             type_of_change,
							 changes,
                             changed_by,
                             time_changed)
    select peopleid, 'D', changes,
           current_user||'@'
               || coalesce(cast(inet_client_addr() as varchar),
                                'localhost'),
           current_timestamp
	FROM (SELECT peopleid, CAST('{'||string_agg(strings, ', ')||'}' AS JSON) changes
    from (select old.peopleid,
				'"first_name":"' || old.first_name||'"' AS strings
				--json_object('first_name', old.first_name) AS changes
          where old.first_name is not null
          union all
          select old.peopleid, '"surname":"' || old.surname||'"' AS strings
				--json_object('surname', old.surname) AS changes
          union all
          select old.peopleid, '"born":"' || cast(old.born AS VARCHAR)||'"' AS strings
				--json_object('born', cast(old.born as varchar)) AS changes
          union all
          select old.peopleid, '"died":"' || cast(old.died AS VARCHAR)||'"' AS strings
				--json_object('died', cast(old.died as varchar)) AS changes
          where old.died is not null) deleted
		  GROUP BY peopleid)json_dtable;
  end if;
  return null;
end;
$$ language plpgsql;
create trigger people_trg
after insert or update or delete on people
for each row
execute procedure people_audit_fn();
--