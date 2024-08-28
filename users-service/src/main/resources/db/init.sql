CREATE TABLE IF NOT EXISTS users(
    id SERIAL,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_users_username ON users (username);

ALTER SEQUENCE users_id_seq RESTART WITH 100;