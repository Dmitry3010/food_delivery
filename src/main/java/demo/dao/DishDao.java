package demo.dao;

import demo.model.Category;
import demo.model.Dish;

import java.util.List;

public interface DishDao {

    List<Dish> findAll();//работает

    Dish create(Dish dish);

    Dish findById(int id);//рабоает

    Dish update(Dish dish);

    void deleteById(int id);//не работает
}
