# TechStacks

A specialized book search and borrowing system tailored for computer science literature.

## Table of Contents
- [Project Overview](#project-overview)
- [Project Goals](#project-goals)
- [Development Timeline](#development-timeline)
- [Scope and Function](#scope-and-function)
- [Development Environment](#development-environment)
- [Development Log](#development-log)
- [Future Features](#future-features)

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
- **Key Tasks**
1. **Spring Framework Integration**: Incorporate Spring Boot to build the core application structure.
2. **Develop RESTful APIs**: Implement RESTful services to enable client-server interactions and facilitate data exchange.
3. **Web UI with Thymeleaf**: Use Thymeleaf to design dynamic web pages, enhancing the overall user experience.
4. **Enhance User Management**: Provide secure user authentication, role-based access control, and user profile management.
5. **Persistence with Hibernate**: Implement persistence using Hibernate as the JPA provider to handle ORM (Object-Relational Mapping) efficiently, providing database operations such as CRUD functionalities, lazy loading, and transaction management for scalable and robust data storage.
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

## Future Features

### Planned Features for the Main Page
1. **Popular Books Section**: Display the most borrowed or highest-rated books.
2. **New Arrivals Section**: Show newly added books to the library.
3. **Reader of the Month**: Highlight users who have borrowed the most books this month.
4. **Important Announcements**: Provide an area to display critical notices or updates.

### Community and User Engagement
1. **Top Borrowers Community Board**: Display a leaderboard of users who have borrowed the most books.
2. **Community Space for Discussions**: Provide a platform for users to discuss books, share opinions, and engage with other readers.

### Additional Functionality
1. **Crawler Development**: Develop a crawler to fetch accurate book cover images and integrate them into the database.
2. **Book Review and Rating**: Allow users to rate and review books they have read, with visibility to others.
3. **Password Recovery**: Implement a secure feature for users to retrieve or reset their passwords.
4. **OAuth Social Login**: Allow easy login using Google, Facebook, Twitter, and other social accounts.
5. **Google Calendar API Integration**: Automatically add loan and return dates to the user’s personal calendar for better management.
6. **Multilingual Support**: Utilize Google Translate API to provide services in multiple languages.

### User-Focused Enhancements
1. **Review and Rating Submission**: Enable users to write and submit reviews and ratings for books.
2. **Lost/Damaged Book Reporting**: Create a feature for users to report lost or damaged books.
3. **Loan Extensions with Points**: Allow users to extend their book loans using a points system.

### Admin-Focused Enhancements
1. **View Deleted Books**: Provide an interface for admins to view and manage deleted books.
2. **Handle Lost/Damaged Books**: Add functionality for admins to handle and process lost or damaged book reports.
