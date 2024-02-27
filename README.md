# Restaurant Management System

The Restaurant Management System is a web application implemented using ReactJS (Hooks) for the frontend and Java Spring Boot framework (Hibernate, Spring Security with JSON Web Token, REST API) for the backend. Additional libraries such as Bootstrap, AXIOS, Sweetalert, and Redux are also utilized.

## Project Overview

The main objective of the application is to facilitate users in viewing restaurant menus, placing food orders, and tracking the status of their orders. The system encompasses three user roles with distinct capabilities:

- **ADMIN**: Manages meal types, meals, users, and final orders.
- **EMPLOYEE**: Reviews and updates order statuses.
- **USER**: Places orders, manages personal information, and tracks order history.

Additionally, unregistered users can:

- Register an account
- Browse the menu
- Add items to the cart
- Confirm orders (address and phone number required)

## Key Features

- User authentication and authorization with role-based access control.
- Comprehensive management of meal types, meals, and user data.
- Order placement, tracking, and history for registered users.
- Real-time order status updates for employees.
- Responsive design for optimal user experience across devices.

## Technologies Used

- **Frontend**: ReactJS (Hooks),AXIOS, Sweetalert, Redux
- **Backend**: Java Spring Boot, Hibernate, Spring Security with JSON Web Token
- **Database**: PostgreSQL
