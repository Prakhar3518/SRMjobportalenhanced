# 🚀 Job Portal Backend API

A production-ready Job Portal backend built using **Spring Boot**, **Spring Security (JWT)**, and **MySQL**.

This project implements a scalable, layered architecture with secure authentication, role-based authorization, and structured API design.

---

## 🏗 Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT Authentication
- BCrypt Password Hashing
- MySQL
- Lombok
- Maven

---

## 📂 Project Architecture

```
Controller → Service → Repository → Database
```

```
com.jobportal.srm
│
├── controller       # REST API endpoints
├── service          # Business logic
├── repository       # JPA repositories
├── entity           # Database entities
├── dto              # Request/Response models
├── config           # Security & JWT configuration
├── exception        # Global exception handling
├── util             # JWT utilities & helpers
```

---

## 🔐 Authentication & Security

### ✔ JWT-Based Authentication
- Stateless authentication
- Token-based access control
- Secure login flow

### ✔ Role-Based Authorization
- ADMIN
- STUDENT
- RECRUITER

### ✔ Password Security
- BCrypt hashing
- No plain-text password storage
- Secure password validation

---

## 👤 User Module

- Register User
- Login User (JWT token generation)
- Role-based access control
- Get user profile
- Update profile
- Change password

---

## 💼 Job Module

- Create Job (Admin/Recruiter only)
- Update Job
- Delete Job
- Get All Jobs
- Search & Filter Jobs
- Pagination support

---

## 📄 Application Module

- Apply to Job
- Prevent duplicate applications
- View applied jobs
- Recruiter can view applicants
- Status management (Applied / Shortlisted / Rejected)

---

## 🛡 Authorization Rules

| Endpoint | Access |
|----------|--------|
| Register | Public |
| Login | Public |
| Create Job | ADMIN / RECRUITER |
| Apply Job | STUDENT |
| View All Users | ADMIN |

---

## 📦 API Sample

### 🔹 Register
```
POST /api/auth/register
```

### 🔹 Login
```
POST /api/auth/login
```

Response:
```json
{
  "token": "jwt_token_here"
}
```

---

## 🧠 Key Features Implemented

- Clean layered architecture
- DTO-based API contracts
- Global exception handling
- Validation annotations
- Pagination & filtering
- Secure password hashing
- JWT authentication
- Role-based access control
- Proper HTTP status handling

---

## 🛠 How To Run

1. Clone repository
2. Configure MySQL in `application.properties`
3. Run application
4. Use Postman to test APIs

---

## 📈 Design Principles Followed

- Separation of Concerns
- Stateless Authentication
- Secure Credential Storage
- Scalable Architecture
- Clean API Contract Design

---

## 🎯 Future Improvements

- Dockerization
- Redis Caching
- Email Notifications
- Swagger API Documentation
- CI/CD Integration

---

## 👨‍💻 Author

Prakhar Chaudhary  
Backend Developer | Spring Boot | Java

---

⭐ If you like this project, consider starring the repository!
