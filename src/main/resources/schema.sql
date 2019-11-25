CREATE TABLE products (
  id            INTEGER PRIMARY KEY GENERATED ALWAYS as IDENTITY,
  description  VARCHAR(64) NOT NULL,
  name         VARCHAR(50) NOT NULL,
  company       VARCHAR (10) NOT NULL,
  price         FLOAT NOT NULL,
  type         VARCHAR(15) NOT NULL,
  quantity      INTEGER NOT NULL
  );