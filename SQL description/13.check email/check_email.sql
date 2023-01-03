
ALTER TABLE IF EXISTS public.users
    ADD CONSTRAINT users_email_check CHECK (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
	
	
	