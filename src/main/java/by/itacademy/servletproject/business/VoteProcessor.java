package by.itacademy.servletproject.business;

import by.itacademy.servletproject.dto.GenreDTO;
import by.itacademy.servletproject.dto.IDTO;
import by.itacademy.servletproject.dto.PlayerDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VoteProcessor {

    private final List<IDTO> players = new ArrayList<>();

    private final List<IDTO> genres = new ArrayList<>();

    private final Map<IDTO, Integer> votesForPlayers = new HashMap<>();

    private final Map<IDTO, Integer> votesForGenres = new HashMap<>();

    private final Map<LocalDateTime, String> descriptions = new TreeMap<>(
            Comparator.reverseOrder()
    );


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public VoteProcessor() {
        formPlayers(List.of("Mozart", "Metallica", "Tarja", "AC_DC"));
        formGenres(List.of("Rock", "Pop", "Metal", "Classic",
                "Grange", "RNB", "Rap", "Jazz", "Bluez", "Folk"));

    }


    public List<IDTO> getPlayers() {
        return players;
    }

    public List<IDTO> getGenres() {
        return genres;
    }

    public List<Integer> getId(Class<? extends IDTO> clazz) {

        List<Integer> res = new ArrayList<>();
        if (clazz.equals(PlayerDTO.class)) {
            votesForPlayers.forEach((K, V) -> res.add(K.getId()));
        } else {
            votesForGenres.forEach((K, V) -> res.add(K.getId()));
        }

        return res;

    }


    public void incrementVotesForPlayers(String[] idOfPlayers) {
        for (String idOfPlayer : idOfPlayers) {
            Integer id = Integer.parseInt(idOfPlayer);
            IDTO player = findById(id, this.players);
            votesForPlayers.put(player, votesForPlayers.get(player) + 1);

        }

    }

    public void incrementVotesForGenres(String[] idOfGenres) {
        for (String idOfGenre : idOfGenres) {
            Integer id = Integer.parseInt(idOfGenre);
            IDTO genre = findById(id, this.genres);
            votesForGenres.put(genre, votesForGenres.get(genre) + 1);
        }
    }


    public Map<IDTO, Integer> getVotesForPlayersSorted() {
        Map<IDTO, Integer> res = new LinkedHashMap<>();
        votesForPlayers.entrySet()
                .stream()
                .sorted(Map.Entry.<IDTO, Integer>comparingByValue().reversed())
                .forEach(x -> res.put(x.getKey(), x.getValue()));
        return res;
    }

    public Map<IDTO, Integer> getVotesForPlayersSorted(Comparator<? super Map.Entry<IDTO, Integer>> comp) {
        Map<IDTO, Integer> res = new LinkedHashMap<>();
        votesForPlayers.entrySet()
                .stream()
                .sorted(comp)
                .forEach(x -> res.put(x.getKey(), x.getValue()));
        return res;
    }

    public Map<IDTO, Integer> getVotesForGenresSorted() {
        Map<IDTO, Integer> res = new LinkedHashMap<>();
        votesForGenres.entrySet().stream()
                .sorted(Map.Entry.<IDTO, Integer>comparingByValue().reversed())
                .forEach(x -> {
                    res.put(x.getKey(), x.getValue());
                });
        return res;
    }


    public Map<IDTO, Integer> getVotesForGenresSorted(Comparator<? super Map.Entry<IDTO, Integer>> comp) {
        Map<IDTO, Integer> res = new LinkedHashMap<>();
        votesForGenres.entrySet().stream()
                .sorted(comp)
                .forEach(x -> {
                    res.put(x.getKey(), x.getValue());
                });
        return res;
    }

    public Map<String, String> getDescriptionsSorted() {
        Map<String, String> res = new LinkedHashMap<>();
        descriptions.entrySet().stream()
                .forEach(
                        x -> res.put(x.getKey().format(formatter), x.getValue()));

        return res;

    }


    public void putDescription(String description) {
        descriptions.put(LocalDateTime.now(), description);
    }

    private IDTO findById(Integer id, List<IDTO> list) {
        IDTO res = null;
        for (IDTO idto : list) {
            if (idto.getId() == id) {
                res = idto;
                break;
            }

        }
        return res;
    }


    private void formPlayers(List<String> namesList) {

        for (int i = 0; i < namesList.size(); i++) {
            PlayerDTO playerDTO = new PlayerDTO(i, namesList.get(i));
            players.add(playerDTO);
            votesForPlayers.put(playerDTO, 0);
        }

    }


    private void formGenres(List<String> genresList) {
        for (int i = 0; i < genresList.size(); i++) {
            GenreDTO genreDTO = new GenreDTO(i, genresList.get(i));
            genres.add(genreDTO);
            votesForGenres.put(genreDTO, 0);
        }
    }


}
