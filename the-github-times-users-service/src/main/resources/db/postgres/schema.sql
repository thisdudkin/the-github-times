--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS users
(
    id          serial       NOT NULL PRIMARY KEY,
    username    varchar(128) NOT NULL,
    email       varchar(128) NOT NULL,
    password    varchar(128) NOT NULL,
    created_utc timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_utc timestamp    NULL
);

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id   bigint      NOT NULL,
    user_role varchar(50) NOT NULL,
    PRIMARY KEY (user_id, user_role),
    CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

--
-- Name: users_id_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX IF NOT EXISTS users_id_idx ON users USING btree (id);
