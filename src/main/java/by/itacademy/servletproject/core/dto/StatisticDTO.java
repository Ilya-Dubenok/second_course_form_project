package by.itacademy.servletproject.core.dto;

import java.util.Map;

public class StatisticDTO {


    private Map<String, Integer> genreTop;

    private Map<String, Integer> artistTop;


    public Map<String, Integer> getGenreTop() {
        return genreTop;
    }

    public Map<String, Integer> getArtistTop() {
        return artistTop;
    }
}
