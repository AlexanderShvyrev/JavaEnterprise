-- Table: public.students

-- DROP TABLE public.students;

CREATE TABLE public.students
(
    student_id bigint NOT NULL DEFAULT nextval('students_student_id_seq'::regclass),
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    birth_date timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (student_id)
)

TABLESPACE pg_default;

ALTER TABLE public.students
    OWNER to postgres;