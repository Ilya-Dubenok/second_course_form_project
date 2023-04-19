package by.itacademy.servletproject.daO.memory.factory;

import by.itacademy.servletproject.daO.api.IGenreDao;
import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;
import by.itacademy.servletproject.daO.memory.GenreMemoryDao;

public class GenreDaoFactory {

    private static volatile IGenreDao instance;

    private GenreDaoFactory() {

    }


    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDaoFactory.class) {
                if (instance == null) {

                    instance = new GenreMemoryDao();
                }
            }
        }
        return instance;
    }
}
