CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
CREATE TABLE Person (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);
