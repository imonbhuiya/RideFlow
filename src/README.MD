# 🚗 RideFlow

**RideFlow** is a console-based ride-hailing application built with **Core Java** to practice Object-Oriented Programming (OOP) through a realistic project.

The project models the basic workflow of applications such as Uber or Bolt, including passengers, drivers, vehicles, ride requests, ride statuses, pricing, and feedback.

> 🚧 **Project Status:** Work in Progress

---

## 🎯 Project Goal

The main goal of RideFlow is to strengthen my understanding of Java and Object-Oriented Programming by building a project from scratch.

Concepts practiced in this project:

* Classes and Objects
* Encapsulation
* Inheritance
* HAS-A relationships
* Constructors
* Abstract Classes
* Enums
* Collections
* Exception Handling
* Business Logic
* Object Interaction

---

## 🛠️ Tech Stack

* Java
* IntelliJ IDEA
* Git
* GitHub

This version intentionally does **not** use Spring Boot, databases, REST APIs, or a frontend.

The focus is on mastering **Core Java and OOP fundamentals first**.

---

## 🚕 RideFlow Workflow

```text
Passenger requests ride
        ↓
Selects Ride Type
(X / XL / XXL)
        ↓
Price Estimated
        ↓
Passenger Confirms
        ↓
SEARCHING
        ↓
Driver Accepts
        ↓
ACCEPTED
        ↓
DRIVER_ARRIVING
        ↓
DRIVER_ARRIVED
        ↓
IN_PROGRESS
        ↓
COMPLETED
        ↓
Feedback
```

A ride can also become:

```text
CANCELLED
```

when cancellation is allowed.

---

## 👤 User

`User` is the abstract parent class containing information shared by passengers and drivers.

### Attributes

```text
id
name
email
phone
address
```

Relationship:

```text
             User
            /    \
           /      \
    Passenger    Driver
```

This demonstrates **inheritance**.

---

## 🧍 Passenger

A `Passenger` is a specialized `User`.

Planned responsibilities include:

* Request a ride
* Cancel a ride
* Give feedback
* Manage account information

Relationship:

```text
Passenger IS-A User
```

---

## 🚘 Driver

A `Driver` is also a specialized `User`.

Additional attributes:

```text
drivingLicence
vehicle
```

Relationship:

```text
Driver IS-A User

Driver HAS-A Vehicle
```

The `Vehicle` field is stored as a `Vehicle` object rather than plain text.

---

## 🚙 Vehicle

A vehicle currently contains:

```text
id
model
numberPlate
color
```

Ride type support will be connected using the `RideType` enum.

---

## 🏷️ Ride Types

RideFlow currently supports three ride categories:

```text
X
XL
XXL
```

Implemented using:

```java
public enum RideType {
    X,
    XL,
    XXL
}
```

---

## 🟢 Driver Status

Drivers can have one of two availability states:

```text
ONLINE
OFFLINE
```

Implemented using the `DriverStatus` enum.

---

## 🔄 Ride Status

The ride lifecycle is represented using `RideStatus`:

```text
SEARCHING
ACCEPTED
DRIVER_ARRIVING
DRIVER_ARRIVED
IN_PROGRESS
COMPLETED
CANCELLED
```

Using enums prevents invalid arbitrary status values from being assigned.

---

## 🧠 OOP Concepts Used

### Encapsulation

Fields are kept private and accessed through controlled methods.

```text
private fields
      ↓
getters / setters
      ↓
controlled access
```

### Inheritance

Common user information is defined once in `User`.

```text
User
├── Passenger
└── Driver
```

### HAS-A Relationship

A driver owns or uses a vehicle.

```text
Driver
   │
   └── HAS-A → Vehicle
```

Instead of storing:

```text
vehicle = "BMW"
```

RideFlow connects actual objects:

```text
Driver → Vehicle object
```

### Abstraction

`User` is abstract because RideFlow does not need generic `User` objects.

The application works with specific user types such as:

```text
Passenger
Driver
```

---

## 📁 Project Structure

```text
src/
└── com/
    └── rideflow/
        ├── model/
        │   ├── User.java
        │   ├── Passenger.java
        │   ├── Driver.java
        │   └── Vehicle.java
        │
        ├── enums/
        │   ├── RideType.java
        │   ├── DriverStatus.java
        │   └── RideStatus.java
        │
        └── app/
```

The structure will grow as new parts of the application are implemented.

---

## 📊 Development Progress

| Feature                      | Status          |
| ---------------------------- | --------------- |
| Project Requirements         | ✅ Completed     |
| OOP Design                   | ✅ Completed     |
| User                         | ✅ Completed     |
| Passenger                    | ✅ Completed     |
| Vehicle                      | 🟡 In Progress  |
| Driver                       | ✅ Basic Version |
| RideType                     | ✅ Completed     |
| DriverStatus                 | ✅ Completed     |
| RideStatus                   | ✅ Completed     |
| Ride                         | ⬜ Not Started   |
| Feedback                     | ⬜ Not Started   |
| Pricing Logic                | ⬜ Not Started   |
| Collections / In-Memory Data | ⬜ Not Started   |
| Exception Handling           | ⬜ Not Started   |
| Console Application          | ⬜ Not Started   |
| Testing & Refactoring        | ⬜ Not Started   |

---

## 🗺️ Next Steps

The next implementation steps are:

1. Connect `RideType` with `Vehicle`
2. Connect `DriverStatus` with `Driver`
3. Implement `Ride`
4. Implement `Feedback`
5. Add ride lifecycle logic
6. Add pricing logic
7. Store application data using Java Collections
8. Add exception handling
9. Build the interactive console application
10. Test and refactor the project

---

## 🔮 Future Improvements

After completing the Core Java version, RideFlow could be extended with:

* Spring Boot
* REST APIs
* MySQL
* JPA / Hibernate
* DTOs
* Validation
* Global Exception Handling
* Spring Security
* Authentication
* Docker

The current version intentionally stays focused on **Java OOP fundamentals** before moving to backend frameworks.

---

## 📚 Learning Project

RideFlow is being developed incrementally as part of my Java Backend Engineering learning journey.

Instead of starting with frameworks, the project focuses first on understanding how objects, relationships, state, and business rules work together in a real application.
