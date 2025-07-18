package demo.dao.impl;

import demo.dao.CategoryDao;
import demo.dao.DishDao;
import demo.model.Category;
import demo.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DishDaoImpl implements DishDao {

    private final DataBase dataBase;
    private final CategoryDao categoryDao;

    public DishDaoImpl(DataBase dataBase, CategoryDao categoryDao) {
        this.dataBase = dataBase;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Dish> findAll() {
        List<Dish> dishList = dataBase.findAllDish();
        if (dishList != null && dishList.isEmpty()) {
            throw new IllegalStateException("Список блюд пуст!");
        }
        return dishList;
    }

    @Override
    public Dish findById(int id) {
        List<Dish> dishList = dataBase.findAllDish();
        if (dishList != null && !dishList.isEmpty()) {
            for (Dish dish : dishList) {
                int dishId = dish.getId();
                if (dishId == id) {
                    return dish;
                }
            }
        }
        return null;
    }

    @Override
    public Dish create(Dish dish) {
        List<Dish> dishList = dataBase.findAllDish();
        for (Dish currentDish : dishList) {
            String nameCurrentDish = currentDish.getName();
            if (nameCurrentDish.equalsIgnoreCase(dish.getName())) {
                throw new IllegalArgumentException("Блюдо " + dish.getName() + " уже существует!");
            }
            String pullDishName = dish.getName().toLowerCase();
            Category pullDishCategory = dish.getCategory();
            String pullDishCategoryName = pullDishCategory.getName().toLowerCase();
            if (!pullDishName.contains(pullDishCategoryName) || !pullDishCategoryName.contains(pullDishName)) {
                throw new IllegalArgumentException("Блюдо " + dish.getName() + " не соответствует категории!");
            } else {
                List<Category> categoryList = dataBase.findAllCategory();
                for (Category category : categoryList) {
                    String categoryName = category.getName().toLowerCase();
                    if (categoryName.equalsIgnoreCase(pullDishCategoryName)) {
                        dish.setId(dataBase.generationIdDish());
                        dishList.add(dish);
                    }
                }
                Category newCategory = categoryDao.create(pullDishCategory);
                dish.setCategory(newCategory);
                dish.setId(dataBase.generationIdDish());
                dishList.add(dish);
            }
        }
        return dish;
    }

    @Override
    public Dish update(Dish dish) {
        String pullDishName = dish.getName().toLowerCase();
        List<Dish> dishList = dataBase.findAllDish();
        for (Dish currentDish : dishList){
            String currentDishName = currentDish.getName().toLowerCase();
            if (!pullDishName.equalsIgnoreCase(currentDishName)){
                throw new IllegalArgumentException("Блюдо " + dish.getName() + " не нашлось в списке!");
            }
        }
        Category pullCategoryDish = dish.getCategory();
        String pullCategoryDishName = pullCategoryDish.getName().toLowerCase();
        for (Dish currentDish : dishList){
            String currentDishName = currentDish.getName().toLowerCase();
            Category currentDishCategory = currentDish.getCategory();
            String currentDishCategoryName = currentDishCategory.getName().toLowerCase();
            if (pullDishName.equalsIgnoreCase(currentDishName) && pullCategoryDishName.equalsIgnoreCase(currentDishCategoryName)){
                return dish;
            }
        }
           for (Dish currentDish : dishList){
               String currentDishName = currentDish.getName().toLowerCase();
               if (pullDishName.equalsIgnoreCase(currentDishName) && pullDishName.contains(pullCategoryDishName) || pullCategoryDishName.contains(pullDishName)){
                   currentDish.setCategory(pullCategoryDish);
               }
           }
        if (!pullDishName.contains(pullCategoryDishName) || !pullCategoryDishName.contains(pullDishName)){
            throw new IllegalArgumentException("Блюдо " + dish.getName() + " не подходит к категории");
        }
        return dish;
    }

    @Override
    public void deleteById(int id) {
        List<Dish> countDish = new ArrayList<>();
        List<Dish> dishList = dataBase.findAllDish();
        if (dishList != null && !dishList.isEmpty()){
            for (Dish dish : dishList){
                int dishId = dish.getId();
                if (id != dishId){
                    throw new IllegalArgumentException("Идентификатор " + id + " не найден!");
                }
                if (id == dishId){
                    Category pullDish = dish.getCategory();
                    for (Dish dishCategory : dishList){
                        Category dishCategoryCurrent = dishCategory.getCategory();
                        if (pullDish.getName().toLowerCase().equalsIgnoreCase(dishCategoryCurrent.getName().toLowerCase())){
                            countDish.add(dishCategory);
                        }
                    }
                    if (countDish.size() == 1){
                        throw new IllegalStateException("Последнее блюдо в категории!");
                    }
                    if (countDish.size() > 1){
                        dishList.remove(dish);
                    }
                }
            }
        }
    }
}

