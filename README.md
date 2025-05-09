# UserService

**Port**: `8081`

## 📘 Description
Handles user registration, validation, and CRUD operations.

## 📡 API Endpoints
- `POST /api/users - Create user`
- `GET /api/users - Get all users`
- `GET /api/users/{id} - Get user by ID`
- `PUT /api/users/{id} - Update user`
- `DELETE /api/users/{id} - Delete user`
- `GET /api/users/validate/{id} - Validate user existence`

## ▶️ How to Run
1. Make sure you have Java 17+ and Maven installed.
2. Navigate to this microservice folder.
3. Run `mvn spring-boot:run`

## 🔍 Swagger UI
- Visit: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)