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
