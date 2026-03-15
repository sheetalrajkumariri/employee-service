Employee Service – Spring Boot + Redis Caching
Overview

This project is a Spring Boot Employee Management Service.

It provides CRUD APIs for managing employees and uses Redis Cache to improve performance.

When someone calls the API:

First time → Data comes from MySQL Database

Second time → Data comes from Redis Cache (much faster 🚀)

This helps reduce database load and improves application performance.

Technologies Used
Backend

Java 17

Spring Boot

Spring Data JPA

Database

MySQL

Caching

Redis

Spring Cache

Other Tools

ModelMapper

Lombok

Docker (optional for Redis)

What This Project Does

This project provides the following APIs:

Create Employee

Get Employee by ID

Get All Employees (Pagination + Sorting)

Update Employee

Soft Delete Employee

Redis Caching

What is Redis?

Redis is an in-memory database.

It stores data in RAM instead of disk, which makes it extremely fast.

Redis is commonly used for:

Caching

Session storage

Real-time analytics

Message queues

Why Redis is Used in This Project

Without Redis:

Every request goes to MySQL

Database load increases

Application becomes slower

With Redis:

First request → MySQL
Next requests → Redis

Result:

Faster response time

Reduced database load

Better application performance

Where Redis is Used in This Project

Redis caching is implemented inside:

EmployeeServiceImpl.java

Using Spring Cache annotations:

Cacheable
@Cacheable(value = "employeeCache", key = "#employeeId")

Stores employee data in Redis when fetching from the database.

CachePut
@CachePut(value = "employeeCache", key = "#result.id")

Updates Redis cache when employee data is updated.

CacheEvict
@CacheEvict(value = "employeeCache", key = "#employeeId")

Removes employee data from Redis when the employee is deleted.

Database Used

This project uses MySQL Database.

Example configuration:

Database Name: employee_db
Port: 3306

Redis Configuration

Redis configuration is defined in:

application.properties

Example configuration:

spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
Install Redis Using Docker
Step 1 – Install Docker

Download and install Docker Desktop.

Step 2 – Pull Redis Image
docker pull redis
Step 3 – Run Redis Container
docker run --name my-redis -p 6379:6379 -d redis
Step 4 – Check Running Containers
docker ps

You should see Redis running.

Install Redis Without Docker (Manual)

For Linux or WSL:

Step 1 – Install Redis
sudo apt update
sudo apt install redis-server
Step 2 – Start Redis
redis-server
Step 3 – Test Redis

Open a new terminal:

redis-cli

Then type:

ping

If Redis is working correctly, it will return:

PONG
Project Structure
employee-service

controller
   EmployeeController

service
   EmployeeService
   EmployeeServiceImpl

repository
   EmployeeRepository

entity
   Employee

dto
   EmployeeRequest
   EmployeeResponse

exception
   GlobalExceptionHandler

resources
   application.properties
Main Components Explanation
Employee Entity

Represents the employee table in MySQL database.

EmployeeRepository

Handles database operations using Spring Data JPA.

EmployeeService

Contains business logic and caching logic.

EmployeeController

Handles REST API requests.

GlobalExceptionHandler

Handles application errors globally.

How Caching Works in This Project

First API Call:

Data is fetched from MySQL

Stored in Redis Cache

Returned to client

Second API Call:

Data is fetched directly from Redis

Response is much faster

MySQL is not accessed

API Example
Get Employee By ID
GET http://localhost:8083/employees/1
Running the Project
Prerequisites

You need:

Java 17+

Maven

MySQL

Redis

Step 1

Start MySQL database

Step 2

Start Redis

Using Docker or manual installation.

Step 3

Run the Spring Boot application

mvn spring-boot:run
Step 4

Server will start on:

http://localhost:8083
Benefits of This Project

Faster performance using Redis caching

Reduced database load

Clean layered architecture

Pagination and sorting support

Soft delete implementation

Proper exception handling

What You Learn From This Project

From this project you will learn:

How to use Redis caching in Spring Boot

How Spring Cache works

How to improve API performance

How to build scalable backend services

Summary

This project demonstrates how to build a Spring Boot Employee Management System with Redis caching.

Redis improves performance by storing frequently accessed data in memory, which reduces database queries and makes the application faster.
