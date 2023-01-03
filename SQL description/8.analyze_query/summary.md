#### execution time:
#### Table “users”:

* find user by username (exact match).

EXPLAIN ANALYZE
SELECT * FROM users WHERE username='administrator';

_execution time without index 30.347ms_
* find user by part of email (partial match).

EXPLAIN ANALYZE
SELECT * FROM users WHERE email LIKE '%@company.com';

_execution time without index 35.247ms_

#### Table “tickets”:

* Find record by event_id and user_id.

EXPLAIN ANALYZE
SELECT * FROM tickets WHERE user_id=100008;

_execution time without index 35.747ms_

EXPLAIN ANALYZE
SELECT * FROM tickets WHERE event_id=100064;

  _execution time without index 33.660ms_

* Find record by category in (…).
* 
EXPLAIN ANALYZE
SELECT * FROM tickets WHERE category='STANDART';

_execution time without index 105.517ms_
