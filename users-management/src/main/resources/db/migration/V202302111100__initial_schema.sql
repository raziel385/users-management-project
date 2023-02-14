CREATE SCHEMA IF NOT EXISTS users_management;

CREATE TABLE IF NOT EXISTS users_management.user (

    id varchar(255) NOT NULL PRIMARY KEY,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    created_on datetime, 
    deleted_on datetime,
    updated_on datetime

) DEFAULT CHARSET=UTF8MB4;