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
