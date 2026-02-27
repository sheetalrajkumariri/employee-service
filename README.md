📘 Employee Service – Spring Boot + Redis Caching
📌 Project Overview

This project is a Spring Boot Employee Management Service.

It provides CRUD APIs for managing employees and uses Redis Cache to improve performance.

When someone calls the API:

First time → Data comes from MySQL Database

Second time → Data comes from Redis Cache (faster 🚀)

🛠 Technologies Used

Java 17

Spring Boot

Spring Data JPA

MySQL

Redis

Spring Cache

ModelMapper

Lombok

Docker (Optional for Redis)

🧑‍💼 What This Project Does?

This project provides:

✅ Create Employee
✅ Get Employee by ID
✅ Get All Employees (Pagination + Sorting)
✅ Update Employee
✅ Soft Delete Employee
✅ Redis Caching

🔥 What is Redis?
📌 Redis Definition 

Redis is an in-memory database.

It stores data in RAM, not on disk.

Because of this:

It is very fast

It is used for caching

It reduces database load

🎯 Why Redis is Used in This Project?

Without Redis:

Every request goes to MySQL

Database becomes slow under heavy load

With Redis:

First request → MySQL

Next request → Redis

Faster response

Better performance

🧠 Where Redis is Used in This Project?

In EmployeeServiceImpl.java

@Cacheable(value = "employeeCache", key = "#employeeId")
@CachePut(value = "employeeCache", key = "#result.id")
@CacheEvict(value = "employeeCache", key = "#employeeId")
✅ @Cacheable

Stores data in Redis when fetching employee.

✅ @CachePut

Updates cache when updating employee.

✅ @CacheEvict

Removes cache when deleting employee.

🗄 Database Used

MySQL Database:

Database Name: employee_db
Port: 3306
⚙️ Application Properties (Without Docker)

If Redis is installed normally on your system:

# Redis Configuration
spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
🐳 Install Redis Using Docker
Step 1: Install Docker

Download Docker Desktop and install it.

Step 2: Pull Redis Image
docker pull redis
Step 3: Run Redis Container
docker run --name my-redis -p 6379:6379 -d redis
Step 4: Check Container
docker ps

You should see Redis running.

📄 application.properties (With Docker)

If using Docker:

spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

⚠️ Configuration is same because Docker maps port 6379 to localhost.

💻 Install Redis Without Docker (Manual Install)
🪟 For Windows (Using WSL Recommended)
Step 1: Install Redis
sudo apt update
sudo apt install redis-server
Step 2: Start Redis
redis-server
Step 3: Test Redis

Open new terminal:

redis-cli

Then type:

ping

If it returns:

PONG

Redis is working ✅

📂 Project Structure
employee-service
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── exception
 └── application.properties
🧱 Main Components Explanation
🧑‍💼 Employee Entity

Represents employee table in MySQL.

📦 EmployeeRepository

Extends JPA Repository for DB operations.

🔄 EmployeeService

Contains business logic and caching logic.

🌍 EmployeeController

Handles REST APIs.

⚠️ GlobalExceptionHandler

Handles errors globally.

🚀 How Caching Works in This Project
First API Call:

Data fetched from MySQL

Stored in Redis

Returned to client

Second API Call:

Data fetched from Redis

Faster response

MySQL not used

📡 API Example
Get Employee By ID
GET http://localhost:8083/employees/1
🏁 How to Run Project
Step 1:

Start MySQL

Step 2:

Start Redis (Docker or Manual)

Step 3:

Run Spring Boot

mvn spring-boot:run

Server runs on:

http://localhost:8083
🎯 Benefits of This Project

✅ Faster performance using Redis
✅ Reduced database load
✅ Clean layered architecture
✅ Pagination & Sorting
✅ Soft delete support
✅ Proper exception handling

📌 Conclusion

This is a Spring Boot Employee Management System with:

MySQL database

Redis caching

Docker support

Clean architecture

Redis improves performance by storing frequently accessed data in memory.
