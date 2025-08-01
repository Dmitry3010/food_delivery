package demo.dto;

public class CategoryDto {

    String name;

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
