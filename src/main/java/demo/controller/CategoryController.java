package demo.controller;

import demo.dao.DishDao;
import demo.dto.CategoryDto;
import demo.dto.CategoryIdDto;
import demo.model.Category;
import demo.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Category> getById(@PathVariable("id") int id){
        Category category = categoryService.getById(id);
        if (category != null){
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        }else {
            return new ResponseEntity<Category>(category, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody CategoryDto request){
        Category categoryCreate = new Category(0, request.getName());
        return categoryService.create(categoryCreate);
    }

    @PutMapping("/update")
    public Category update(@RequestBody Category category){
        Category categoryUpdete = categoryService.update(category);
        return categoryUpdete;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") int id){
        boolean isDelete = categoryService.deleteById(id);
        if (isDelete){
            return new ResponseEntity<Boolean>(isDelete, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        } else {
            return new ResponseEntity<Boolean>(isDelete, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/add")
    public Category addDishCategory(@RequestParam int categoryId) {
        Category category = categoryService.addDishCategory(categoryId);
        return category;
    }

    @GetMapping("/key")
    public List<Category> getkeywords() {
        List<Category> keyList = categoryService.getkeywords();
        return keyList;
    }
}
