package by.itacademy.servletproject.daO.memory.factory;

import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.daO.api.IArtistDao;
import by.itacademy.servletproject.daO.api.IVoteDao;
import by.itacademy.servletproject.daO.memory.ArtistMemoryDao;
import by.itacademy.servletproject.daO.memory.VoteMemoryDao;

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
