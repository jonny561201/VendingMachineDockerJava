CREATE DATABASE vending_machine;

\connect vending_machine;

CREATE TABLE products (
    ID serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    cost money NOT NULL
);