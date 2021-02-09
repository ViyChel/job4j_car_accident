CREATE TABLE types
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE rules
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE accident
(
    id      serial primary key,
    name    varchar(2000),
    text    varchar(2000),
    address varchar(2000),
    type_id int not null references types (id)
);

CREATE TABLE accident_rules
(
    accident_id int REFERENCES accident (id),
    rule_id     int REFERENCES rules (id)
);