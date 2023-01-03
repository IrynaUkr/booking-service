CREATE OR REPLACE FUNCTION update_current_date() 
RETURNS trigger
   LANGUAGE plpgsql AS
$$BEGIN 
NEW.update_date = now();
    RETURN NEW;
END;$$;

CREATE TRIGGER update_date 
   BEFORE UPDATE ON users FOR EACH ROW 
   EXECUTE PROCEDURE update_current_date();
   