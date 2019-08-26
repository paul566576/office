DROP DATABASE IF EXISTS main_departments;

CREATE DATABASE main_departments CHARACTER SET utf8 COLLATE utf8_bin;

USE main_departments;

CREATE TABLE departments
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE employers
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    dep_id      INT NOT NULL,
    email       VARCHAR(50) NOT NULL UNIQUE,
    employed_at Date,
                FOREIGN KEY (dep_id)
                REFERENCES departments (id)
                ON DELETE CASCADE

);

INSERT INTO departments (name)
VALUES ('dep_1'),
       ('dep_2'),
       ('dep_3'),
       ('dep_4');

INSERT INTO employers (name, dep_id, email, employed_at)
VALUES ('name_1', 1, '11@gmail.com', '2011-03-18'),
       ('name_2', 1, '22@gmail.com', '2012-03-18'),
       ('name_3', 2, '33@gmail.com', '2013-03-18'),
       ('name_4', 2, '44@gmail.com', '2014-03-18'),
       ('name_5', 2, '55@gmail.com', '2015-03-18'),
       ('name_6', 2, '66@gmail.com', '2016-03-18'),
       ('name_7', 3, '77@gmail.com', '2017-03-18'),
       ('name_8', 3, '88@gmail.com', '2018-03-18'),
       ('name_9', 3, '99@gmail.com', '2019-03-18'),
       ('name_a', 1, '00@gmail.com', '2010-03-18'),
       ('name_0', 1, '12@gmail.com', '2009-03-18');
