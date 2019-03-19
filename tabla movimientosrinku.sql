-- Table: movimientosrinku

-- DROP TABLE movimientosrinku;

CREATE TABLE movimientosrinku
(
  numemp integer,
  nombre character(20),
  rol character(10),
  tipo character(10),
  fecha date,
  cantidadentregas integer,
  cubrioturno integer,
  id serial NOT NULL,
  CONSTRAINT movimientosrinku_pkey PRIMARY KEY (id)
)