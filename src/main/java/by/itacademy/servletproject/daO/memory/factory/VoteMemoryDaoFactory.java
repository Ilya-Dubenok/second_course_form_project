package by.itacademy.servletproject.daO.memory.factory;

import by.itacademy.servletproject.daO.api.IVoteDao;
import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;
import by.itacademy.servletproject.daO.memory.VoteMemoryDao;

public class VoteMemoryDaoFactory {

    private static volatile IVoteDao instance;

    private VoteMemoryDaoFactory(){};


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
