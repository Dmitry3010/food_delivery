package demo.dao;

import demo.model.Category;

import java.util.List;


public interface CategoryDao {

    List<Category> findAll();

    Category findById(int id);//в сервисе сделать: если данные не найдем отдаем ошибку not found Exception 404 ошибка

    Category update(Category category);// незабываем про валидацию

    Category create(Category category);//обязательно добавить блюда и статус код (201), незабываем про валидацию

    void deleteById(int id);//вернуть статус 203 если удалит, если не найдет not found Exception 404 ошибка

}

/**
 * Задача
 * сервис по доставки еды
 * 1.блюда которые находятся в разных категорях (пицца, ролы, паста итд)
 * 2. делаем для владельца сайта
 * 3. пользователь может заходить на сайт и создавать блюда
 * 1.1 метод должен создавать блюдо и отмечать определенную категорию блюда
 * 1.2 обновлять блюда (смена категории)
 * 1.3 удалять блюда
 * я могу получать списки категорий
 * получить конкретную категорию
 * при выборе категории получаю: отоброзить инфо о категории, список блюд
 * 2.2 создавать категории
 * 2.3 обновлять категории
 * 2.4 удалять категории
 *
 * когда создаем блюдо и назначаем категорию должна быть валидация на логичность категории
 * при назначении неверной категории выводим ошибку (ексепшен)
 * аналогично при создании категории для блюда
 *
 * блюдо (название, описание, стоимость)
 * категория (название, блюдо + инфа)
 *
 * когда хочу получить категорию получаю только по ID, и когда выводится категория -->
 * выводим информацию о ней (ID, название и информацию о блюдах) в информации о блюдах получаю только -->
 * (ID, название)
 *
 * в начале лучше получать список категорий (ID, название)
 * дальше получаем конкретную категорию (содержит список блюд)
 * получить блюдо по ID (ID, название, описание, стоимость)
 * обновить категорию (изменить название, список блюд (например убрать лишнее блюдо либо добавить или имя категории))
 * (если я передам не существующее блюдо должен сработать ексепшен)
 * (уже имеющее блюдо добавить нельзя - ексепшен)
 * (пиццу добавить в ролы нельзя тоже ексепшен)
 *
 * DTO посмотреть что это!!!
 * optional (класс)
 *
 *
 *
 * 1. создать блюдо присвоив категорию (должна быть валидация на логичность категории, при назначении неверной категории выводим ошибку (ексепшен))
 * 9. получить блюдо (получаем по ID выводим ID, название, описание, стоимость)
 * 2. обновить блюдо (сменить категорию)
 * 3. удалить блюдо из категории
 *
 * 4. получить список всех категорий  (ID, название)
 * 5. получить конкретную категорию (информация о самой категории и список блюд (ID, название). Получаем только по ID)
 * 6. создать категорию (должна быть валидация на логичность блюд, при наличии неверных блюд выводим ошибку (ексепшен))
 * 7. обновить категорию (изменить название, список блюд (например убрать лишнее блюдо либо добавить или имя категории))
 * 8. удалить категорию
 *
 *  * (если я передам не существующее блюдо должен сработать ексепшен)
 *  * (уже имеющее блюдо добавить нельзя - ексепшен)
 *  * (пиццу добавить в ролы нельзя тоже ексепшен)
 *
 *
 *
 * есть идея создать метод на получение всех блюд, и спользовать его для заполнения списков блюд в категориях!!!!
 */