package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.daO.memory.factory.VoteDaoFactory;
import by.itacademy.servletproject.service.VoteService;

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
                            VoteDaoFactory.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
