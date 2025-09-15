--liquibase formatted sql

CREATE SEQUENCE s_token_id
    START WITH 100000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE CACHE 1;

CREATE TABLE token
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('s_token_id'),
    token      VARCHAR(255) UNIQUE                 NOT NULL,
    token_type VARCHAR(255)       DEFAULT 'BEARER' NOT NULL,
    revoked    BOOLEAN            DEFAULT FALSE,
    expired    BOOLEAN            DEFAULT FALSE,
    user_id    BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "app_user" (id)
);
