CREATE EXTENSION pgcrypto;

CREATE TABLE owner (
  id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name TEXT NOT NULL,
  age  INT  NOT NULL
);

CREATE TABLE pet (
  id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name     TEXT NOT NULL,
  owner_id INT  NOT NULL REFERENCES owner (id),
  type     TEXT NOT NULL
);
