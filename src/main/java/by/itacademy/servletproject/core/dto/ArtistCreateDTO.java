package by.itacademy.servletproject.core.dto;

public class ArtistCreateDTO {


    private String name;

    public ArtistCreateDTO() {
    }

    public ArtistCreateDTO(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
