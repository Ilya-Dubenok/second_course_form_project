package by.itacademy.servletproject;

import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {

        System.out.println(Storage.ONE.name());
        System.out.println(Storage.valueOf("one".toUpperCase()));

    }


    enum Storage{
        ONE,TWO
    }

}
