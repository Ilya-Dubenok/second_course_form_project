package by.itacademy.servletproject.service.factory;

import by.itacademy.servletproject.service.VoteStatisticsService;
import by.itacademy.servletproject.service.api.IVoteStatisticsService;

public class VoteStatisticsServiceFactory {

    private static volatile IVoteStatisticsService instance;

    private VoteStatisticsServiceFactory() {

    }

    public static IVoteStatisticsService getInstance() {
        if (instance == null) {
            synchronized (VoteServiceFactory.class) {
                if (instance == null) {

                    instance = new VoteStatisticsService(
                            VoteServiceFactory.getInstance(),
                            GenreServiceFactory.getInstance(),
                            ArtistServiceFactory.getInstance()
                    );
                }
            }
        }
        return instance;
    }


}
