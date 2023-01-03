CREATE PROCEDURE populate_tables(amount_rows int)
LANGUAGE plpgsql 

AS $$

BEGIN

WHILE amount_rows>0 LOOP

INSERT into public.users (username, first_name, last_name, age, email) VALUES
('user name','first name','last name',42, 'user@gmail.com'); 

INSERT INTO public.events(title, date) VALUES
(amount_rows, current_timestamp+ interval '10 days');

INSERT INTO public.tickets(event_id, user_id, category, place, cinema_name,cinema_phone,cinema_facilities, purchase_date)VALUES
(1, 1, 'STANDART',15,'cinema name','{{098-123-45-67},{067-987-65-43}}','{{cafe},{parking}}',current_timestamp);

amount_rows = amount_rows-1;

END LOOP;

END; $$

