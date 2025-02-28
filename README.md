# Cambo API (Mini Project )

This is a Spring Boot-based RESTful API that provides endpoints for managing products, product categories, and user authentication (login and registration).

## Features

- **Product Management**: CRUD operations for products.
- **Product Category Management**: CRUD operations for product categories.
- **User Authentication**: User registration and login with JWT-based authentication.

## Technologies Used

- **Spring Boot**: Framework for building the API.
- **Spring Data JPA**: For database operations.
- **Spring Security**: For securing the API and handling authentication.
- **JWT (JSON Web Tokens)**: For token-based authentication.
- **PostgreSQL**: Database for storing data
- **Gradle**: For dependency management and building the project.

---

## API Endpoints

### Authentication

- **POST /api/v1/auth/register**: Register a new user.
  ```json
  {
    "userName":"phivlyhou",
    "email":"lyhou123hom@gmail.com",
    "password":"Password1!",
    "confirmPassword":"Password1!"
  }
- **POST /api/v1/auth/login**: Login user.
  ```json 
   admin account for testing
   {
    "email":"admin@gmail.com",
    "password":"lyhou123"
   }
  
   user account for testing
   {
    "email":"lyhou282@@gmail.com",
    "password":"lyhou123"
   }

### Product Management

- **GET /api/v1/products?page=0&size=25**: Get all products.
- **GET /api/v1/products/{uuid}**: Get a product by UUID.
- **POST /api/v1/products**: Add a new product.
- **PUT /api/v1/products/{uuid}**: Update a product.
- **DELETE /api/v1/products/{uuid}**: Delete a product.
- **GET /api/v1/products/category/{category}**: Get all products by category.

### Product Category Management
- **GET /api/v1/categories**: Get all categories.
- **GET /api/v1/categories/{uuid}**: Get a category by UUID.
- **POST /api/v1/categories**: Add a new category.
- **PUT /api/v1/categories/{uuid}**: Update a category.
- **DELETE /api/v1/categories/{uuid}**: Delete a category.

### User Management
- **GET /api/v1/users**: Get all users.
- **GET /api/v1/users/{uuid}**: Get a user by UUID.
- **DELETE /api/v1/users/{uuid}**: Delete a user.
- **PUT /api/v1/users/{uuid}**: Update a user.
- **PUT /api/v1/users/block/{uuid}**: Block a user.
- **PUT /api/v1/users/unblock/{uuid}**: Unblock a user.

### Roles Management
- **GET /api/v1/roles**: Get all roles.
- **GET /api/v1/roles/{uuid}**: Get a role by UUID.
- **POST /api/v1/roles**: Add a new role.
- **PUT /api/v1/roles/{uuid}**: Update a role.
- **DELETE /api/v1/roles/{uuid}**: Delete a role.

### File Upload
- **POST /api/v1/files**: Upload a file.
- **POST /api/v1/files/multiple**: Upload multiple files.
- **DELETE /api/v1/files/{fileName}**: Delete a file.
