DROP TABLE IF EXISTS leilao CASCADE;
DROP SEQUENCE IF EXISTS seq_leilao;

CREATE SEQUENCE seq_leilao INCREMENT 1 MINVALUE 1 MAXVALUE 9999999 START 1 CACHE 1;
CREATE TABLE leilao (
	id INTEGER,
	codigo INTEGER,
	descricao VARCHAR(60),
	vendedor INTEGER,
	inicioPrevisto TIMESTAMP,
	createdAt TIMESTAMP,
	updatedAt TIMESTAMP
);

ALTER TABLE leilao ADD CONSTRAINT leilao_pk PRIMARY KEY (id);

ALTER TABLE leilao ALTER COLUMN descricao SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN vendedor SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN inicioPrevisto SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN createdAt SET NOT NULL;

ALTER TABLE leilao ALTER COLUMN updatedAt SET NOT NULL;