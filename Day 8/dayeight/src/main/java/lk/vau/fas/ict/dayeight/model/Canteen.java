package lk.vau.fas.ict.dayeight.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Canteen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @ManyToMany(mappedBy = "canteens")
    private List<Employee> employees;

    @OneToMany(mappedBy = "canteen")
    private List<DailyMenu> dailyMenus;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus) {
        this.dailyMenus = dailyMenus;
    }   
}