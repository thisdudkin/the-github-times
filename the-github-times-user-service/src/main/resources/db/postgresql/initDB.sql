-- Database: users_db

-- Table: users
CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL       NOT NULL,
    username VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_users_username ON users (username);
CREATE INDEX IF NOT EXISTS idx_users_email ON users (email);

ALTER SEQUENCE users_id_seq RESTART WITH 100;


-- Table: profiles
CREATE TABLE IF NOT EXISTS profiles
(
    id          SERIAL,
    user_id     BIGINT        NOT NULL,
    full_name   VARCHAR(255)  NOT NULL,
    bio         VARCHAR(8192) NOT NULL,
    location    VARCHAR(255)  NOT NULL,
    birth_date  DATE          NOT NULL,
    website_url VARCHAR(255),
    created_utc TIMESTAMP     NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT pk_profiles PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_profiles_user_id ON profiles (user_id);

ALTER SEQUENCE profiles_id_seq RESTART WITH 100;


-- Table: user_roles
CREATE TABLE IF NOT EXISTS user_roles
(
    user_id   INT          NOT NULL,
    user_role VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, user_role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
