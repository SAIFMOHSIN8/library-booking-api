Library Booking API

Project Overview

Library Booking API is a Spring Boot application that manages resource borrowing, waitlists, reservations, and reservation claims.

The system allows employees to borrow available resources, join waitlists when resources are unavailable, receive reservations automatically when resources are returned, and claim reservations before they expire.

⸻

Features

Employee Management

* Create employees
* View all employees

Resource Management

* Create resources
* View all resources

Borrow Management

* Borrow available resources
* Return borrowed resources
* Prevent duplicate active borrows

Waitlist Management

* Join waitlists when resources are unavailable
* FIFO (First-In First-Out) queue processing
* Prevent duplicate waitlist entries

Reservation Management

* Automatically create reservations when resources are returned
* Reservation expiration support
* Claim valid reservations
* Prevent claiming expired reservations
* Prevent claiming another employee’s reservation

⸻

Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven

⸻

Business Rules

Borrowing Rules

* Employees can only borrow available resources.
* Employees cannot borrow the same resource more than once while an active borrow exists.
* Pending reservations reduce available copies.

Waitlist Rules

* Employees may join a waitlist when no copies are available.
* Waitlist positions are assigned using FIFO order.
* Employees cannot join the same waitlist multiple times.

Reservation Rules

* Returning a resource automatically creates a reservation for the first employee in the waitlist.
* Reservations expire after 2 hours.
* Expired reservations cannot be claimed.
* Employees may only claim their own reservations.
* Claiming a reservation creates a new borrow record.

⸻

Assumptions and Design Decisions

The specification left some business rules open to interpretation. The following assumptions were made:

Reservation Expiry

Reservations expire 2 hours after creation.

Waitlist Processing

The first employee in the waitlist receives the next available reservation using FIFO ordering.

Reservation Ownership

Only the employee assigned to a reservation may claim it.

Availability Calculation

Pending reservations are treated as unavailable copies and reduce the number of available resources.

⸻

API Endpoints

Employee Endpoints

POST /employees
GET /employees

Resource Endpoints

POST /resources
GET /resources

Borrow Endpoints

POST /borrow?employeeId={employeeId}&resourceId={resourceId}
POST /borrow/return?borrowRecordId={borrowRecordId}

Waitlist Endpoints

POST /waitlist?employeeId={employeeId}&resourceId={resourceId}

Reservation Endpoints

POST /reservations/claim?reservationId={reservationId}&employeeId={employeeId}

⸻

Business Rule Validation Examples

Duplicate Borrow Prevention

Request:

POST /borrow?employeeId=4&resourceId=3

Expected Result:

Employee already has this resource borrowed

Duplicate Waitlist Prevention

Request:

POST /waitlist?employeeId=4&resourceId=2

Expected Result:

Employee is already in the waitlist for this resource

Claim Another Employee’s Reservation

Request:

POST /reservations/claim?reservationId=2&employeeId=4

Expected Result:

You cannot claim another employee's reservation

Claim Expired Reservation

Request:

POST /reservations/claim?reservationId=3&employeeId=1

Expected Result:

Reservation expired

⸻

How to Run

1. Clone the repository:

git clone https://github.com/SAIFMOHSIN8/library-booking-api.git

2. Open the project in IntelliJ IDEA.
3. Run:

LibraryBookingApiApplication

4. The application will start on:

http://localhost:8080

⸻

Database Access

H2 Console:

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:file:./data/librarydb

Username:

sa

Password:

(blank)

⸻

Sample Workflow

1. Create employees.
2. Create resources.
3. Borrow a resource.
4. Join a waitlist when no copies are available.
5. Return a borrowed resource.
6. Automatically create a reservation.
7. Claim the reservation.
8. Create a new borrow record.

⸻

Exception Handling

The application uses a global exception handler to return meaningful error messages and appropriate HTTP status codes when business rules are violated.

Examples:

* Employee not found
* Resource not found
* No copies available
* Employee already has this resource borrowed
* Employee is already in the waitlist for this resource
* You cannot claim another employee’s reservation
* Reservation expired

⸻

Author

Saif Al Mahrouqi