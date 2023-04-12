package by.itacademy.servletproject.dto;

public class PlayerDTO implements IDTO{


    private int id;
    private String name;

    public PlayerDTO() {
    }

    public PlayerDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
