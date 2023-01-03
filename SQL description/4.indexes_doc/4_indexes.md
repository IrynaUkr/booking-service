## Index in MySQL

Indexes are special lookup tables that the database search engine can use to speed up data retrieval.

An index helps to speed up SELECT queries and WHERE clauses;
however, it slows down data input, with UPDATE and INSERT statements.
Indexes can be created or dropped with no effect on the data.

PostgresSQL provides several index types: B-tree, Hash, GiST, SP-GiST and GIN.
Each Index type uses a different algorithm that is best suited to different types of queries. 

#### B-trees (balance trees)

**_By default, the CREATE INDEX command creates B-tree indexes_**, which fit the most common situations
Used when a large number of possible values in a column(high cardinality).
B- tree is a tree data structure with a root and nodes.
The tree is balanced because the root node is the index value that splits the range of values found in the index column.
B-trees can handle equality and range queries on data that can be sorted into some ordering.
In particular, the PostgreSQL query planner will consider using a B-tree index whenever an indexed column is involved 
in a comparison using one of these operators: <, <=, =, >=, >

Constructs equivalent to combinations of these operators, such as BETWEEN and IN, can also be implemented with a B-tree index search. Also, an IS NULL or IS NOT NULL condition on an index column can be used with a B-tree index

We can use explain to see how the database maps query into an execution plan. For this goal write the word EXPLAIN  before query.

**CREATE INDEX** idx_users_username **ON** users(username);

#### Hash

Equality only. Hash indexes used when “=” is used, but not for ranges of values.
Smaller Size than b-tree.
As Fast as b-tree. Builds and lookups are comparable, advantage is size, may fit in memory.
Email is a good candidate for hash index, since the email should be unique to each person.

**CREATE INDEX** idx_users_email **ON** users **USING HASH** (email);

#### Bitmap

Bitmap indexes are used when columns have few distinct values. 
This is known as low cardinality. Bitmaps are also used when our queries filter on these low-cardinality values.
Bitmap indexes store a series of bites for index values.
The number of bits used is the same as the number of distinct values in a column. For example, a column that has either a yes or no value would require two bits. One corresponding to the yes, and one corresponding to the no.
One of the advantages is  that we can perform boolean operations quickly.
Used when small number of possible values in a column(low cardinality).
Filter by bitwise operations, such as AND, OR and NOT.
Postgres builds bitmap indexes on the fly as needed.
Their drawback is they are less efficient than the traditional B-tree indexes for columns whose data is frequently updated:
consequently, they are more often employed in read-only systems that are specialized for fast query - e.g., data warehouses, and generally unsuitable for online transaction processing applications.

**CREATE INDEX** idx_tickets_category **ON** tickets(category)

#### Special purpose indexes

(these indexes designed for specific data type, like geospatial data)
* GIST - generalized Search Tree. Not a single type of index. This is a framework for implementing custom indexes
* SP-GIST- space partitioned GIST, supports partitioned search trees. Used for unbalanced data structures
* GIN indexes are inverted indexes which can handle values that contain more than one key, arrays for example. Like GiST and SP-GiST, GIN can support many different user-defined indexing strategies and the particular operators with which a GIN index can be used vary depending on the indexing strategy. As an example, the standard distribution of PostgreSQL includes GIN operator classes for one-dimensional arrays, which support indexed queries using these operators:
<@, @>, =, &&

* BRIN

  #### Pros and cons of using indexes.

  **Indexes have various advantages like:**
* increased performance in searching for records,
* sorting records,
* grouping records
* maintaining a unique column. 


  **disadvantages**
* increased disk space, 
* slower data modification,
* updating records in the clustered index.

