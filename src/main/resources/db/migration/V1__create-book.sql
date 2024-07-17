CREATE schema if NOT EXISTS devDb;
CREATE TABLE IF NOT EXISTS devDb.book
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title  VARCHAR(255) NOT NULL,
    price NUMBER NOT NULL DEFAULT 0
);