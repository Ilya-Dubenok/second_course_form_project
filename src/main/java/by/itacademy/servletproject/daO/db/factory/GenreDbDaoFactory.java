package by.itacademy.servletproject.daO.db.factory;

import by.itacademy.servletproject.daO.api.IArtistDao;
import by.itacademy.servletproject.daO.api.IGenreDao;
import by.itacademy.servletproject.daO.db.ArtistDbDao;
import by.itacademy.servletproject.daO.db.GenreDbDao;

public class GenreDbDaoFactory {

    private static volatile IGenreDao instance;

    private GenreDbDaoFactory() {
    }

    public static IGenreDao getInstance() {
        if (instance == null) {
            synchronized (GenreDbDao.class) {
                if (instance == null) {

                    instance = new GenreDbDao();
                }
            }
        }
        return instance;
    }


}
