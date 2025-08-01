package demo.dao.impl;

import demo.dao.DishDao;
import demo.model.Category;
import demo.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DishDaoImpl implements DishDao {

    private List<Dish> dishList = new ArrayList<>();

    private int dishId = 1;

    public DishDaoImpl() {
        create(new Dish(0, "Beef Burger", "Бургер с говядиной", 750, 1));
        create(new Dish(0, "Chicken Burger", "Бургер с курицей", 650, 1));
        create(new Dish(0, "Greek Salad", "Греческий салат", 300, 2));
        create(new Dish(0, "Caesar Salad", "Салат Цезарь", 400, 2));
    }

    @Override
    public List<Dish> findAll() {
        if (dishList != null && dishList.isEmpty()) {
            throw new IllegalStateException("Список блюд пуст!");
        }
        return dishList;
    }

    @Override
    public Dish findById(int id) {
        if (dishList != null && dishList.isEmpty()) {
            throw new IllegalStateException("Список блюд пуст!");
        }
            for (Dish dish : dishList) {
                if (dish.getId() == id) {
                    return dish;
                }
            }
        throw new IllegalArgumentException("Блюдо c ID " + id + " не нашлось в списке!");
    }

    @Override
    public Dish create(Dish dish) {
        dish.setId(dishId++);
        dishList.add(dish);
        return dish;
    }

    @Override
    public Dish update(Dish dish) {
        int dishId = dish.getId();
        int dishCategoryId = dish.getIdCategory();
        for (Dish currentDish : dishList){
            int currentDishId = currentDish.getId();
            if (dishId == currentDishId){
                currentDish.setIdCategory(dishCategoryId);
                currentDish.setName(dish.getName());
            }
        }
        return dish;
    }

    @Override
    public boolean deleteById(int id) {
        List<Dish> deleteDish = new ArrayList<>();
        for (Dish dish : dishList) {
            if (id == dish.getId()) {
                deleteDish.add(dish);
            }
        }
        dishList.removeAll(deleteDish);
        return true;
    }

    @Override
    public void deleteByCategoryId(int id) {
        List<Dish> deleteDishList = new ArrayList<>();
        for (Dish dish : dishList) {
            if (id == dish.getIdCategory()) {
                deleteDishList.add(dish);
            }
        }
        dishList.removeAll(deleteDishList);
    }

    @Override
    public List<Dish> findByCategoryId(int categoryId) {
        if (dishList != null && dishList.isEmpty()) {
            throw new IllegalStateException("Список блюд пуст!");
        }
        List<Dish> dishCategoryIdList = new ArrayList<>();
        for (Dish dish : dishList){
            int dishIdCategory = dish.getIdCategory();
            if (dishIdCategory == categoryId){
                dishCategoryIdList.add(dish);
            }
        }
        return dishCategoryIdList;
    }
}

