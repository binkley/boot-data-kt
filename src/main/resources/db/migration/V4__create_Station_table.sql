CREATE TABLE Station
(
  id       BIGINT PRIMARY KEY,
  name     VARCHAR(100) NOT NULL UNIQUE,
  table_id BIGINT       NOT NULL REFERENCES Table(id)
);
