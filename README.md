# Library Booking API

## Project Overview

Library Booking API is a Spring Boot application that simulates a library resource management system.

The system allows employees to borrow resources, join waitlists when resources are unavailable, receive reservations when resources are returned, and claim reservations before they expire.

---

## Features

### Employee Management
- Create employees
- View all employees

### Resource Management
- Create resources
- View all resources

### Borrow Management
- Borrow available resources
- Return borrowed resources
- Prevent duplicate active borrows

### Waitlist Management
- Join waitlists when resources are unavailable
- FIFO (First-In First-Out) queue handling
- Prevent duplicate waitlist entries

### Reservation Management
- Automatically create reservations when resources are returned
- Reservation expiration support
- Claim valid reservations
- Prevent claiming expired reservations

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

---

## Business Rules

### Borrowing Rules

- Employees can only borrow available resources.
- Employees cannot borrow the same resource more than once while an active borrow exists.
- Pending reservations reduce available copies.

### Waitlist Rules

- Employees may join a waitlist when no copies are available.
- Waitlist positions are assigned using FIFO order.
- Employees cannot join the same waitlist multiple times.

### Reservation Rules

- Returning a resource automatically creates a reservation for the first employee in the waitlist.
- Reservations expire after 2 hours.
- Expired reservations cannot be claimed.
- Claiming a reservation creates a borrow record.

---

## API Endpoints

### Employee Endpoints

```http
POST /employees
GET  /employees
```

### Resource Endpoints

```http
POST /resources
GET  /resources
```

### Borrow Endpoints

```http
POST /borrow
POST /borrow/return
```

### Waitlist Endpoints

```http
POST /waitlist
```

### Reservation Endpoints

```http
POST /reservations/claim
```

---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/SAIFMOHSIN8/library-booking-api.git
```

2. Open the project in IntelliJ IDEA.

3. Run:

```text
LibraryBookingApiApplication
```

4. The application will start on:

```text
http://localhost:8080
```

---

## Database Access

H2 Console:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:file:./data/librarydb
```

Username:

```text
sa
```

Password:

```text
(blank)
```

---

## Sample Workflow

1. Create an employee.
2. Create a resource.
3. Borrow the resource.
4. Join the waitlist when no copies are available.
5. Return the resource.
6. Automatically create a reservation.
7. Claim the reservation.
8. Create a new borrow record.

---

## Exception Handling

The application uses a global exception handler to return meaningful error messages and appropriate HTTP status codes when business rules are violated.

Examples:

- Employee not found
- Resource not found
- No copies available
- Employee already has this resource borrowed
- Employee is already in the waitlist
- Reservation expired

---

## Author

Saif Al Mahrouqi