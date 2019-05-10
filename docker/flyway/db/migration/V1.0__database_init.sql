CREATE TABLE products (
    ID serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    location VARCHAR(50) NOT NULL,
    cost money NOT NULL
);

INSERT INTO products (name, location, cost) values
('Funyuns', 'A1', 0.75),
('Pepsi', 'B2', 1.25),
('Twix', 'C3', 0.75),
('Twizzlers', 'D5', 0.75),
('Big Red', 'A3', 0.55);