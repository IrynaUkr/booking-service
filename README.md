Spring Web application contains three service classes:

* UserService
* EventService
* Ticket service
They contain user, event, and booking-related functionality accordingly. 

Implementation of the BookingFacade interface delegates method calls to services mentioned above.

A model UserAccount stores the amount of prepaid money the user has in the system, which uses during the booking procedure.

Ticket booking methods  checks and withdraw money from user account according to the ticketPrice for a particular event.

Implemented DAO objects for each of the domain model entities.They inherit from one of the Spring Data interfaces, CrudRepository.

Uses transaction management to perform actions in a transaction where it necessary.

Created a database schema for storing application entities.

They store and retrieve application entities from the database.

Covered code with unit tests and integration tests.
