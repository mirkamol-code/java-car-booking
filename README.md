# 🚗 Car Booking CLI System

A **Java-based command-line application** for managing car rentals and bookings.  
The project demonstrates **layered architecture, OOP principles, in-memory data handling, file-based user loading, and unit testing**.

![Java](https://img.shields.io/badge/Java-25-blue)
![Maven](https://img.shields.io/badge/Build-Maven-orange)
![JUnit](https://img.shields.io/badge/Tests-JUnit%205-green)

---

## 📌 Overview

The **Car Booking CLI System** simulates a simplified car rental platform where users can:

- Browse available cars
- Filter electric cars
- Book and cancel cars
- View bookings per user
- Manage users loaded from CSV or generated dynamically

This project was built as a **learning-focused backend system** to strengthen:

- Object-Oriented Programming (OOP)
- Layered architecture design
- CLI-based application flow
- Java Collections API
- File I/O and CSV processing
- Unit testing with modern Java testing tools

---

## 🎯 Features

### Core Features
- View all users
- View all available cars
- View electric cars only
- Book a car for a user
- View all bookings
- View bookings by user
- Cancel bookings

### Data Features
- Load users from CSV file
- Generate sample users using JavaFaker
- In-memory data handling using `ArrayList`

### Testing Features
- Unit tests for service layer logic
- Mocked dependencies using Mockito
- Assertions using AssertJ

---

## 🛠 Tech Stack

- Java 25
- Maven
- JUnit 5 (Jupiter)
- Mockito
- AssertJ
- JavaFaker

---

## 🧱 Project Structure

```

java-cli-build/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mirkamolcode/
│   │   │       ├── model/
│   │   │       ├── dao/
│   │   │       ├── service/
│   │   │       └── Main.java
│   │   └── resources/
│   │       └── users.csv
│   ├── test/
│   │   └── java/
│   │       └── com/mirkamolcode/service/
├── pom.xml
└── README.md

```

---

## 🏗 Architecture

The project follows a **clean layered architecture**:

---

### 1. Model Layer

Contains domain entities and enums:

- `User`
- `Car`
- `CarBooking`
- Supporting enums (brand, menu options, messages)

Responsible for defining the **core business objects**.

---

### 2. DAO Layer

Handles data access and storage logic.

Responsibilities:
- Manage cars, users, and bookings in memory
- Load users from CSV file
- Provide basic CRUD-like operations
- Maintain application state using `ArrayList`

---

### 3. Service Layer

Contains business logic and rules.

Responsibilities:
- Booking validation logic
- Car availability checks
- Filtering electric cars
- Managing bookings lifecycle
- Handling cancellation logic
- Coordinating DAO operations

---

### 4. CLI Layer (Main)

Handles all user interaction.

Responsibilities:
- Display menu options
- Read user input using `Scanner`
- Call service layer methods
- Show results and error messages

---

## 📋 CLI Menu

```

0 - Cancel booking
1 - Book car
2 - View user bookings
3 - View all bookings
4 - View available cars
5 - View electric cars
6 - View all users
7 - Exit

````

---

## ⚙️ Requirements

- Java 25+
- Maven 3.8+

Check installation:

```bash
java --version
mvn --version
````

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd java-cli-build
```

### 2. Build project

```bash
mvn clean compile
```

### 3. Run tests

```bash
mvn test
```

### 4. Run application

```bash
mvn exec:java -Dexec.mainClass="com.mirkamolcode.Main"
```

Or run `Main.java` directly from your IDE.

---

## 🔁 Example Workflow

1. Start application
2. View available cars (`4`)
3. View users (`6`)
4. Book a car (`1`)
5. Enter car registration number
6. Enter user ID
7. View bookings (`3`)
8. Cancel booking (`0`)

---

## 🧪 Testing

The project includes unit tests for service layer logic:

* `CarBookingServiceTest`
* `CarServiceTest`
* `UserServiceTest`

Run tests:

```bash
mvn test
```

---

## 🚧 Key Challenges

### 1. Data Management Without Database

Instead of a database, the system uses:

* `ArrayList` for in-memory storage
* CSV file for initial user loading

Challenges included:

* Data consistency across operations
* Synchronization between bookings and car availability
* Maintaining clean separation of responsibilities

---

## 🔧 Improvements Implemented

* Replaced arrays with `ArrayList`
* Added file-based user loading (CSV)
* Implemented booking removal from available cars
* Introduced layered architecture (DAO → Service → CLI)
* Added unit testing with Mockito + AssertJ
* Improved input handling and exception safety

---

## 🚀 Future Improvements

* Persist cars and bookings to file/database
* Add authentication system
* Improve CLI UX (clearer menus, navigation)
* Add logging system (SLF4J / Logback)
* Add integration tests
* Package as executable JAR
* Migrate to Spring Boot + REST API version

---

## 📚 Learning Outcomes

* Strong understanding of Java OOP principles
* Practical experience with layered architecture
* Real-world CLI application design
* Testing with JUnit, Mockito, and AssertJ
* Working with file I/O and CSV parsing
* Better project structuring and maintainability

---

## 👤 Author

Developed by **Mirkamol**
---
