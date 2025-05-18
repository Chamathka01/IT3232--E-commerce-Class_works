package lk.vau.fas.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.vau.fas.ict.model.Department;
@RestController
@RequestMapping("/dept")
public class DepartmentController {
    @Autowired
    @GetMapping
	public List<Department>getDepts(){
		return repo.findAll();
	}


}
