package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.core.dto.VoteCreateDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IVoteService {

    void save(VoteCreateDTO createDTO);

    Map<ArtistDTO,Integer> getArtistsVotesMap();

    Map<GenreDTO,Integer> getGenresVotesMap();


    Map<LocalDateTime, String> getAboutWithTimeMap();
}
