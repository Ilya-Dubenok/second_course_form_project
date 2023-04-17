package by.itacademy.servletproject.daO.api;

import by.itacademy.servletproject.core.dto.ArtistDTO;

import java.util.Map;

public interface IArtistDao extends ICRUDDao<ArtistDTO> {

    //TODO TO DELETE
    Map<Integer, Integer> getMapOfVotes();

    void putNewVoices(Integer[] artistIDS);

}
