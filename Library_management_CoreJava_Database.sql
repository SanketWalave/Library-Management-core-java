Library_management_CoreJava_Database
mysql> desc user;
+---------------+--------------------------+------+-----+---------+----------------+
| Field         | Type                     | Null | Key | Default | Extra          |
+---------------+--------------------------+------+-----+---------+----------------+
| user_id       | int                      | NO   | PRI | NULL    | auto_increment |
| user_name     | varchar(100)             | YES  |     | NULL    |                |
| user_email    | varchar(200)             | YES  | UNI | NULL    |                |
| user_password | varchar(300)             | YES  |     | NULL    |                |
| role          | enum('librarian','user') | YES  |     | NULL    |                |
+---------------+--------------------------+------+-----+---------+----------------+
5 rows in set (0.02 sec)

mysql> desc books;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| book_id   | int          | NO   | PRI | NULL    | auto_increment |
| title     | varchar(200) | NO   |     | NULL    |                |
| author    | varchar(150) | NO   |     | NULL    |                |
| category  | varchar(100) | YES  |     | NULL    |                |
| available | tinyint(1)   | YES  |     | 1       |                |
+-----------+--------------+------+-----+---------+----------------+
5 rows in set (0.00 sec)

mysql> desc transactions;
+-------------+-----------------------------+------+-----+----------+----------------+
| Field       | Type                        | Null | Key | Default  | Extra          |
+-------------+-----------------------------+------+-----+----------+----------------+
| trans_id    | int                         | NO   | PRI | NULL     | auto_increment |
| book_id     | int                         | NO   | MUL | NULL     |                |
| user_id     | int                         | NO   | MUL | NULL     |                |
| borrow_date | date                        | NO   |     | NULL     |                |
| return_date | date                        | YES  |     | NULL     |                |
| status      | enum('borrowed','returned') | YES  |     | borrowed |                |
+-------------+-----------------------------+------+-----+----------+----------------+
6 rows in set (0.00 sec)