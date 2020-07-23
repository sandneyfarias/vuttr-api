CREATE TABLE public.tag
(
    tag_id serial NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT tag_pkey PRIMARY KEY (tag_id),
    CONSTRAINT uk_1wdpsed5kna2y38hnbgrnhi5b UNIQUE (name)

)
