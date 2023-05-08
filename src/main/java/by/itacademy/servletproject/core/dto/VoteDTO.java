package by.itacademy.servletproject.core.dto;

import java.time.LocalDateTime;

public class VoteDTO {

    private final Integer artist;

    private final Integer[] genres;

    private final String about;
    private final LocalDateTime timeOfVote;


    public VoteDTO(Integer artist,
                   Integer[] genres,
                   String about) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
        this.timeOfVote = LocalDateTime.now();


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

    public LocalDateTime getTimeOfVote() {
        return timeOfVote;
    }
}
