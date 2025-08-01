package demo.dto;

public class CategoryIdDto {

    int categoryId;

    public CategoryIdDto(int categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryIdDto() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
