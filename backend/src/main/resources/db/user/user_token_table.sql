--liquibase formatted sql
CREATE SEQUENCE s_user_tokens_id START WITH 100000 INCREMENT BY 10 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE TABLE user_tokens
(
    user_id  BIGINT NOT NULL,
    token_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, token_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES "app_user" (id) ON DELETE CASCADE,
    CONSTRAINT fk_token FOREIGN KEY (token_id) REFERENCES token (id) ON DELETE CASCADE
);

