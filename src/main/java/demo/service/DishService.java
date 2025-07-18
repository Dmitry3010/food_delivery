package demo.service;

import demo.model.Category;
import demo.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAll();

    Dish create(Dish dish);

    Dish getById(int id);

    Dish update(Dish dish);

    void deleteById(int id);
}
