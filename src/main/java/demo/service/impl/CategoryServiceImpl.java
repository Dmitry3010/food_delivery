package demo.service.impl;

import demo.dao.CategoryDao;
import demo.dto.CategoryDishFilter;
import demo.model.Category;
import demo.model.Dish;
import demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = categoryDao.findAll();
        return categoryList;
    }

    @Override
    public CategoryDishFilter getkeywordsById(int categoryId) {
        return categoryDao.findkeywordsById(categoryId);
    }

    @Override
    public Category getById(int id) {
        Category category = categoryDao.findById(id);
        return category;
    }

    @Override
    public Category create(Category category) {
        String categoryName = category.getName().toLowerCase();
        List<Category> currentCategoryList = categoryDao.findAll();
        for (Category currentCategory : currentCategoryList) {
            String currentCategoryName = currentCategory.getName();
            if (currentCategoryName.equals(categoryName)) { //проверка есть ли уже такая категоря
                throw new IllegalArgumentException("Категория " + category.getName() + " уже существует!");
            }
        }
//        List<Dish> categoryDishList = category.getDishList();
//        for (Dish categoryDish : categoryDishList){
//            String categoryDishName = categoryDish.getName().toLowerCase();
//            if (!categoryDishName.contains(categoryName)){
//                throw new IllegalArgumentException("Блюдо " + categoryDishName + " не соответствует категории!");
//            }
//        }
        Category newCategory = categoryDao.create(category);
        return newCategory;
    }

    @Override
    public Category update(Category category) { //обновить категорию (изменить название категории, список блюд (например убрать лишнее блюдо либо добавить))
        int categoryId = category.getId();
        String categoryName = category.getName().toLowerCase();
        List<Dish> categoryDishList = category.getDishList();
        CategoryDishFilter filter = categoryDao.findkeywordsById(categoryId);
        if (filter.getCategoryName().equals(categoryName)) {
            for (Dish categoryDish : categoryDishList) {
                String categoryDishName = categoryDish.getName().toLowerCase();
                if (!categoryDishName.contains(filter.getDishName())) {
                    throw new IllegalArgumentException("Блюда не соответсвуют категории!");
                }
            }
        }
        return categoryDao.update(category);
    }

    @Override
    public boolean deleteById(int id) {
        Category deleteCategory = categoryDao.findById(id);
        int deleteCategoryId = deleteCategory.getId();
        categoryDao.deleteById(deleteCategoryId);
        return true;
    }

    @Override
    public Category addDishCategory(int categoryId) {
        return categoryDao.addDishCategory(categoryId);
    }

    @Override
    public List<Category> getkeywords() {
        List<Category> keyList = categoryDao.findkeywords();
        return keyList;
    }
}
