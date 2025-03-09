# 🔐 Spring Boot JWT Authentication

Hi there! 👋 I'm excited to share this project where I implement **JWT-based authentication** in a **Spring Boot** backend.  
This is a solid foundation for any application that requires **Spring Security** with user authentication and authorization using **PostgreSQL**. 🚀

## 🛠️ Technologies Used
- **Java 17**
- **Spring Boot 3**
- **Spring Security & JWT**
- **PostgreSQL**
- **JPA (Hibernate)**

## 📌 Features
✅ User registration and authentication.  
✅ Secure password hashing with BCrypt.  
✅ Endpoint protection using JWT.  
✅ Clean and modular configuration.

## 🏗️ Architecture
Here's an overview of the authentication flow with JWT in this project:

![Architecture Diagram](docs/basic-api.svg)

## 🚀 Getting Started

1️⃣ **Clone the repository**:

```bash
git clone https://github.com/lopezsDev/SecureAuthAPI.git
cd SecureAuthAPI
```
2️⃣ Set up environment variables:

Be sure to define the environment variables before running the project:
```bash
spring.datasource.url=jdbc:postgresql://localhost:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION}
```

3️⃣ Run the project:

```bash
./mvnw spring-boot:run
```

📩 [Contact Me](mailto:lopezs.dev@gmail.com)
