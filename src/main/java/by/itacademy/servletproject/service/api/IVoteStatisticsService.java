package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.StatisticDTO;

import java.time.LocalDateTime;
import java.util.Map;

public interface IVoteStatisticsService {


    StatisticDTO getTop();

    Map<String, Integer> getGenreTop();

    Map<String, Integer> getArtistTop();

    Map<LocalDateTime, String> getAboutTop();





}
