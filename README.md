# ECommerce-Spring-Boot

A robust backend API for managing products, categories, and orders in an eCommerce platform, built with Spring Boot.

## Features

- **Product Management**
  - Add new products (single or bulk)
  - Update product information
  - Delete products by ID
  - Get all products or fetch by ID
  - Products are associated with categories and track stock quantities

- **Category Management**
  - Add new categories (single or bulk)
  - Update category details
  - Delete categories by ID or name
  - Fetch all categories or by ID

- **Order Management**
  - Create new orders (single or bulk)
  - Update order information
  - Delete orders by ID
  - Fetch all orders or by ID

- **RESTful API Endpoints**
  - Follows standard REST conventions with clear separation between public endpoints and internal logic

- **Exception Handling**
  - Custom exceptions for resource not found and API errors
  - Graceful error messages for clients

- **Validation**
  - Uses `jakarta.validation` for request validation (e.g., required fields, field sizes)

- **Persistence**
  - Uses Spring Data JPA for database access and entity management

## Technologies

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Validation
- Maven
- H2 DB
- Lombok

## API Endpoints Overview

### Products

- `GET /api/v1/products` - List all products
- `GET /api/v1/products/{id}` - Get a product by ID
- `POST /api/v1/products` - Add products (bulk)
- `PATCH /api/v1/products/{id}` - Update product
- `DELETE /api/v1/products/{id}` - Delete product

### Categories

- `GET /api/v1/categories` - List all categories
- `GET /api/v1/categories/{id}` - Get a category by ID
- `POST /api/v1/categories` - Add categories (bulk)
- `PATCH /api/v1/categories/{id}` - Update category
- `DELETE /api/v1/categories/{id}` - Delete category by ID
- `DELETE /api/v1/categories/name/{name}` - Delete category by name

### Orders

- `GET /api/v1/public/orders` - List all orders
- `GET /api/v1/public/orders/{id}` - Get order by ID
- `POST /api/v1/public/orders` - Create orders (bulk)
- `POST /api/v1/public/order` - Create a single order
- `PATCH /api/v1/public/orders/{id}` - Update order
- `DELETE /api/v1/public/orders/{id}` - Delete order

## Getting Started

1. **Clone the repo**
   ```bash
   git clone https://github.com/Avdheshg/ECommerce-Spring-Boot.git
   cd ECommerce-Spring-Boot
   ```

2. **Configure Database**
   - Update `application.properties` for your DB credentials

3. **Build & Run**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Test Endpoints**
   - Use Postman or curl to interact with API endpoints

## Project Highlights

- **Code Structure:** Clean separation of controllers, services, models, repositories, and exception handling
- **Bulk Operations:** Efficiently add products, categories, and orders in bulk
- **Validation & Error Handling:** Reliable request validation and user-friendly error responses

**Made by [Avdheshg](https://github.com/Avdheshg)**
