package lk.vau.fas.ict.dayeight.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private double weight;

    @OneToMany(mappedBy = "food")
    private List<OrderLine> orderLines;

    @ManyToMany(mappedBy = "foods")
    private List<DailyMenu> dailyMenus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<DailyMenu> getDailyMenus() {
        return dailyMenus;
    }

    public void setDailyMenus(List<DailyMenu> dailyMenus) {
        this.dailyMenus = dailyMenus;
    }
}