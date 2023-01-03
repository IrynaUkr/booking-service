CREATE DATABASE cinema
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
	
CREATE TABLE public.users
(
    id serial NOT NULL,
    username character varying(50) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),
    age numeric,
    email character varying(50),
    create_date timestamp DEFAULT  Current_TIMESTAMP,
    update_date timestamp without time zone,
    PRIMARY KEY (id),
    CHECK (age > 0 AND age < 130)
);

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;	
	
	
CREATE TABLE public.events
(
    id serial NOT NULL,
    title character varying(200) NOT NULL,
    date timestamp without time zone,
    create_date timestamp DEFAULT  Current_TIMESTAMP,
    update_date timestamp without time zone,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.events
    OWNER to postgres;	
	
	
CREATE TABLE public.tickets
(
    id serial NOT NULL,
    event_id integer NOT NULL,
    user_id integer NOT NULL,
    category character varying NOT NULL,
    place integer,
    cinema_name character varying NOT NULL,
    cinema_phone character varying[],
    cinema_facilities character varying[],
    purchase_date timestamp without time zone,
    create_date timestamp DEFAULT  Current_TIMESTAMP,
    update_date timestamp without time zone,
    PRIMARY KEY (id),
    CONSTRAINT tickets_event_fk FOREIGN KEY (event_id)
        REFERENCES public.events (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT tickets_user_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CHECK (category IN ( 'ULTRA' , 'STANDART', 'PREMIUM'))
);

ALTER TABLE IF EXISTS public.tickets
    OWNER to postgres;
	
	


	