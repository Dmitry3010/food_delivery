package demo.dto;

public class CategoryDishFilter {

    private String categoryName;
    private String dishName;

    public CategoryDishFilter(String categoryName, String dishName) {
        this.categoryName = categoryName;
        this.dishName = dishName;
    }

    public CategoryDishFilter() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
