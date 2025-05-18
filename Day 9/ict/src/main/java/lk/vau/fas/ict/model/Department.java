package lk.vau.fas.ict.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Department {
    @Id
    @Column(name="dept_id")
    private int id;
    @Column(nullable=false)
    private String name;
    private Date established;
    @OneToMany(mappedBy="department")
	private List<Employee>employees;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getEstablished() {
        return established;
    }
    public void setEstablished(Date established) {
        this.established = established;
    }

    
}
