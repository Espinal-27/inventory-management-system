# Inventory Management System

A console-based inventory management system developed with Java, SQLite, JDBC, and Maven.

## Overview

This project is designed to manage products through a simple command-line interface.

The application allows users to:

- Add products
- View all products
- Search products by ID
- Update product information
- Delete products
- Update product stock
- Store data persistently using SQLite

## Technologies

- Java 17
- SQLite
- JDBC
- Maven
- SQL
- Object-Oriented Programming (OOP)

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── espinal/
│   │           └── inventory/
│   │               ├── Database.java
│   │               ├── Main.java
│   │               ├── Product.java
│   │               └── ProductDAO.java
│   │
│   └── resources/
│       └── database.sql
│
└── pom.xml
