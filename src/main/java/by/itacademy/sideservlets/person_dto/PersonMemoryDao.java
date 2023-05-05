package by.itacademy.sideservlets.person_dto;

import java.util.HashMap;
import java.util.Map;

public class PersonMemoryDao {



    private static class Holder{
        private static PersonMemoryDao instance = new PersonMemoryDao();
    }

    private PersonMemoryDao() {

    }

    public static PersonMemoryDao getInstance() {
        return Holder.instance;
    }


    public PersonDTO getPerson(Storage storage, int id) {
        return storage.getPerson(id);

    }

    public PersonDTO putPerson(Storage storage, PersonDTO personDTO) {
        return storage.putPerson(personDTO.getId(), personDTO);
    }




    enum Storage {
        COOKIE(new HashMap<>()), SESSION(new HashMap<>());

        private Map<Integer, PersonDTO> storage;

        Storage(Map<Integer, PersonDTO> storage) {
            this.storage = storage;
        }

        public PersonDTO getPerson(int id) {
            return storage.get(id);
        }


        public PersonDTO putPerson(Integer key, PersonDTO value) {
            storage.put(key, value);
            return value;

        }


    }

}
