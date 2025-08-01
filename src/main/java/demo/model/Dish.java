package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties({"description", "price"})
public class Dish {

    private int id;
    private String name;
    private String description;
    private int price;
    private int idCategory;

    public Dish(int id, String name, String description, int price, int idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
    }

    public Dish(int id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish(int id, String name, int idCategory) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
    }

    public Dish(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dish() {
    }

    public Dish(int idCategory) {
        this.idCategory = idCategory;
    }

    public Dish(String name, int idCategory) {
        this.name = name;
        this.idCategory = idCategory;
    }

    public Dish(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id && price == dish.price && Objects.equals(name, dish.name) && Objects.equals(description, dish.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }
}
