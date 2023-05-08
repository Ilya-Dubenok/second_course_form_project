package by.itacademy.servletproject.core.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class StatisticDTO {


    private Map<String, Integer> genreTop;

    private Map<String, Integer> artistTop;

    private Map<LocalDateTime, String> aboutTop;

    public StatisticDTO(Map<String, Integer> genreTop, Map<String, Integer> artistTop, Map<LocalDateTime, String> aboutTop) {
        this.genreTop = genreTop;
        this.artistTop = artistTop;
        this.aboutTop = aboutTop;
    }

    public Map<String, Integer> getGenreTop() {
        return genreTop;
    }

    public Map<String, Integer> getArtistTop() {
        return artistTop;
    }

    public Map<LocalDateTime, String> getAboutTop() {
        return aboutTop;
    }
}
