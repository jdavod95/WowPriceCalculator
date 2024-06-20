DROP TABLE IF EXISTS resource;
DROP TABLE IF EXISTS sale;

CREATE SEQUENCE resource_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE sale_id_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE resource (
    id bigint SEQUENCE PUBLIC.resource_id_seq NOT NULL,
    name text,
    on_stock int,
    CONSTRAINT resource_pkey PRIMARY KEY (id)
);

CREATE TABLE sale (
    id bigint SEQUENCE PUBLIC.sale_id_seq NOT NULL,
    date date,
    resource_id int,
    amount int,
    cost int,
    CONSTRAINT sale_pkey PRIMARY KEY (id),
    CONSTRAINT fk_resource FOREIGN KEY (resource_id) REFERENCES resource(id)
);
