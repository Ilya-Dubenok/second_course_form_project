package by.itacademy.sideservlets.person_dto;

public class PersonDTO {

    private static int totalId = 1;
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int age;


    public PersonDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        synchronized (PersonDTO.class) {
            this.id = totalId++;

        }
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
