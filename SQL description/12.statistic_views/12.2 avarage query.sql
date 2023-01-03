--Average days difference between ticket purchase date end event date. 
--The most frequent users age. 
--Percentage of each category across all tickets. 
create view task2 AS
select 
	tickets.category,
	round(AVG(EXTRACT (DAY from events.date-tickets.purchase_date)),2) as avarage_days ,
	round(AVG(users.age),2) as avarage_age ,
	count(tickets.id) * 100/ (select count(tickets.id) from tickets) as percentage 
from tickets

JOIN users ON users.id = tickets.user_id
JOIN events ON events.id = tickets.event_id

--For category ‘ULTRA’ select only that tickets, which event does not contain special characters. 
WHERE tickets.category= 'ULTRA' AND events.title !~ '^.*[^A-Za-z0-9 .-].*$'

--For category ‘PREMIUM’ select tickets, which name contains numbers. 
OR tickets.category= 'PREMIUM' AND events.title ~'[0-9]'

--For category ‘STANDART’ check that the user age is not 20, 30, 35, 40, 50.
OR tickets.category= 'STANDART' AND users.age != 20 AND users.age != 30 AND users.age != 35
AND users.age != 40 AND users.age != 50


GROUP BY tickets.category;


