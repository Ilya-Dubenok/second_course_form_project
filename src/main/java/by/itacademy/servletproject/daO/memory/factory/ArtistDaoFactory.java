package by.itacademy.servletproject.daO.memory.factory;

import by.itacademy.servletproject.daO.api.IArtistDao;
import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;

public class ArtistDaoFactory {

    private static volatile IArtistDao instance;

    private ArtistDaoFactory(){};


    public static IArtistDao getInstance() {
        if (instance == null) {
            synchronized (ArtistMemoryDao.class) {
                if (instance == null) {

                    instance = new ArtistMemoryDao();
                }
            }
        }
        return instance;
    }
}
