package by.itacademy.sideservlets.person_dto;

public class PersonCreateDTO {
    private final String firstName;
    private final String lastName;
    private final String age;
    private final String storage;

    public PersonCreateDTO(String firstName, String lastName, String age, String storage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.storage = storage;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getStorage() {
        return storage;
    }
}
