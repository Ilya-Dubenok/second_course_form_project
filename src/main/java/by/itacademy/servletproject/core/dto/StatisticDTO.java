package by.itacademy.servletproject.core.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class StatisticDTO {


    private Map<GenreDTO, Integer> genreTop;

    private Map<ArtistDTO, Integer> artistTop;

    private Map<LocalDateTime, String> aboutTop;

    public StatisticDTO(Map<GenreDTO, Integer> genreTop, Map<ArtistDTO, Integer> artistTop, Map<LocalDateTime, String> aboutTop) {
        this.genreTop = genreTop;
        this.artistTop = artistTop;
        this.aboutTop = aboutTop;
    }

    public Map<GenreDTO, Integer> getGenreTop() {
        return genreTop;
    }

    public Map<ArtistDTO, Integer> getArtistTop() {
        return artistTop;
    }

    public Map<LocalDateTime, String> getAboutTop() {
        return aboutTop;
    }
}
