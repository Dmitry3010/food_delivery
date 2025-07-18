package demo.service.impl;

import demo.dao.DishDao;
import demo.model.Category;
import demo.model.Dish;
import demo.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishDao dishDao;

    public DishServiceImpl(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> dishList = dishDao.findAll();
       return dishList;
    }

    @Override
    public Dish create(Dish dish) {
        Dish dishCreate = dishDao.create(dish);
        return dishCreate;
    }

    @Override
    public Dish getById(int id) {
        Dish dish = dishDao.findById(id);
        return dish;
    }

    @Override
    public Dish update(Dish dish) {
        Dish dishUpdate = dishDao.update(dish);
        return dishUpdate;
    }

    @Override
    public void deleteById(int id) {
        dishDao.deleteById(id);
    }
}
