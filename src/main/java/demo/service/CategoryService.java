package demo.service;

import demo.dto.CategoryDishFilter;
import demo.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(int id);

    Category update(Category category);

    Category create(Category category);

    boolean deleteById(int id);

    Category addDishCategory(int categoryId);

    List<Category> getkeywords();

    CategoryDishFilter getkeywordsById(int categoryId);
}
