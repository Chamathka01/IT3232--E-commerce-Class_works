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
