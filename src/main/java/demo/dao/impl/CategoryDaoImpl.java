package demo.dao.impl;

import demo.dao.CategoryDao;
import demo.model.Category;
import demo.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final DataBase dataBase;

    public CategoryDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public List<Category> findAll() {
        List<Category> activCategoryList = new ArrayList<>();
        List<Category> categoryList = dataBase.findAllCategory();
        if(categoryList != null && !categoryList.isEmpty()){
            for (Category category : categoryList){
                int categoryId = category.getId();
                String categoryName = category.getName();
                Category activCategory = new Category(categoryId, categoryName);
                activCategoryList.add(activCategory);
            }
        }
        return activCategoryList;
    }

    @Override
    public Category findById(int id) {
        List<Dish> activDishList = new ArrayList<>();
        List<Category> categoryList = dataBase.findAllCategory();
        if(categoryList != null && !categoryList.isEmpty()){
            for (Category category : categoryList){
                int categoryId = category.getId();
                String categoryName = category.getName();
                if (categoryId == id){
                    List<Dish> dishList = category.getDishList();
                    for (Dish dish : dishList){
                        int dishId = dish.getId();
                        String dishName = dish.getName();
                        Dish activDish = new Dish(dishId, dishName);
                        activDishList.add(activDish);
                    }
                    Category activCategory = new Category(categoryId, categoryName, activDishList);
                    return activCategory;
                }
            }
        }
        return null;
    }

    @Override
    public Category create(Category category) {
        List<Category> categoryList = dataBase.findAllCategory();
        for (Category currentCategory : categoryList) {
            String currentCategoryName = currentCategory.getName().toLowerCase();
            String pullCategoryName = category.getName().toLowerCase();
            if (currentCategoryName.equalsIgnoreCase(pullCategoryName)) { //проверка есть ли уже такая категоря
                throw new IllegalArgumentException("Категория " + category.getName() + " уже существует!");
            }
        }
            List<Dish> newCategoryDishList = new ArrayList<>();
            List<Dish> currentDishList = dataBase.findAllDish(); // получаем имеющийся список блюд
            for (Dish currentDish : currentDishList) {
                String currentDishName = currentDish.getName().toLowerCase();
                String categoryName = category.getName().toLowerCase();
                if (currentDishName.contains(categoryName) || categoryName.contains(currentDishName)) { //если находим совпадающие блюда добавляем
                    newCategoryDishList.add(currentDish);
                }
            }
            if (newCategoryDishList.isEmpty()){
                Category newCategory = new Category(dataBase.generationIdCategory(), category.getName());
                categoryList.add(newCategory);
            }else {
                Category newCategory = new Category(dataBase.generationIdCategory(), category.getName(), newCategoryDishList);
                categoryList.add(newCategory);
            }
            return category;
    }

    @Override
    public Category update(Category category) {

        int pullCategoryId = category.getId();
        String pullCategoryName = category.getName().toLowerCase();
        List<Dish> pullCategoryDishList = category.getDishList();
        List<Dish> pullCategoryDishListCopy = new ArrayList<>(pullCategoryDishList);
        String dishNamePull = null;

        List<Category> categoryList = dataBase.findAllCategory();
        for (Category currentCategory : categoryList) {
            int currentCategoryId = currentCategory.getId();
            if (pullCategoryId != currentCategoryId) {
                throw new IllegalArgumentException("Идентификатор " + category.getId() + " не найден!");
            }
        }
        for (Dish pullCategoryDishName : pullCategoryDishList) {
            String dishName = pullCategoryDishName.getName().toLowerCase();
            dishNamePull = dishName;
            if (!pullCategoryName.contains(dishName) || !dishName.contains(pullCategoryName)) {
                throw new IllegalArgumentException("Блюда не соответсвуют категории!");
            }
        }
        for (Category currentCategory : categoryList) {
            String currentCategoryName = currentCategory.getName().toLowerCase();
            List<Dish> currentCategoryDishList = currentCategory.getDishList();
            List<Dish> currentCategoryDishListCopy = new ArrayList<>(currentCategoryDishList);
            if (pullCategoryName.equalsIgnoreCase(currentCategoryName)) {
                currentCategoryDishListCopy.removeAll(pullCategoryDishList);
                pullCategoryDishListCopy.removeAll(currentCategoryDishList);
            }
            if (currentCategoryDishListCopy.isEmpty() && pullCategoryDishListCopy.isEmpty()) {
                return category;
            }
            if (!currentCategoryDishListCopy.isEmpty() && pullCategoryDishListCopy.isEmpty()) {
                currentCategoryDishList.removeAll(currentCategoryDishListCopy);
            }
            if (currentCategoryDishListCopy.isEmpty() && !pullCategoryDishListCopy.isEmpty()) {
                currentCategoryDishList.addAll(pullCategoryDishListCopy);
            }
        }
        for (Category currentCategory : categoryList) {
            String currentCategoryName = currentCategory.getName().toLowerCase();
            int currentCategoryId = currentCategory.getId();
            if (pullCategoryId == currentCategoryId && !currentCategoryName.contains(dishNamePull) || !dishNamePull.contains(currentCategoryName)) {
                throw new IllegalArgumentException("Блюда не соответсвуют категории!");
            }
            String newCategoryName = currentCategory.setName(pullCategoryName);
            if (!newCategoryName.toLowerCase().contains(dishNamePull) || !dishNamePull.contains(newCategoryName.toLowerCase())) {
                throw new IllegalArgumentException("Блюда не соответсвуют категории!");
            }
            List<Dish> currentCategoryDishList = currentCategory.getDishList();
            List<Dish> currentCategoryDishListCopy = new ArrayList<>(currentCategoryDishList);
            currentCategoryDishListCopy.removeAll(pullCategoryDishList);
            pullCategoryDishListCopy.removeAll(currentCategoryDishList);
        if (currentCategoryDishListCopy.isEmpty() && pullCategoryDishListCopy.isEmpty()) {
            return category;
        }
        if (!currentCategoryDishListCopy.isEmpty() && pullCategoryDishListCopy.isEmpty()) {
            currentCategoryDishList.removeAll(currentCategoryDishListCopy);
        }
        if (currentCategoryDishListCopy.isEmpty() && !pullCategoryDishListCopy.isEmpty()) {
            currentCategoryDishList.addAll(pullCategoryDishListCopy);
        }
    }
        return null;
    }

    @Override
    public void deleteById(int id) {
        List<Category> categoryList = dataBase.findAllCategory();
        if (categoryList != null && !categoryList.isEmpty()){
            for (Category category : categoryList){
                int categoryId = category.getId();
                if (id != categoryId){
                    throw new IllegalArgumentException("Идентификатор " + id + " не найден!");
                }
                if (id == categoryId){
                    List<Dish> categoryDishList = category.getDishList();
                    List<Dish> dishList = dataBase.findAllDish();
                    dishList.removeAll(categoryDishList);
                    categoryList.remove(category);
                }
            }
        }
    }
}
