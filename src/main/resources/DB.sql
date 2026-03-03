CREATE DATABASE student_db;
USE student_db;

Create table User(
                     Id int auto_increment primary key,
                     Name varchar(50),
                     Password varchar(1000)
);

Create table Student(
                        Id int auto_increment primary key,
                        firstName varchar(50),
                        lastName varchar(50),
                        email varchar(200),
                        dateOfBirth date,
                        enrollmentDate date


);