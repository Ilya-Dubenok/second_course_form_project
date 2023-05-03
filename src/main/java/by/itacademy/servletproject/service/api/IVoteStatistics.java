package by.itacademy.servletproject.service.api;

import by.itacademy.servletproject.core.dto.StatisticDTO;

import java.util.Map;

public interface IVoteStatistics {


    StatisticDTO getTop();

    Map<String, Integer> getGenreTop();

    Map<String, Integer> getArtistTop();





}
