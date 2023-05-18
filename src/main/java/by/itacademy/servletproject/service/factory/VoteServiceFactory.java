package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.daO.memory.factory.VoteMemoryDaoFactory;
import by.itacademy.servletproject.service.VoteService;
import by.itacademy.servletproject.service.api.IVoteService;

public class VoteServiceFactory {

    private static volatile IVoteService instance;

    private VoteServiceFactory() {

    }

    public static IVoteService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceFactory.class) {
                if (instance == null) {

                    instance = new VoteService(
                            ArtistServiceFactory.getInstance(),
                            GenreServiceFactory.getInstance(),
                            VoteMemoryDaoFactory.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
