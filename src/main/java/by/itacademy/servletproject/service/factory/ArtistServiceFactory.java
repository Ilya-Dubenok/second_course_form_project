package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;
import by.itacademy.servletproject.daO.memory.factory.ArtistDaoFactory;
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

                    instance = new ArtistService(ArtistDaoFactory.getInstance());
                }
            }
        }
        return instance;
    }
}
