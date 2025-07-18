package demo.controller;


import demo.model.Category;
import demo.model.Dish;
import demo.service.DishService;
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

    @PostMapping("/create")
    public Dish create(@RequestBody Dish dish) {
        Dish dishCreate = dishService.create(dish);
        return dishCreate;
    }

    @GetMapping("/{id}")//работает
    public Dish getById(@PathVariable("id") int id) {
        Dish dish = dishService.getById(id);
        return dish;
    }

    @PutMapping("/update")
    public Dish update(@RequestBody Dish dish) {
        Dish dishUpdate = dishService.update(dish);
        return dishUpdate;
    }

    @DeleteMapping("/delete/{id}")//не работает
    public void deleteById(@PathVariable("id") int id) {
        dishService.deleteById(id);
    }
}
