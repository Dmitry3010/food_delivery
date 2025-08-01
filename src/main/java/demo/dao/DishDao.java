package demo.dao;

import demo.model.Category;
import demo.model.Dish;

import java.util.List;

public interface DishDao {

    List<Dish> findAll();

    Dish create(Dish dish);

    Dish findById(int id);//рабоает

    Dish update(Dish dish);

    boolean deleteById(int id);

    void deleteByCategoryId(int id);

    List<Dish> findByCategoryId(int categoryId);
}
