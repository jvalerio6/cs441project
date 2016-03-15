# SQLite

### Install
For win, download: https://www.sqlite.org/2016/sqlite-tools-win32-x86-3110100.zip. Drop the *sqlite3.exe* file into windows/system32 folder.

For visual interface, I use sqlite studio: http://sqlitestudio.pl/?act=download

### SQLite shell 
[Field type reference](https://www.sqlite.org/datatype3.html)
In terminal: >sqlite3

Create db
```
sqlite3 test.db
```

Create a user table
```
sqlite> create table user(id integer primary key, name text, profile text);
```

Create a task table
```
sqlite> create table task(id integer primary key, date_created text, date_last_mod text, 
title text, content text, user_id integer, foreign key(user_id) references user(id));
```

Insert value 
``` 
sqlite> insert into user(name) values('will');
sqlite> insert into user(id, name) values(2, 'ricky');
sqlite> insert into user(name) values('eric');

sqlite> INSERT INTO task (date_created, date_last_mod, title, content, user_id) VALUES (date('now'), date('now'), 'create uml', 'bla bla bla bla ........................... bla', 2 );
sqlite> INSERT INTO task (date_created, date_last_mod, title, content, user_id) VALUES (date('now'), date('now'), 'prepare for midterm', 'la bla bla bla ........................... bla', 1 );
sqlite> INSERT INTO task (date_created, date_last_mod, title, content, user_id) VALUES (date('now'), date('now'),'buy toliet paper ', 'yeahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh ........................... lalallalalla ', 1 );
```

Display table
```
sqlite> .mode column
sqlite> .headers on

sqlite> select * from user;

// result:
id          name        profile
----------  ----------  ----------
1           will
2           ricky
```

Output file
```
sqlite> .mode csv
sqlite> .output ./output.csv
sqlite> select * from user;
sqlite> .output stdout
```


Select all task relates to certain user from two tables using 'where'

```
sqlite> .mode column
sqlite> .headers on
sqlite> select name, date_created, title, content from user, task where user.id = user_id and name = 'will';
name        date_created  title                content
----------  ------------  -------------------  ----------------------------------------------
will        2016-03-15    prepare for midterm  la bla bla bla ........................... bla
will        2016-03-15    buy toliet paper     yeahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh ..
```
