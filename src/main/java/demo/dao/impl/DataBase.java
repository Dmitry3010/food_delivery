package demo.dao.impl;

import demo.model.Category;
import demo.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataBase {

    private List<Category> categoryList = new ArrayList<>();
    private List<Dish> burgerList = new ArrayList<>();
    private List<Dish> saladList = new ArrayList<>();
    private List<Dish> dishList = new ArrayList<>();

    private int idDish = 0;
    private int idCatgegory = 0;

    public int generationIdDish() {
        idDish++;
        int nextId = idDish;
        return nextId;
    }

    public int generationIdCategory() {
        idCatgegory++;
        int nextId = idCatgegory;
        return nextId;
    }

    {
        Category burger = new Category("Burger");
        Category salad = new Category("Salad");

        Dish beefBurger = new Dish(generationIdDish(), "Beef Burger", "Булочка с кунжутом, котлета из говядины, салат, помидор, огурец", 750, burger);
        Dish chickenBurger = new Dish(generationIdDish(), "Chicken Burger", "Булочка с сыром, куриная котлета, салат, помидор, огурец", 650, burger);

        Dish greekSalad = new Dish(generationIdDish(), "Greek Salad", "Салат с помидором, огурцом, сыр Фетакса, оливки", 300, salad);
        Dish caesarSalad = new Dish(generationIdDish(), "Caesar Salad", "Салат с помидором, листьями салата, сыр, сухари, оливки", 400, salad);

        dishList.add(beefBurger);
        dishList.add(chickenBurger);
        dishList.add(greekSalad);
        dishList.add(caesarSalad);
    }

    {
        Dish beefBurger = new Dish(generationIdDish(), "Beef Burger", "Булочка с кунжутом, котлета из говядины, салат, помидор, огурец", 750);
        Dish chickenBurger = new Dish(generationIdDish(), "Chicken Burger", "Булочка с сыром, куриная котлета, салат, помидор, огурец", 650);
        burgerList.add(beefBurger);
        burgerList.add(chickenBurger);
        Category burger = new Category(generationIdCategory(), "Burger", burgerList);

        Dish greekSalad = new Dish(generationIdDish(), "Greek Salad", "Салат с помидором, огурцом, сыр Фетакса, оливки", 300);
        Dish caesarSalad = new Dish(generationIdDish(), "Caesar Salad", "Салат с помидором, листьями салата, сыр, сухари, оливки", 400);
        saladList.add(greekSalad);
        saladList.add(caesarSalad);
        Category salad = new Category(generationIdCategory(), "Salad", saladList);

        categoryList.add(burger);
        categoryList.add(salad);
    }

    public List<Dish> findAllDish() {
        return dishList;
    }

    public List<Category> findAllCategory(){
        return categoryList;
    }

}
