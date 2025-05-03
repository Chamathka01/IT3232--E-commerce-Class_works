package lk.vau.fas.ict.daysix.model;

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
	private long totalcost;

    @ManyToMany
	private List<Employee>employees;
    
}
