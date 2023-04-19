package by.itacademy.servletproject.core.dto;

import java.time.LocalDateTime;

public class VoteCreateDTO {

    private final Integer artist;

    private final Integer[] genres;

    private final String about;

    private final LocalDateTime localDateTime;


    public VoteCreateDTO(Integer artist, Integer[] genres, String about) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
        this.localDateTime = LocalDateTime.now();
    }


    public Integer getArtist() {
        return artist;
    }

    public Integer[] getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
