package by.itacademy.servletproject.core;

public class Main {




    public static void main(String[] args) {

//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//
//        populator.addScript(new ClassPathResource("data.sql"));
//        populator.execute(DataSourceConnector.getInstance().getDataSource());
        Person person = new Person(1, "aaa");


        System.out.println(person.hashCode());
        changePerson(person);
        System.out.println(person.hashCode());


        Person person1 = new Person(1, "aaa");

        System.out.println(person1.hashCode());

        Person person2 = changePerson(person1);
        System.out.println(person2.hashCode());


    }


    private static class Person{

        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }


    private static Person changePerson(Person person) {

        person.age = 3;
        person.name = "33";
        return person;
    }


}
