CREATE OR REPLACE FUNCTION create_current_date() 
RETURNS trigger
   LANGUAGE plpgsql AS
$$BEGIN 
NEW.create_date = now();
    RETURN NEW;
END;$$;

CREATE TRIGGER create_date_users 
   BEFORE INSERT ON users FOR EACH ROW 
   EXECUTE PROCEDURE create_current_date();
   
CREATE TRIGGER create_date_events 
   BEFORE INSERT ON events FOR EACH ROW 
   EXECUTE PROCEDURE create_current_date();  
   
CREATE TRIGGER create_date_tickets 
   BEFORE INSERT ON tickets FOR EACH ROW 
   EXECUTE PROCEDURE create_current_date();
   