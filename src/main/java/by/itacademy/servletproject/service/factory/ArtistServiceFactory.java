package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.daO.db.ArtistDbDao;
import by.itacademy.servletproject.daO.db.factory.ArtistDbDaoFactory;
import by.itacademy.servletproject.daO.memory.factory.ArtistMemoryDaoFactory;
import by.itacademy.servletproject.service.ArtistService;
import by.itacademy.servletproject.service.api.IArtistService;

public class ArtistServiceFactory {


    private static volatile IArtistService instance;

    private ArtistServiceFactory() {

    }

    public static IArtistService getInstance() {
        if (instance == null) {
            synchronized (ArtistServiceFactory.class) {
                if (instance == null) {

                    instance = new ArtistService(ArtistDbDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
