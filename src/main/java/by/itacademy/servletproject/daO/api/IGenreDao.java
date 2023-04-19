package by.itacademy.servletproject.daO.api;

import by.itacademy.servletproject.core.dto.GenreDTO;

import java.util.Map;

public interface IGenreDao extends ICRUDDao<GenreDTO> {

    //TODO TO DELETE
    Map<Integer, Integer> getMapOfVotes();

    void putNewVoices(Integer[] artistIDS);

}
