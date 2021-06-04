-- Table: public.students

-- DROP TABLE public.students;
CREATE SEQUENCE students_student_id_seq START 1;
CREATE TABLE public.students
(
    student_id bigint NOT NULL DEFAULT nextval('students_student_id_seq'::regclass),
    first_name text COLLATE pg_catalog."default" NOT NULL,
    last_name text COLLATE pg_catalog."default" NOT NULL,
    birth_date timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (student_id)
)
