package demo.service;

import demo.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(int id);

    Category update(Category category);

    Category create(Category category);

    void deleteById(int id);

}
