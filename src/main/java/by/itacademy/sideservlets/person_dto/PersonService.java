package by.itacademy.sideservlets.person_dto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private final PersonMemoryDao personMemoryDao = PersonMemoryDao.getInstance();

    private static class Holder {
        private static PersonService instance = new PersonService();

    }

    public static PersonService getInstance() {
        return Holder.instance;
    }

    private PersonService() {

    }

    public PersonDTO getPerson(Cookie[] cookies, HttpSession session, String attributeName, String storage) {

        if (storage.toUpperCase().equals(PersonMemoryDao.Storage.COOKIE.name())) {

            int id = validateOnGetPerson(cookies, attributeName);
            PersonDTO personDTO = personMemoryDao.getPerson(PersonMemoryDao.Storage.COOKIE, id);
            if (personDTO == null) {
                throw new IllegalArgumentException("Нет такого человека!");
            }
            return personDTO;


        } else if (storage.toUpperCase().equals(PersonMemoryDao.Storage.SESSION.name())) {
            int id = validateOnGetPerson(session,attributeName);
            PersonDTO personDTO = personMemoryDao.getPerson(PersonMemoryDao.Storage.SESSION, id);
            if (personDTO == null) {
                throw new IllegalArgumentException("Нет такого человека!");
            }
            return personDTO;

        } else {
            throw new IllegalArgumentException("Неверно задано хранилище");

        }


    }

    private int validateOnGetPerson(HttpSession session, String attributeName) {
        if (null == session) {
            throw new IllegalArgumentException("Ты пришел без сессии!");
        }



        int id;

        try {

             id = (int) session.getAttribute(attributeName);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("в твоей сессии не число!");
        }

        return id;

    }

    private int validateOnGetPerson(Cookie[] cookies, String attributeName) {
        if (null == cookies) {
            throw new IllegalArgumentException("Ты пришел без кук!");
        }

        Cookie cookie = Arrays.stream(cookies)
                .filter(x -> attributeName.equals(x.getName()))
                        .findAny().orElseThrow(
                        ()->new IllegalArgumentException("У тебя неправильная кука!")
                );

        int id;

        try {
            id = Integer.parseInt(cookie.getValue());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("В куке не число");
        }
        return id;

    }


    public PersonDTO putPerson(PersonCreateDTO personCreateDTO) {
        try {
            PersonDTO personDTO = validateOnPutPerson(personCreateDTO);
            PersonMemoryDao.Storage storage = PersonMemoryDao.Storage.valueOf(personCreateDTO.getStorage().toUpperCase());
            return personMemoryDao.putPerson(storage, personDTO);
        } catch (IllegalArgumentException e) {
            throw e;
        }


    }

    private PersonDTO validateOnPutPerson(PersonCreateDTO personCreateDTO) {
        if (personCreateDTO.getFirstName().isBlank()
                ||
                personCreateDTO.getLastName().isBlank()) {
            throw new IllegalArgumentException(
                    "Передано пустое имя/фамилия"
            );
        }

        String firstName = personCreateDTO.getFirstName();
        String lastName = personCreateDTO.getLastName();

        int age;

        try {
            age = Integer.parseInt(personCreateDTO.getAge());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Неверное указание возраста"
            );
        }


        try {
            PersonMemoryDao.Storage.valueOf(personCreateDTO.getStorage().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверно указано хранилище");
        }

        return new PersonDTO(firstName, lastName, age);

    }


    public List<String> getStoragesNames() {
        return Arrays.stream(PersonMemoryDao.Storage.values()).map(
                PersonMemoryDao.Storage::name
        ).collect(Collectors.toList());
    }


}
