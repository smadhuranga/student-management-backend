
# 🎓 Student Management System (Backend)

A full‑featured **Student Management System Backend API** built with **Spring Boot**, **JDBC Template** (no ORM), and **MySQL/MariaDB** — supporting secure authentication and full CRUD operations on student records.

This backend is part of a full‑stack student management project with a React frontend.

---

## 🧠 Project Overview

This project exposes REST APIs for authenticated users to manage student data.  
The APIs are protected via simple user authentication (username/password). All database access is done using **Spring JDBC Template** (no Hibernate or JPA). Layered architecture is used (Controller → Service → Repository).

---

## 🚀 Features

### 🔐 Authentication

✔ Register new users  
✔ Login users (username + password)  
✔ Only authenticated users can access CRUD APIs  

### 📎 Student Management

✔ Create a new student  
✔ Get all students  
✔ Get single student by ID  
✔ Update a student  
✔ Delete a student  
✔ Global exception handling  
✔ Secure and consistent API responses  

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend Framework | Spring Boot |
| Database Access | JDBC Template |
| Database | MySQL / MariaDB |
| Authentication | Basic / Custom |
| Dependency Injection | Spring IoC |
| Exception Handling | Global Exception Handler |
| API Format | JSON REST |

---

## 📁 Folder Structure

```
backend/
├── controller/
│   ├── AuthController.java
│   └── StudentController.java
│
├── service/
│   ├── AuthService.java
│   └── StudentService.java
│
├── repository/
│   ├── UserRepository.java
│   └── StudentRepository.java
│
├── model/
│   ├── User.java
│   └── Student.java
│
├── security/
│   └── SecurityConfig.java
│
├── exception/
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── StudentManagementApplication.java
```

---

## 🛠 API Endpoints

### ✅ Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/register` | Register new user |
| POST | `/api/v1/auth/login` | Login user (returns success message) |
| GET | `/api/v1/auth/user/{username}` | Fetch user details |

---

### 📚 Student APIs

> **Require authentication**

| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/api/v1/student` | Create student |
| GET | `/api/v1/student` | Get all students |
| GET | `/api/v1/student/{id}` | Get student by ID |
| PUT | `/api/v1/student/{id}` | Update student |
| DELETE | `/api/v1/student/{id}` | Delete student |

---

## 🧩 Example Requests (via Postman / Axios)

### Register

```json
POST /api/v1/auth/register
{
  "Name": "admin",
  "Password": "1234"
}
```

### Login

```json
POST /api/v1/auth/login
{
  "Name": "admin",
  "Password": "1234"
}
```

### Create Student

```json
POST /api/v1/student
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "2000-01-15",
  "enrollmentDate": "2023-09-01"
}
```

---

## ⚠ Exception Handling

Errors are consistently returned with structured JSON:

```json
{
  "timestamp": "2026‑02‑25T10:00:00",
  "status": 404,
  "message": "Student not found"
}
```

Custom exceptions like `ResourceNotFoundException` are globally handled using `GlobalExceptionHandler`.

---

## 📌 How to Run (Locally)

1. **Clone repository**

   ```bash
   git clone https://github.com/smadhuranga/student-management-backend.git
   ```

2. **Configure `application.properties`**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Build & Run**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

✅ Backend will start on `http://localhost:8080`

---

## 🧠 Best Practices Applied

✔ Layered architecture (Controller → Service → Repository)  
✔ JDBC Template (no ORM complexity)  
✔ Centralized Exception Handling  
✔ Consistent REST endpoints and API versioning  
✔ Security configuration using Spring Security  
✔ Descriptive error messages

---

## 🙌 Contributing

Feel free to open issues or submit pull requests! Suggestions and improvements are always welcome!

---

## 📜 License

This project is open‑source and available under the MIT License.

---

🎓 Build amazing APIs — this backend is ready to power your full‑stack student app!
```