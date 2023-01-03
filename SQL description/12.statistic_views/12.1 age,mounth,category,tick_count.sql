--1 view gives the aggregated information: 
--month, category, age, tickets count
create view task1 AS
SELECT
	tickets.category,
	EXTRACT (MONTH from events.date) AS month,
	users.age,
	count(tickets.id)
	
FROM tickets
JOIN events ON events.id = tickets.event_id
JOIN users ON users.id=tickets.user_id
GROUP BY category, users.age,EXTRACT (MONTH from events.date)
;



