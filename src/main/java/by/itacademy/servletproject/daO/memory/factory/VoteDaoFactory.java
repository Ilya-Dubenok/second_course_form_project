package by.itacademy.servletproject.daO.memory.factory;

import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;

public class VoteDaoFactory {

    private static volatile IVoteDao instance;

    private VoteDaoFactory(){};


    public static IVoteDao getInstance() {
        if (instance == null) {
            synchronized (ArtistMemoryDao.class) {
                if (instance == null) {

                    instance = new VoteMemoryDao();
                }
            }
        }
        return instance;
    }
}
