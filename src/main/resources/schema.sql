-- Table: public.product

DROP TABLE IF EXISTS public.product;

DROP SEQUENCE IF EXISTS public.product_id_seq;

CREATE SEQUENCE public.product_id_seq
    INCREMENT 1
    START 100000
    MINVALUE 100000
    MAXVALUE 99999999999999
	CYCLE;

CREATE TABLE public.product
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    price numeric,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.product ALTER COLUMN id SET DEFAULT nextval('product_id_seq');

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;

ALTER TABLE public.product OWNER to postgres;