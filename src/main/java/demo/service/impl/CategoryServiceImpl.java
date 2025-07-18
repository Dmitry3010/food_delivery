package demo.service.impl;

import demo.dao.CategoryDao;
import demo.model.Category;
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
    public Category getById(int id) {
        Category category = categoryDao.findById(id);
        return category;
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdete = categoryDao.update(category);
        return categoryUpdete;
    }

    @Override
    public Category create(Category category) {
        Category categoryCreate = categoryDao.create(category);
        return categoryCreate;
    }

    @Override
    public void deleteById(int id) {
        categoryDao.deleteById(id);
    }
}
