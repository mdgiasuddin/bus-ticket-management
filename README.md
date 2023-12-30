# Bus Ticket Management System
Bus ticket management system back-end. This architecture can be used in various bus companies of Bangladesh to sell the bus tickets.

## Table of Contents
- [Features](#features)
- [Workflow](#workflow)
- [Technology Stack](#technology-stack)
- [Project Setup](#project-setup)
- [Future Improvement](#future-improvement)

## Features
- Counter master is the main user for selling tickets. They are connected with a ticket counter. A bus company has several bus routes. A ticket counter can access only the permitted bus routes (Sell the bus tickets for the permitted bus routes). Ticket price for the whole route is the same for every bus counter. There is also <b>Admin</b> user who can create another users, create but routes, trips etc.
- Besides auto-incremented id, there is another feature <b>id-key</b>. This will prevent users to access unauthorized entity. Any user can only access the details of an entity by sending its id-key as query parameter with the id. If the user failed to provide the correct id-key the request will be rejected. It will make sure that a user will only access the details of an entity from the list view he sees in web.

## Workflow
* Create a admin user at the start of the project.
* Create bus stations.
* Create bus routes using any of the two stations.
* Create ticket counter.
* Create users of the ticket counter.
* Give permission of routes to the ticket counters so that the users of the counter get access of the routes.
* Create trips (schedule of bus journey).
* Now the counter masters can access the trips of permitted routes and sell the tickets.

## Technology Stack
This repository is built upon following technologies:
* Spring Boot 3.2
* Postgresql
* JDK 21

To get started with this project, you will need to have the following installed on your local machine:
* IDE (IntelliJ IDEA Recommended)

## Project Setup
To build and run the project, follow these steps:

### Repository Cloning
Clone the repository: `git clone https://github.com/mdgiasuddin/bus-ticket-management.git`.

### Set up Database
* Set up <b>Postgresql</b> database for this either by installing Postgresql database manually or by <b>Docker</b>. Change the `application.properties` file accordingly.
* Run the project.

The application will be available at http://localhost:9236/swagger-ui/index.html.

## Future Improvement
* Integrate `payment-gateway` with the system so that passenger can buy ticket directly without going to the ticket counter and can make payment through payment-gateway.
* Integrate accounting module so the company authority can see the report of income, expense and profit.