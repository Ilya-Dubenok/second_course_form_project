package by.itacademy.servletproject.business;

import by.itacademy.servletproject.dto.GenreDTO;
import by.itacademy.servletproject.dto.IDTO;
import by.itacademy.servletproject.dto.PlayerDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteProcessor {

    private final List<IDTO> players = new ArrayList<>();

    private final List<IDTO> genres = new ArrayList<>();

    private final HashMap<IDTO, Integer> votesForPlayers = new HashMap<>();

    private final HashMap<IDTO, Integer> votesForGenres = new HashMap<>();

    private final Map<LocalDateTime, String> descriptions = new HashMap<>();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public VoteProcessor() {
        formPlayers("Mozart", "Metallica", "Tarja", "AC_DC");
        formGenres("Rock", "HardRock", "Metal", "POP", "RNB", "Blues", "Rap", "Classic", "Country", "Jazz");

    }




    private void formPlayers(String... names) {

        for (int i = 0; i < names.length; i++) {
            votesForPlayers.put(new PlayerDTO(i, names[i]), 0);
        }

    }


    private void formGenres(String... genres) {
        for (int i = 0; i < genres.length; i++) {
            votesForGenres.put(new GenreDTO(i, genres[i]), 0);
        }
    }



}
