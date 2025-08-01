package demo.service.impl;

import demo.dao.DishDao;
import demo.dto.CategoryDishFilter;
import demo.model.Category;
import demo.model.Dish;
import demo.service.CategoryService;
import demo.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishDao dishDao;
    private final CategoryService categoryService;

    public DishServiceImpl(DishDao dishDao, CategoryService categoryService) {
        this.dishDao = dishDao;
        this.categoryService = categoryService;
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> dishList = dishDao.findAll();
       return dishList;
    }

    @Override
    public Dish getById(int id) { //получить блюдо (получаем по ID выводим ID, название, описание, стоимость)
        Dish dish = dishDao.findById(id);
        return dish;
    }

    @Override//создать блюдо присвоив категорию (должна быть валидация на логичность категории, при назначении неверной категории выводим ошибку (ексепшен))
    public Dish create(Dish dish) {
        String dishName = dish.getName().trim().toLowerCase();
        List<Dish> dishList = dishDao.findAll();
        for (Dish currentDish : dishList){
            String currentDishName = currentDish.getName().toLowerCase();
            if (currentDishName.equals(dishName)){
                throw new IllegalArgumentException("Блюдо " + dish.getName() + " уже существует!");
            }
        }
        // проверка на правильность категории
        int idCategory = dish.getIdCategory();
        Category currentCategory = categoryService.getById(idCategory);
        CategoryDishFilter filter = categoryService.getkeywordsById(idCategory);
        if (!dishName.contains(filter.getDishName().toLowerCase())){
            throw new IllegalArgumentException("Блюдо " + dish.getName() + " не соответствует категории!");
        }
        Dish dishCreate = dishDao.create(dish);
        List<Dish> categoryDishList = currentCategory.getDishList();
        List<Dish> categoryIdDishList = dishDao.findByCategoryId(idCategory);
        categoryDishList.addAll(categoryIdDishList);
        return dishCreate;
    }

    @Override
    public Dish update(Dish dish) { //обновить блюдо (сменить категорию)
        int dishId = dish.getId();
        Dish currentDish = dishDao.findById(dishId);
        String currentDishName = currentDish.getName().toLowerCase();
        int dishCategoryId = dish.getIdCategory();
        Category dishCategory = categoryService.getById(dishCategoryId);
        String dishCategoryName = dishCategory.getName().toLowerCase();
        CategoryDishFilter filter = categoryService.getkeywordsById(dishCategoryId);
        if (dishCategoryName.contains(filter.getCategoryName()) && currentDishName.contains(filter.getDishName())){
            Dish dishUpdate = dishDao.update(dish);
            return dishUpdate;
        }
        throw new IllegalArgumentException("Блюдо " + currentDishName + " не подходит к категории");
    }

    @Override
    public boolean deleteById(int id) {
        Dish deleteDish = dishDao.findById(id);
        int deleteDishId = deleteDish.getId();
        dishDao.deleteById(deleteDishId);
        return true;
    }

    @Override
    public void deleteByCategoryId(int id) {
        Dish deleteDish = dishDao.findById(id);
        int deleteDishCategoryId = deleteDish.getIdCategory();
        dishDao.deleteByCategoryId(deleteDishCategoryId);
    }

    @Override
    public List<Dish> getByCategoryId(int categoryId) {
        List<Dish> dishCategoryList = dishDao.findByCategoryId(categoryId);
        if (dishCategoryList != null && dishCategoryList.isEmpty()) {
            throw new IllegalStateException("Нет подходящих блюд!");
        }
        return dishCategoryList;
    }
}
