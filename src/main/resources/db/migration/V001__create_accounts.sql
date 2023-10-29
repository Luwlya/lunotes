CREATE TABLE accounts
(
    id            UUID PRIMARY KEY NOT NULL,
    name          TEXT             NOT NULL,
    email         TEXT UNIQUE      NOT NULL,
    password_hash TEXT             NOT NULL,
    created_at    TIMESTAMPTZ      NOT NULL,
    updated_at    TIMESTAMPTZ      NOT NULL,
    status        TEXT             NOT NULL
);