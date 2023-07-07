CREATE TABLE IF NOT EXISTS app.votes
(
    id bigserial,
    about text,
    PRIMARY KEY (id)
);

DELETE FROM app.votes;

INSERT INTO  app.votes(about)
VALUES ('secondval');