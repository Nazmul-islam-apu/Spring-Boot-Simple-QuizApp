# Spring-Boot-Simple-QuizApp

I worked on this project as learning purpose. During implementing this project, I have used the following tools:
* Spring Boot
* Spring Data Jpa
* Thymleaf
* Mysql

If you want to use this prject, create a database as "quiz_database" and then add some questions. Here I am posting a sample insert query to add the data in the table. 

### Sample query to insert data into the table<br>
insert into questions(id, title, option1, option2, option3, option4, ans, chosen)<br>
values(1, 'i = 9; j =i++; What is the value of i and j now?',  '10, 10','10, 9','9, 10','None of them',2,1);
<br><br>
Insert 15-20 sample questions in the table before running the application.
<br><br>
Then enter your database username and password in the application.properties file and run the application.
