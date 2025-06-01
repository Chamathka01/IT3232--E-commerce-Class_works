# Department and Employee Management System

This is a Spring Boot-based RESTful web service for managing an employee database. It features department and employee management, custom error handling, and database queries using Spring Data JPA.

## 📁 Project Structure
```
lk.ac.vau.fas.ict
│
├── model
│   ├── Department.java
│   ├── Employee.java
│   ├── Project.java
│   ├── Person.java
│   ├── ViewDepartment.java
│   └── ErrorResponce.java
│
├── controller
│   ├── DepartmentController.java
│   └── EmployeeController.java
│
├── service
│   ├── DepartmentService.java
│   └── EmployeeService.java
│
├── repo
│   ├── DepartmentRepo.java
│   └── EmployeeRepo.java
│
└── exceptionHandler
    └── GenericExceptionHandler.java

```

## 🗃️ Database Configuration
The system connects to a MySQL database. Configure `application.properties`:
```
spring.application.name=ict
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create
```

## 📦 Models

01. Department.java
```java
package lk.ac.vau.fas.ict.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Department {
	@Id
	@Column(name = "dept_id")
	private int id;
	@Column(nullable = false)
	private String name;
	private Date established;
	@JsonIgnore
	@OneToMany(mappedBy = "department")
	private List<Employee>employees;
	
	
	public Department(int id, String name, Date established) {
		this.id = id;
		this.name = name;
		this.established = established;
	}

	public Department() {
		
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}

```

02. Employee.java
```java
package lk.ac.vau.fas.ict.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee extends Person {
	@Id
	private String empNo;
	private double salary;
	@ManyToOne
	private Department department;
	@ManyToMany(mappedBy = "employees")
	private List<Project>projects;
	public Employee(String empNo, double salary, Department department, List<Project> projects) {
		super();
		this.empNo = empNo;
		this.salary = salary;
		this.department = department;
		this.projects = projects;
	}
	public Employee() {
		super();
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

04. Person.java
```java
package lk.ac.vau.fas.ict.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
	private String name;
	private int age;
	private String gender;
}

```

05. Project.java
```java
package lk.ac.vau.fas.ict.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
@Entity
public class Project {
	@Id
	private int id;
	private String name;
	private long totalCost;
	@ManyToMany
	@JoinTable(name="project_assignments",
	joinColumns = @JoinColumn(name="pro_id"),
	inverseJoinColumns = @JoinColumn(name="emp_id"))
	private List<Employee>employees;
}

```

06. ViewDepartment.java
```java
package lk.ac.vau.fas.ict.model;

import java.sql.Date;

public class ViewDepartment extends Department {
	private int empcount;
	public ViewDepartment() {
		
	}
	public ViewDepartment(int id, String name, Date established, int employees) {
		super(id, name, established);
		this.empcount = employees;
	}
	public int getEmpcount() {
		return empcount;
	}
	public void setEmpcount(int empcount) {
		this.empcount = empcount;
	}	
}

```


##  📁 Controllers

01. DepartmertController.java
```java
package lk.ac.vau.fas.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ac.vau.fas.ict.model.Department;
import lk.ac.vau.fas.ict.model.ViewDepartment;
import lk.ac.vau.fas.ict.service.DepartmentService;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDept(@PathVariable("id") int id) {
		return new ResponseEntity<Department>(service.getDept(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addDept(@RequestBody Department department) {
		return new ResponseEntity<String>(service.addDept(department),HttpStatus.OK);
	}
	/*
	@PutMapping("/{id}")
	public String updateDept(@PathVariable("id") int id,@RequestBody Department department) {
		if(repo.findById(id).isEmpty()) {
			return "couldn't find the department";
		}
		repo.save(department);
		return "the department updated";
	}
	@DeleteMapping("/{id}")
	public String deleteDept(@PathVariable("id") int id) {
		if(repo.findById(id).isEmpty()) {
			return "couldn't find the department";
		}
		repo.deleteById(id);
		return "the department deleted";
	}*/
	@GetMapping("/names")
	public ResponseEntity<List<String>>getNames(){
		return new ResponseEntity<List<String>>
		(service.getDepartmentNames(),HttpStatus.OK);
	}
	

	@GetMapping("/search/{nm}")
	public ResponseEntity<List<Department>>searchDepts(@PathVariable("nm") String name){
		return new ResponseEntity<List<Department>>
		(service.searchDepartmentByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/empcount/{id}")
	public ResponseEntity<Integer> countEmp(@PathVariable("id") int id) {
		return new ResponseEntity<Integer>(service.getEmpCount(id),HttpStatus.OK);
	}
	@GetMapping("/vempcount/{id}")
	public ResponseEntity<ViewDepartment> vcountEmp(@PathVariable("id") int id) {
		return new ResponseEntity<ViewDepartment>(service.getEmpCountView(id),HttpStatus.OK);
	}
}

```

02. EmployeeController.java
```java
package lk.ac.vau.fas.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.ac.vau.fas.ict.model.Department;
import lk.ac.vau.fas.ict.model.Employee;
import lk.ac.vau.fas.ict.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/salary/{a}/{b}")
	public ResponseEntity<List<Employee>>searchBySalary(@PathVariable("a") int s,@PathVariable("b") int e){
		return new ResponseEntity<List<Employee>>
		(service.searchSalary(s, e),HttpStatus.OK);
	}

}

```

## 📚 Repositories

01.DepartmentRepo.java
```java
package lk.ac.vau.fas.ict.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.ac.vau.fas.ict.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	// JPQL
	@Query("select name from Department")
	public List<String> getDeptNames();

	@Query("select d from Department d where d.name like %?1%")
	public List<Department> searchName(String name);

	@Query("select count(*) from Department d "
			+ "join d.employees "
			+ "where d.id= ?1")
	public int numberOfEmp(int did);
}
```

02.EmployeeRepo.java
```java
package lk.ac.vau.fas.ict.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.ac.vau.fas.ict.model.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
	
	//find the employees with the salary range 70000 to 90000
	@Query("select e from Employee e where e.salary between ?1 and ?2")
	public List<Employee>findSalaryRange(int b,int e);
	//find the employees who are working in a department (pass dept id)
	@Query("select e from Employee e where e.department.id = ?1")
	public List<Employee>findByDept(int did);

}

```

## 🧠 Service

01. DepartmentService.java
```java
package lk.ac.vau.fas.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lk.ac.vau.fas.ict.model.Department;
import lk.ac.vau.fas.ict.model.ViewDepartment;
import lk.ac.vau.fas.ict.repo.DepartmentRepo;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepo repo;

	public List<Department> getDepts() {
		return repo.findAll();
	}

	public Department getDept(int id) {
		if (repo.findById(id).isEmpty()) {
			throw new EntityNotFoundException("Department Not Found");
		}
		return repo.findById(id).get();
	}

	public String addDept(Department department) {
		// before adding a department make sure that the department id is unique
		if (repo.findById(department.getId()).isPresent()) {
			throw new DuplicateKeyException("The department id is already available");
		}
		repo.save(department);
		return "New department added";
	}
	// update

	public List<String> getDepartmentNames() {
		if (repo.getDeptNames().isEmpty()) {
			throw new EntityNotFoundException("Department Not Found");
		}
		return repo.getDeptNames();
	}

	public List<Department> searchDepartmentByName(String name) {
		if (repo.searchName(name).isEmpty()) {
			throw new EntityNotFoundException("Department Not Found");
		}
		return repo.searchName(name);
	}

	public int getEmpCount(int id) {
		if (repo.findById(id).isEmpty()) {
			throw new EntityNotFoundException("Department Not Found");
		}
		return repo.numberOfEmp(id);
	}

	public ViewDepartment getEmpCountView(int id) {
		if (repo.findById(id).isEmpty()) {
			throw new EntityNotFoundException("Department Not Found");
		}
		Department department = repo.findById(id).get();
		ViewDepartment viewDepartment = new ViewDepartment(department.getId(), department.getName(),
				department.getEstablished(), getEmpCount(id));
		return viewDepartment;
	}
}

```

02. EmployeeService.java
```java
package lk.ac.vau.fas.ict.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lk.ac.vau.fas.ict.model.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
	
	//find the employees with the salary range 70000 to 90000
	@Query("select e from Employee e where e.salary between ?1 and ?2")
	public List<Employee>findSalaryRange(int b,int e);
	//find the employees who are working in a department (pass dept id)
	@Query("select e from Employee e where e.department.id = ?1")
	public List<Employee>findByDept(int did);
}

```

## 🔍 exceptionHandler

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

## 🌐 REST API Endpoints

01. DepartmentController

- GET /dept – Get all departments

- GET /dept/{id} – Get department by ID

- POST /dept – Add new department

- GET /dept/names – Get all department names

- GET /dept/search/{name} – Search departments by name

- GET /dept/empcount/{id} – Get employee count for department

- GET /dept/vempcount/{id} – Get detailed view with employee count

02. EmployeeController

- GET /emp/salary/{min}/{max} – Get employees within a salary range

## Outputs

01. DepartmentController

- GET /dept – Get all departments
![1](./screenshots/getall.png)

- GET /dept/{id} – Get department by ID
![2](./screenshots/getdepartment.png)

- POST /dept – Add new department
![3](./screenshots/adddept.png)

- GET /dept/names – Get all department names
![4](./screenshots/alldeptnames.png)

- GET /dept/search/{name} – Search departments by name
![5](./screenshots/searchdeptname.png)

- GET /dept/empcount/{id} – Get employee count for department
![6](./screenshots/getempcount.png)

- GET /dept/vempcount/{id} – Get detailed view with employee count
![7](./screenshots/getviewempcount.png)

02. EmployeeController

- GET /emp/salary/{min}/{max} – Get employees within a salary range
![1](./screenshots/getempsalary.png)


## 🧠 Key Classes Explained

- **Department.java**: Represents a department entity with ID, name, establishment date, and associated employees.
- **Employee.java**: Defines an employee with salary, department, and project associations, inheriting from `Person`.
- **Project.java**: Represents a project with an ID, name, cost, and a many-to-many relationship with employees
- **Person.java**: A base class (mapped superclass) containing common fields like name, age, and gender.
- **ViewDepartment.java**: A custom class extending `Department` to add the employee count for viewing purposes.
- **ErrorResponse.java**: Defines the structure of error messages returned by the exception handler.

- **DepartmentService.java**: Provides business logic for operations like retrieving departments, adding a new department, searching, and counting employees.
- **EmployeeService.java**: Handles employee-related logic like searching for employees within a salary range.

- **DepartmentController.java**: REST controller exposing department-related APIs like list, add, search, and count employees.
- **EmployeeController.java**: REST controller exposing employee-related APIs like salary range search.

- **DepartmentRepo.java**: Data access layer for Department, includes custom queries for names, search, and employee count.
- **EmployeeRepo.java**: Data access layer for Employee, includes queries for salary range and employees by department.

- **GenericExceptionHandler.java**: Handles exceptions like entity not found or duplicate key globally and returns meaningful HTTP responses.


## ✅ How to Run

01. Make sure you have MySQL running and the database employeedb is created.

02. Clone the repository.

03. Update application.properties with your MySQL credentials.

04. Run the application using your IDE or command line:

```
mvn spring-boot:run
```
05. Access the endpoints using a REST client like Postman or curl.

## ✅ Features

### 🏢 Department Management
- Add new departments with name and established date.
- View all departments.
- Search departments by name (case-insensitive).
- Get employee count in each department.

### 👨‍💼 Employee Management
- Add employees with personal info, salary, and department/project association.
- Retrieve all employees or filter by department.
- Search employees within a salary range.

### 🧾 Project Assignment
- Create and manage project records.
- Assign multiple employees to a project.

### 🔍 Custom Queries
- Case-insensitive department search.
- Employee count per department.
- Employee salary range search.

### 🧱 Architecture
- Clean separation using Controller, Service, Repository layers.
- Reusable Person superclass.
- Custom view model: `ViewDepartment` for enriched responses.

### 🚨 Exception Handling
- Centralized global error handling via `@ControllerAdvice`.

## 🛠️ Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Jakarta Persistence API (JPA)**
- **MySQL** (or any relational DB)
- **Postman (for testing)**
- **Maven** for dependency management
- **RESTful Web Services**
