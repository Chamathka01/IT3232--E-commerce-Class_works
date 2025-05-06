package lk.vau.fas.ict.daysix.model;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
    @Id
    @Column(name="dept_id")
    private int id;
    @Column(nullable=false)
    private String name;
    private Date established;

    @OneToMany(mappedBy="department")
	private List<Employee>employees;
}
