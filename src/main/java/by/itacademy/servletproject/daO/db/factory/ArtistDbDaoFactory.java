package by.itacademy.servletproject.daO.db.factory;

import by.itacademy.servletproject.daO.api.IArtistDao;
import by.itacademy.servletproject.daO.db.ArtistDbDao;
import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;

public class ArtistDbDaoFactory {

    private static volatile IArtistDao instance;

    private ArtistDbDaoFactory() {
    }

    public static IArtistDao getInstance() {
        if (instance == null) {
            synchronized (ArtistDbDao.class) {
                if (instance == null) {

                    instance = new ArtistDbDao();
                }
            }
        }
        return instance;
    }
}
