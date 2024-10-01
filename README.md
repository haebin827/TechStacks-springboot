# TechStacks

A specialized book search and borrowing system tailored for computer science literature.

## Table of Contents
- [Project Overview](#project-overview)
- [Project Goals](#project-goals)
- [Development Timeline](#development-timeline)
- [Scope and Function](#scope-and-function)
- [Development Environment](#development-environment)
- [Development Log](#development-log)

## Project Overview

### Project Name
**TechStacks**

### Purpose of Development
The TechStacks project is designed to create a specialized system for searching and borrowing computer science books. The aim is to provide students with easy access to relevant literature by utilizing the Google Books API and ChatGPT for data collection. The system supports essential library functions such as loan requests, renewals, and returns, along with admin functionalities for easy management.

---

## Project Goals

### Functional Goals
1. **Book Search**: Efficiently search for and borrow computer science literature.
2. **Loan System**: Integrate with the Google Books API to make book data accessible.
3. **User Management**: Manage user registration, login, loan requests, renewals, and returns.
4. **Admin Functionality**: Enable book catalog management, user management (blacklist), and announcement/FAQ management.

### Quality Goals
1. **User Experience (UX) Optimization**: Use Bootstrap to provide a user-friendly interface.
2. **Testing and Debugging**: Minimize bugs through unit, integration, and user testing.

### Business Goals
1. **Timely Project Completion**: Complete development in 3 months, starting with a console-based program and transitioning to a web system.
2. **Cost Efficiency**: Utilize open-source technologies to minimize costs.

### Strategic Goals
1. **Specialized Library Service**: Provide a service tailored for computer science students and researchers.
2. **Technological Competence**: Enhance development skills in Java and Spring Boot.

### Technological Goals
1. **Modern Tech Stack**: Use Spring Boot, Thymeleaf, MySQL, and QueryDSL.
2. **Database Optimization**: Efficiently manage book data in MySQL.

### Organizational Goals
1. **Team Skill Enhancement**: Improve proficiency in Spring Boot, MySQL, RESTful APIs, and frontend development.
2. **Agile Development**: Apply Agile methodologies for iterative improvements.

---

## Development Timeline

### Month 1: Console-Based Java Application
- **Objective**: Build core functionality using Java.
- **Key Tasks**: Implement core library operations, manage data with MySQL, and provide a command-line interface (CLI).

### Months 2 ~: Web-Based System with Spring Boot
- **Objective**: Migrate to a web-based platform.
- **Key Tasks**: Integrate the Spring Framework, develop RESTful APIs, implement a web UI with Thymeleaf, and enhance user management.

---

## Scope and Function

### Development Scope
1. **Book Data Collection**: Use Google Books API and ChatGPT to gather book data.
2. **Data Cleansing**: Standardize and refine data, remove duplicates, and validate values.
3. **Database Schema Design**: Design a scalable schema in MySQL.
4. **Frontend & Backend Development**:
   - **Backend**: Use Spring Boot to handle logic, database interactions, and API development.
   - **Frontend**: Build with HTML, CSS, JavaScript, and Thymeleaf for seamless interaction.

### Program Functions
- **Guest**:
  - User authentication (login/registration)
  - Search/view book details
  - Access public resources (announcements/FAQs)
- **User**:
  - Secure logout
  - Manage wishlist, submit book reviews, and request/manage loans
  - Access personalized account features (e.g., My Page)
- **Admin**:
  - Manage book catalog, FAQ categories, and users
  - Process loan requests, manage announcements/FAQs
  - Access admin dashboard

---

## Development Environment

- **OS**: Windows 11
- **Database**: MySQL 8
- **Framework**: Spring Boot 3.3.2
- **Language**: Java 17
- **Tools & Libraries**:
  - Lombok
  - Thymeleaf (Layout Dialect: 3.1.0)
  - ModelMapper (3.1.0)
  - QueryDSL (JPA: 5.0.0, APT: 5.0.0)
  - Jakarta APIs (Persistence: 3.1.0, Annotation: 1.3.5)
  - Spring Boot Starters (Data JPA, Web, Thymeleaf, Test, Validation)

---

## Development Log

- **07/24/24**: Resolved `java.lang.ExceptionInInitializerError` caused by a conflict between `Log4j` and `SLF4J`.
- **07/26/24**: Fixed `Q Entity` generation issue by aligning table names.

---

## Project Details

### Database Schema & Naming Guidelines
1. **Variable Naming**: Use `camelCase` for descriptive variables.
2. **File Naming**: Use `PascalCase` for filenames and `camelCase` for UI templates.
3. **Entity Naming**: Use `PascalCase` for entities and `snake_case` for tables and fields.

---

## Development Notes
- **Data Source**: Approximately 11,000 computer science book records managed in MySQL.
- **Frontend & Backend Interaction**: Frontend integrated with RESTful APIs for seamless data interaction.
- **Security & User Roles**: Role-based access control for admin and general users.
- **Testing & Scalability**: Unit and integration testing for APIs, with a focus on scalability and performance optimization.
