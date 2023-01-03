## Postgres views


A Postgres **view** is a virtual table in Postgres.

It represents the result of a query to one or more underlying tables in Postgres.

Views are used to simplify complex queries since these queries are defined once in the view, 
and can then be directly queried via the same.

###### Standard views

Standard views represent the result of a query without actually storing data.
The view is not physically materialized. Instead, the query is run every time the view is referenced in a query.
CREATE VIEW name_view AS
SELECT

https://www.postgresql.org/docs/current/sql-createview.html

###### Materialized views

Compared to the standard view described above, materialized views do store data physically in the database.
Materialized views are used if data from complex queries needs to be accessed quickly.
CREATE MATERIALIZED VIEW view_name AS
SELECT....

**Refreshing materialized views**

Materialized views don't always have the most recent data. 
Since the result of a query is stored in a materialized view like in a cache, you need to make sure to refresh it periodically:

REFRESH MATERIALIZED VIEW popular_active_authors;

Materialized views can be refreshed when their underlying source data changes using Postgres triggers.



