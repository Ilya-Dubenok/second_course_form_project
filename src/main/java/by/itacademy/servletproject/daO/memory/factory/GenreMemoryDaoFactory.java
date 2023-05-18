package by.itacademy.servletproject.daO.memory.factory;

import by.itacademy.servletproject.daO.api.IGenreDao;
import by.itacademy.servletproject.daO.memory.GenreMemoryDao;

public class GenreMemoryDaoFactory {

    private static volatile IGenreDao instance;

    private GenreMemoryDaoFactory() {

    }


    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreMemoryDaoFactory.class) {
                if (instance == null) {

                    instance = new GenreMemoryDao();
                }
            }
        }
        return instance;
    }
}
