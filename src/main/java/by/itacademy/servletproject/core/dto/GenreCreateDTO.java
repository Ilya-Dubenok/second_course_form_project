package by.itacademy.servletproject.core.dto;

public class GenreCreateDTO{

    private String name;

    public GenreCreateDTO() {
    }

    public GenreCreateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
