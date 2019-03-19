-- Table: empleadosrinku

-- DROP TABLE empleadosrinku;

CREATE TABLE empleadosrinku
(
  numemp integer NOT NULL,
  nombre character(20),
  apellido character(20),
  rol character(10),
  tipo character(10),
  status integer DEFAULT 1,
  CONSTRAINT empleadosrinku_pkey PRIMARY KEY (numemp)
)