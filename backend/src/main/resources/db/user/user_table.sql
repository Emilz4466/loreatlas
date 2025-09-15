--liquibase formatted sql

CREATE SEQUENCE s_user_id
    START WITH 100000
    INCREMENT BY 10
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE app_user
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('s_user_id'),
    username     VARCHAR(255) NOT NULL,
    email        VARCHAR(60) UNIQUE NOT NULL CHECK (
        email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'
),
    password     VARCHAR(255) NOT NULL,
    role         VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    created_by   BIGINT,
    modified_by  BIGINT,
    CONSTRAINT fk_user_created_by FOREIGN KEY (created_by) REFERENCES app_user(id),
    CONSTRAINT fk_user_modified_by FOREIGN KEY (modified_by) REFERENCES app_user(id)
);