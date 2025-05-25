package lk.vau.fas.ict.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.vau.fas.ict.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {

}

