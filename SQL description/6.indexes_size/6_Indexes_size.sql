--. The pg_indexes_size() function takes in the table name
-- or respective OID and returns the size of all the attached indexes from a table.
-- The pg_indexes_size() function is used to get the total size of all indexes attached to a table.

SELECT pg_indexes_size('users');

SELECT pg_indexes_size('tickets');

--DROP INDEX idx_tickets_event_id;
--DROP INDEX idx_tickets_user_id;
-- DROP INDEX idx_users_email;
-- DROP INDEX idx_users_username;