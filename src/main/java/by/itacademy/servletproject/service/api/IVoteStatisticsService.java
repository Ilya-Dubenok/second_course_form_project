package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.core.dto.StatisticDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IVoteStatisticsService {


    StatisticDTO getTop();

    Map<GenreDTO, Integer> getGenreTop();

    Map<ArtistDTO, Integer> getArtistTop();

    Map<LocalDateTime, String> getAboutTop();





}
