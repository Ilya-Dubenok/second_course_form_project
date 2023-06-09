package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;
import by.itacademy.servletproject.daO.memory.factory.GenreDaoFactory;
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

                    instance = new GenreService(GenreDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
