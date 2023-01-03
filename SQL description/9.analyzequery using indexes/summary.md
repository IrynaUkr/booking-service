#### index size before filling 100000 rows:

* table users   65536 bigint
* table tickets 49152 bigint

#### index size after filling 100000 rows:

* table users   9199616 bigint
* table tickets 3686400 bigint

#### Table “users”:

* find user by username (exact match).

EXPLAIN ANALYZE
SELECT * FROM users WHERE username='administrator';

_execution time without index 30.347ms_
_execution time without index 0.285ms_
* find user by part of email (partial match).

EXPLAIN ANALYZE
SELECT * FROM users WHERE email LIKE '%@company.com';

_execution time without index 35.247ms_
_execution time with hash  index 41.801ms_
--hash index was deleted and b-tree was used
_execution time with hash  index 24.643ms_
b-tree is faster

#### Table “tickets”:

* Find record by event_id and user_id.

EXPLAIN ANALYZE
SELECT * FROM tickets WHERE user_id=100008;

_execution time without index 35.747ms_
_execution time with    index 39.329ms_

EXPLAIN ANALYZE
SELECT * FROM tickets WHERE event_id=100064;

  _execution time without index 33.660ms_
  _execution time with    index 0.521ms_

* Find record by category in (…).

EXPLAIN ANALYZE
SELECT * FROM tickets WHERE category='STANDART';

_execution time without index 105.517ms_
_execution time with index    25.491ms_