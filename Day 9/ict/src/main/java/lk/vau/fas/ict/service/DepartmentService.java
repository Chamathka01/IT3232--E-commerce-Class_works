package lk.vau.fas.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public Department getDept(@PathVariable("id") int id) {
		if(repo.findById(id).isEmpty()) {
			return null;
		}
		return repo.findById(id).get();
	}

}
