package lk.vau.fas.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.vau.fas.ict.model.Department;
@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
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
    


}
