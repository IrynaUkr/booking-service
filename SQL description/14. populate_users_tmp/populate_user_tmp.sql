CREATE PROCEDURE populate_users_tmp(amount_rows int)
LANGUAGE plpgsql 

AS $$

BEGIN
CREATE TABLE public.users_tmp
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
	CHECK(email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    CHECK (age > 0 AND age < 50)
);

WHILE amount_rows>0 LOOP

INSERT into public.users_tmp (username, first_name, last_name, age, email) VALUES
('user name','first name','last name',42, 'user@gmail.com'); 


amount_rows = amount_rows-1;

END LOOP;

END; $$
