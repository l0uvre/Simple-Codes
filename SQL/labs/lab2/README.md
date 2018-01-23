
# Lab2 - Creating a SQLite "database" 

Once you have submitted your database design for Lab1, you are asked to use it again 
to improve a number of things: 

1. Set proper datatypes in MySQL Workbench 
2. Use MySQL Workbench to generate the CREATE TABLE statements. 
   For this: 
   
   Go to File/Export/Forward Engineer SQL CREATE script  
   - [ ]  Check option 'Omit Schema Qualifier in Object Names' but let all the other 
export options unchecked (a schema is a logical subset and is irrelevant in our 
case). Enter the name of the script that you want to generate; it will have the 
".sql" extension (for instance: myscript.sql or lab2.sql). Click on "Continue". 

 
 - [ ] On the next screen, only "Export MySQL Table objects" should be checked. 
Click on "Continue". 
 - [ ]  Don't worry about reviewing the script. It contains a lot of statements that only 
MySQL understands, including the ENGINE = option of CREATE TABLE 
statements. All database management system products have options that 
specify how data is physically stored in them (this will be discussed far later in 
the course). Click on "Finish". 

We want to use the generated script to create the tables in SQLite. This will require a 
bit of work. Once you have generated the script, edit it and remove manually 
everything that only MySQL understands: 

1.  Remove every backquote. Names between backquotes are case sensitive and 
can contain spaces. Backquotes aren't needed and not understood by other 
products (your editor should be able to suppress all the backquotes at once). 
2.  Remove all rows that start wit SET. They specify MySQL options. 
3.  Remove all lines that start with ENGINE	=  (there is one by CREATE	TABLE	
statement). 
4.  You need to replace every UNIQUE	INDEX	name with CONSTRAINT	name	
UNIQUE. 
5.  You need to remove every line that starts with INDEX. 
6.  Unfortunately, SQLite doesn't understand COMMENT (and other products that 
do understand comments generally use a different syntax and a separate 
statement). 

You need to replace every COMMENT by what indicates a comment in a script: 
```sql
--	This is	a comment
```

As with // in Java, the comment extends to the end of the line. Beware that with a 
comment on a column 
```sql
colname int	not null comment 'column comment',
```
you have to move the comma BEFORE the comment, thus: 
```sql
colname int not	null, -- 'column comment'	
```
	
(you can remove the quotes but it's not important) and for a column on a table the 
semi-colon (;) that terminates the statement must be moved to the previous or next 
line: 

```sql
...)	
COMMENT = 'Table comment';
```

thus
```sql
...)	
-- 'Table comment'	
;	
```

At this stage, you script can be run in SQLite; however, you can improve it and add 
CHECK constraints (that MySQL accepts in CREATE statements but ignores). 

Candidates for CHECK constraints are columns: 

- [ ] For which you want to make sure that data is always stored all in upper case or 
all in lower case. It's better for codes than names, because people usually want 
names to be capitalized in a peculiar way. However, Americans expect the 
code for Texas to be TX, not tx. 

- [ ] For which there is a particular range of values that is acceptable. For instance, 
if you have latitudes and longitudes, they must belong to a certain range. If 
you store an offset to GMT (time zone), it must also belong to a certain range. 
A population cannot be negative, and so forth. 

There are two ways to write a CHECK condition: 
- either simply as CHECK	(condition) as shown in the lecture slides, 
- or as CONSTRAINT	constraint_name	CHECK	(condition) 

The second option is a bit long for slides but better because when you violate a 
constraint the name usually appears in the error message; if you give a name, it can be 
meaningful (otherwise a name will be automatically given by the system). 

If you have installed SQLite from sqlite.org, you just need to type 
```bash
sqlite3	dbname.sqlite < lab2.sql	
```
Where dbname.sqlite is the name you give to your database file (can be anything). 
If you get error messages, remove the file that was created, fix the .sql script and retry. 

