package demo.controller;

import demo.model.Category;
import demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAll(){
        List<Category> categoryList = categoryService.getAll();
        return categoryList;
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable("id") int id){
        Category category = categoryService.getById(id);
        return category;
    }

    @PutMapping("/update")
    public Category update(@RequestBody Category category){
        Category categoryUpdete = categoryService.update(category);
        return categoryUpdete;
    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category){
        Category categoryCreate = categoryService.create(category);
        return categoryCreate;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id){
        categoryService.deleteById(id);
    }
}
