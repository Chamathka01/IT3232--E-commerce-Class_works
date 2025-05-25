# Department and Employee Management System

This is a Spring Boot application that manages departments and their associated employees. It provides RESTful APIs to create and retrieve departments and handle related business logic and exceptions.

## 📁 Project Structure
```
src/
└── main/
└── java/
└── lk/
└── vau/
└── fas/
└── ict/
├── model/
│ ├── Department.java
│ ├── Employee.java
│ └── ErrorResponse.java
├── controller/
│ └── DepartmentController.java
├── service/
│ └── DepartmentService.java
├── repo/
│ └── DepartmentRepo.java
└── exceptionHandler/
└── GenericExceptionHandler.java
```

## 🛢️ Database Configuration
```
spring.application.name=day10
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create
```

## 📦 Models

01. Department
```java
package lk.vau.fas.ict.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dept_id")
    private int id;
    @Column(nullable=false)
    private String name;
    private Date established;
    @OneToMany(mappedBy="department")
	private List<Employee>employees;

    public Department(int id, String name, Date established) {
        this.id = id;
        this.name = name;
        this.established = established;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getEstablished() {
        return established;
    }
    public void setEstablished(Date established) {
        this.established = established;
    }

    
}
```

02. Employee
```java
package lk.vau.fas.ict.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmpId;
    private String EmpName;
    private String Job;
    private double Salary;
    private Date DOB;

    @ManyToOne
    @JsonBackReference
    private Department department;

    public Employee() {}

    public Employee(Long empId, String empName, String job, double salary, Date dOB, Department department) {
        EmpId = empId;
        EmpName = empName;
        Job = job;
        Salary = salary;
        DOB = dOB;
        this.department = department;
    }

    public Long getEmpId() {
        return EmpId;
    }

    public void setEmpId(Long empId) {
        EmpId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date dOB) {
        DOB = dOB;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
```
03. ErrorResonse.java
```java
package lk.ac.vau.fas.ict.model;

public class ErrorResponse {
    private int statusCode;
	private String errorMessage;

    public ErrorResponse() {
		
	}

    public ErrorResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }   
}

```

##  📁 Controllers

01. DepartmertController.java
```java
package lk.vau.fas.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import lk.ac.vau.fas.ict.DepartmentRepo;
import lk.vau.fas.ict.model.Department;
import lk.vau.fas.ict.service.DepartmentService;
@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    public DepartmentService service;

	@GetMapping
    public ResponseEntity<List<Department>>getDepts(){
		return new ResponseEntity<List<Department>>
			(service.getDepts(),HttpStatus.OK);
	}
	/* 
    @GetMapping("/{id}")
	public ResponseEntity<Department>getDepts(@PathVariable("id") int id){
		if(service.getDept(id)== null) {
			return new ResponseEntity<Department>
			(service.getDept(id),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Department>
		(service.getDept(id),HttpStatus.OK);
	}
*/
	@PostMapping
	public ResponseEntity<String> addDept(@RequestBody Department department) {
		return new ResponseEntity<String>(service.addDept(department),HttpStatus.OK);
	}
}
    /* 
    @PostMapping
	public String addDept(@RequestBody Department department) {
		repo.save(department);
		return "New department added";
	}

    @PutMapping("/{id}")
	public String updateDept(@PathVariable("id") int id,@RequestBody Department department) {
		if(repo.findById(id).isEmpty()) {
			return "Couldn't find the department";
		}
		repo.save(department);
		return "The department updated";
	}

    @DeleteMapping("/{id}")
	public String deleteDept(@PathVariable("id") int id) {
			if(repo.findById(id).isEmpty()) {
				return "Couldn't find the department";
			}
			repo.deleteById(id);
			return "The department deleted";
	
	}
    */
```

## 📚 Repositories

01.DepartmentRepo.java
```java
package lk.vau.fas.ict.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.vau.fas.ict.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {

}

```

## 🧠 Service

01. DepartmentService.java
```java
package lk.vau.fas.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lk.vau.fas.ict.model.Department;
import lk.vau.fas.ict.repo.DepartmentRepo;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo repo;

    @GetMapping
    public List<Department>getDepts(){
		return repo.findAll();
	}

    public Department getDept( int id) {
		if(repo.findById(id).isEmpty()) {
			throw new EntityNotFoundException("Department Not Found");
		}
		return repo.findById(id).get();
	}

	public String addDept(Department department) {
		if(repo.findById(department.getId()).isPresent()){
			throw new DuplicateKeyException("The department id is already available");
		}
		repo.save(department);
		return "New department added";
	}

}
```

## 🔍 exceptinHandler

01. GenericExceptionHandler.java
```java
package lk.ac.vau.fas.ict.exceptionHandler;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import lk.ac.vau.fas.ict.model.ErrorResponse;

@RestControllerAdvice
public class GenericExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), 
				exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateKey(DuplicateKeyException exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleCommonExceptions(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				exception.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

```

## 📮 API Endpoints

| Method | Endpoint     | Description                 |
|--------|--------------|-----------------------------|
| GET    | `/dept`      | Get list of all departments |
| POST   | `/dept`      | Add a new department        |

## Outputs

01. Get list of all departments

![1](./screenshots/getall.png)

02.  Add a New Department

![2](./screenshots/add.png)


## 🧠 Key Classes Explained

- **Department.java**: Entity representing a department, includes ID, name, and established date.
- **Employee.java**: Entity representing an employee with a many-to-one relationship with `Department`.
- **DepartmentService.java**: Business logic to manage departments.
- **DepartmentController.java**: REST controller to expose department APIs.
- **GenericExceptionHandler.java**: Handles exceptions and returns proper HTTP responses.
- **ErrorResponse.java**: Custom model for error response formatting.

## ❗ Error Handling

Centralized exception handling using `@RestControllerAdvice` to manage:

- `EntityNotFoundException` → 404 Not Found
- `DuplicateKeyException` → 400 Bad Request
- General `Exception` → 500 Internal Server Error

## ✅ How to Run

01. Make sure you have MySQL running and the database employeedb is created.

02. Clone the repository.

03. Update application.properties with your MySQL credentials.

04. Run the application using your IDE or command line:

```
mvn spring-boot:run
```
05. Access the endpoints using a REST client like Postman or curl.

## 📌 Features

- **Create Departments** via REST API
- **Retrieve All Departments**
- **Associate Employees** with Departments (One-to-Many relationship)
- **Exception Handling** for:
  - Entity not found
  - Duplicate entries
  - General exceptions
- **Layered Architecture**: Controller → Service → Repository

## 🚀 Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Jakarta Persistence API (JPA)**
- **MySQL** (or any relational DB)
- **Jackson** for JSON serialization
- **Lombok** (optional)
- **Maven** for dependency management
