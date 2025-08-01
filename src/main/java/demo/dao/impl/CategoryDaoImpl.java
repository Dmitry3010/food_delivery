package demo.dao.impl;

import demo.dao.CategoryDao;
import demo.dao.DishDao;
import demo.dto.CategoryDishFilter;
import demo.model.Category;
import demo.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final DishDao dishDao;

    private List<Category> categoryList = new ArrayList<>();
    private List<Dish> burgerList = new ArrayList<>();
    private List<Dish> saladList = new ArrayList<>();
    private List<Category> keywordList = new ArrayList<>();

    private int idCatgegory = 1;


    public CategoryDaoImpl(DishDao dishDao) {
        this.dishDao = dishDao;

        Dish beefBurger = new Dish(1, "Beef Burger", "Бургер с говядиной", 750, 1);
        Dish chickenBurger = new Dish(2, "Chicken Burger", "Бургер с курицей", 650, 1);
        burgerList.add(beefBurger);
        burgerList.add(chickenBurger);

        Dish greekSalad = new Dish(3, "Greek Salad", "Греческий салат", 300, 2);
        Dish caesarSalad = new Dish(4, "Caesar Salad", "Салат Цезарь", 400, 2);
        saladList.add(greekSalad);
        saladList.add(caesarSalad);

        create(new Category(0, "Burger", burgerList));
        create(new Category(0, "Salad", saladList));


        Category validateBurger = new Category(1,"Burger");
        Category validateSalad = new Category(2, "Salad");
        //Category validateSale = new Category(3, "Burger");
        keywordList.add(validateBurger);
        keywordList.add(validateSalad);
        //keywordList.add(validateSale);
    }

    @Override
    public List<Category> findkeywords() {
        return keywordList;
    }

    @Override
    public CategoryDishFilter findkeywordsById(int categoryId) {
            for (Category category : categoryList){
                int categoryValidateId = category.getId();
                if (categoryValidateId == categoryId){
                    CategoryDishFilter categoryDishFilter = new CategoryDishFilter();
                    categoryDishFilter.setCategoryName(category.getName());
                    categoryDishFilter.setDishName(category.getName());
                    return categoryDishFilter;
                }
            }
        throw new IllegalStateException("Список пуст!");
    }

    @Override
    public List<Category> findAll() {
        if(categoryList != null && categoryList.isEmpty()) {
            throw new IllegalStateException("Список категорий пуст!");
        }
        return categoryList;
    }

    @Override
    public Category findById(int id) {
        if(categoryList != null && categoryList.isEmpty()) {
            throw new IllegalStateException("Список категорий пуст!");
        }
        for (Category category : categoryList){
            if (category.getId() == id){
                return category;
            }
        }
        throw new IllegalArgumentException("Категория c ID " + id + " не нашлась в списке!");
    }

    @Override
    public Category create(Category category) {
        category.setId(idCatgegory++);
        categoryList.add(category);
//        List<Dish> createCategoryDishList = category.getDishList();
//        for (Dish createDish : createCategoryDishList){
//            dishDao.create(createDish);
//        }
        return category;
    }


    @Override
    public Category update(Category category) { //изменение списка блюд в категории
        int categoryId = category.getId();
        List<Dish> currentCategoryDishList = new ArrayList<>();
        for (Category currentCategory : categoryList){
            int currentCategoryId = currentCategory.getId();
            if (categoryId == currentCategoryId){
                currentCategory.setName(category.getName());
                List<Dish> dishList = category.getDishList();
                currentCategoryDishList.addAll(dishList);
                currentCategory.setDishList(currentCategoryDishList);
            }
        }
        return category;
    }

    @Override
    public boolean deleteById(int id) {
        Category categoryToDelete = null;
        for (Category category : categoryList){
            int categoryId = category.getId();
            if (id == categoryId){
                categoryToDelete = category;
                dishDao.deleteByCategoryId(categoryId);
            }
        }
        categoryList.remove(categoryToDelete);
        return true;
    }

    @Override
    public Category addDishCategory(int categoryId) {
        for (Category currentCategory : categoryList){
            int idCategory = currentCategory.getId();
            if (categoryId == idCategory){
                List<Dish> categoryDishList = currentCategory.getDishList();
                List<Dish> categoryIdDishList = dishDao.findByCategoryId(categoryId);
                categoryDishList.addAll(categoryIdDishList);
                return currentCategory;
            }
        }
        throw new IllegalArgumentException("Категория c ID " + categoryId + " не нашлась в списке!");
    }
}
