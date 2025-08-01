package demo.controller;


import demo.dto.CategoryDto;
import demo.dto.DishDto;
import demo.model.Category;
import demo.model.Dish;
import demo.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/all")//работает
    public List<Dish> getAll() {
        List<Dish> dishList = dishService.getAll();
        return dishList;
    }

    @GetMapping("/{id}")//работает
    public ResponseEntity<Dish> getById(@PathVariable("id") int id) {
        Dish dish = dishService.getById(id);
        if (dish != null){
            return new ResponseEntity<Dish>(dish, HttpStatus.OK);
        } else {
            return new ResponseEntity<Dish>(dish, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        Dish dishCreate = dishService.create(dish);
        return new ResponseEntity<Dish>(dishCreate, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public Dish update(@RequestBody Dish dish) {
        Dish dishUpdate = dishService.update(dish);
        return dishUpdate;
    }

    @DeleteMapping("/delete/{id}")//не работает
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") int id) {
        boolean isDelete = dishService.deleteById(id);
        if (isDelete){
            return new ResponseEntity<Boolean>(isDelete, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }else {
            return new ResponseEntity<Boolean>(isDelete, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/categoryId/{id}")
    public void deleteByCategoryId(@PathVariable("id") int id) {
        dishService.deleteByCategoryId(id);
    }

    @GetMapping("/categoryId/{categoryId}")
    public List<Dish> getByCategoryId(@PathVariable("categoryId") int categoryId) {
        List<Dish> dishList = dishService.getByCategoryId(categoryId);
        return dishList;
    }
}
