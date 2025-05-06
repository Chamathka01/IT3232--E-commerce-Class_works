package lk.vau.fas.ict.dayeight.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DailyMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Canteen canteen;

    @ManyToMany
    @JoinTable(
        name = "daily_menu_food",
        joinColumns = @JoinColumn(name = "daily_menu_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}