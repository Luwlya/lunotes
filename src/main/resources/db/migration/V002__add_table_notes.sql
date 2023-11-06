CREATE TABLE notes
(
    id              UUID PRIMARY KEY NOT NULL,
    author_id       UUID             NOT NULL REFERENCES accounts (id),
    title           TEXT,
    text            TEXT,
    created_at      TIMESTAMPTZ      NOT NULL,
    updated_at      TIMESTAMPTZ      NOT NULL,
    note_visibility TEXT             NOT NULL,
    tags            TEXT[]           NOT NULL
);