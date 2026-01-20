# Car Booking CLI Project

## Overview

The **Car Booking CLI Project** is a console-based Java application designed to simulate a simple car rental/booking system. The project focuses on applying core Java concepts such as object-oriented programming, layered architecture, and data handling using arrays as an in-memory database.

This project was developed as a learning-oriented system to strengthen practical skills in Java, Git, and clean project structuring.

---

## Project Goals

* Practice core Java fundamentals through a real-world use case
* Understand layered architecture (Domain, DAO, Service)
* Implement business logic without external databases
* Build a fully interactive CLI application using `Scanner`
* Handle common challenges such as fixed-size data structures

---

## Technologies Used

* **Java (Core Java)**
* **Command Line Interface (CLI)**
* **Git & GitHub**
* No external frameworks or databases

---

## Project Architecture

The project follows a simple layered architecture:

### 1. Domain / Model Layer

Contains core entities of the system, such as:

* User
* Car
* ElectricCar
* Booking

These classes define the structure and attributes of the system objects.

### 2. DAO (Data Access Object) Layer

Responsible for managing data storage and retrieval. Since no database is used, **arrays** act as an in-memory data source.

Responsibilities include:

* Storing users, cars, and bookings
* Searching by ID or registration number
* Adding and removing elements by resizing arrays

### 3. Service Layer

Contains business logic and acts as a bridge between the DAO layer and the CLI.

Responsibilities include:

* Booking a car
* Validating user and car availability
* Filtering electric cars
* Coordinating operations across multiple DAOs

### 4. Main / CLI Layer

Handles user interaction via the command line.

Responsibilities include:

* Displaying menus
* Reading user input using `Scanner`
* Calling appropriate service methods
* Running the application inside a `while` loop

---

## Features Implemented

* View all users
* View all available cars
* View all car bookings
* Get a car by registration number
* Get a user by ID
* View electric cars only
* Book a car

---

## Key Challenges

### Fixed-Size Arrays

Java arrays have a fixed size, which introduced challenges such as:

* Adding new elements when the array is full
* Removing booked cars from the available cars list

These challenges were solved by:

* Creating new arrays with adjusted sizes
* Copying existing elements efficiently using `System.arraycopy()`
* Reassigning references after resizing

This approach improved understanding of how dynamic data structures work internally.

---

## Enhancements Completed

* Automatically remove booked cars from the available car array after successful booking

---

---

## Learning Outcomes

* Stronger understanding of Java OOP principles
* Hands-on experience with layered architecture
* Improved problem-solving skills with data structures
* Better understanding of CLI-based application flow

---

## Author

Developed as a learning project by **Mirkamol**.
