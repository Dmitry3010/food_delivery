package demo.dto;

public class DishDto {

    int idCategory;

    public DishDto(int idCategory) {
        this.idCategory = idCategory;
    }

    public DishDto() {
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
