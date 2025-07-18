package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties({"dishList"})
public class Category {

    private int id;
    private String name;
    private List<Dish> dishList;

    public Category(int id, String name, List<Dish> dishList) {
        this.id = id;
        this.name = name;
        this.dishList = dishList;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name, List<Dish> dishList) {
        this.name = name;
        this.dishList = dishList;
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public List<Dish> setDishList(List<Dish> dishList) {
        this.dishList = dishList;
        return dishList;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishList=" + dishList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(dishList, category.dishList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishList);
    }
}