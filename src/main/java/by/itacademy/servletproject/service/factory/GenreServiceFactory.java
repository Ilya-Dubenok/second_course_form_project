package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.daO.db.factory.GenreDbDaoFactory;
import by.itacademy.servletproject.daO.memory.factory.GenreMemoryDaoFactory;
import by.itacademy.servletproject.service.GenreService;
import by.itacademy.servletproject.service.api.IGenreService;

public class GenreServiceFactory {

    private static volatile IGenreService instance;

    private GenreServiceFactory() {

    }

    public static IGenreService getInstance() {
        if (instance == null) {
            synchronized (GenreServiceFactory.class) {
                if (instance == null) {

                    instance = new GenreService(GenreDbDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
