package lk.vau.fas.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
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

    public ResponseEntity<List<Department>>getDepts(){
		return new ResponseEntity<List<Department>>
			(service.getDepts(),HttpStatus.OK);
	}
    @GetMapping("/{id}")
	public ResponseEntity<Department>getDepts(@PathVariable("id") int id){
		if(service.getDept(id)== null) {
			return new ResponseEntity<Department>
			(service.getDept(id),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Department>
		(service.getDept(id),HttpStatus.OK);
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


}
