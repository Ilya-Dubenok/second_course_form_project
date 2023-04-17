package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.GenreCreateDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;

import java.util.List;
import java.util.Map;

public interface IGenreService extends ICRUDService<GenreDTO, GenreCreateDTO> {

    String validate (Integer[] genreIDS);

    void putVotes(Integer[] genreIDS) throws IllegalArgumentException;

    Map<GenreDTO, Integer> getSortedVotesInfo();

}
