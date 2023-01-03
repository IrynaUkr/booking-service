--Indexes for  table “users” will be used for searching by username or by email; 

--username is not unique according to the task requirement.
--The column has a large number of possible values.
--The best solution for the username is the b-tree index

CREATE INDEX idx_users_username ON users(username);

--Email is a good candidate for hash index, since the email should be unique to each person.

CREATE INDEX idx_users_email ON users USING HASH (email);


--Indexes for table “tickets” will be used for searching by event_id and user_id).
--The column has a large number of possible values.
--The best solution for the event_id and user_id is the b-tree index

CREATE INDEX idx_tickets_event_id ON tickets(event_id);

CREATE INDEX idx_tickets_user_id ON tickets(user_id);

