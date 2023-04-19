package by.itacademy.servletproject.service;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.daO.api.IVoteDao;
import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.api.IVoteService;

import java.time.LocalDateTime;
import java.util.*;

public class VoteService implements IVoteService {

    private IArtistService artistService;

    private IGenreService genreService;

    private final IVoteDao voteDao;


    public VoteService(IArtistService artistService, IGenreService genreService, IVoteDao voteDao) {
        this.artistService = artistService;
        this.genreService = genreService;
        this.voteDao = voteDao;
    }


    public Map<ArtistDTO,Integer> getArtistsVotesMap() {
        List<ArtistDTO> artistDTOS = artistService.get();
        Map<Integer, Integer> mapOfIDsAndVotes = new HashMap<>();
        Map<Integer, ArtistDTO> mapOfIDsAndArtists = new HashMap<>();
        for (ArtistDTO artistDTO : artistDTOS) {
            mapOfIDsAndVotes.put(artistDTO.getId(), 0);
            mapOfIDsAndArtists.put(artistDTO.getId(), artistDTO);
        }

        List<VoteCreateDTO> voteCreateDTOS = voteDao.get();
        for (VoteCreateDTO vote: voteCreateDTOS) {
            mapOfIDsAndVotes.computeIfPresent(vote.getArtist(),
                    (k, v) -> v + 1
            );
        }
        Map<ArtistDTO, Integer> res = new LinkedHashMap<>();

        mapOfIDsAndVotes.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .forEach(
                        x -> {
                            res.put(mapOfIDsAndArtists.get(x.getKey()),x.getValue());

                        }
                );

        return res;

    }


    public Map<GenreDTO,Integer> getGenresVotesMap() {
        List<GenreDTO> genreDTOS = genreService.get();
        Map<Integer, Integer> mapOfIDsAndVotes = new HashMap<>();
        Map<Integer, GenreDTO> mapOfIDsAndGenres = new HashMap<>();
        for (GenreDTO genreDTO : genreDTOS) {
            mapOfIDsAndVotes.put(genreDTO.getId(), 0);
            mapOfIDsAndGenres.put(genreDTO.getId(), genreDTO);
        }

        List<VoteCreateDTO> voteCreateDTOS = voteDao.get();
        for (VoteCreateDTO vote: voteCreateDTOS) {
            for (Integer genre : vote.getGenres()) {
                mapOfIDsAndVotes.computeIfPresent(genre,
                        (k, v) -> v + 1
                );
            }

        }
        Map<GenreDTO, Integer> res = new LinkedHashMap<>();

        mapOfIDsAndVotes.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .forEach(
                        x -> {
                            res.put(mapOfIDsAndGenres.get(x.getKey()),x.getValue());

                        }
                );

        return res;

    }

    @Override
    public Map<LocalDateTime, String> getAboutWithTimeMap() {

        Map<LocalDateTime, String> res = new LinkedHashMap<>();
        List<VoteCreateDTO> voteCreateDTOS = voteDao.get();

        voteCreateDTOS
                .stream()
                .sorted(
                        (o1, o2) -> o2.getLocalDateTime().compareTo(o1.getLocalDateTime())
                )
                .forEach(
                        x -> res.put(x.getLocalDateTime(),x.getAbout())
                );

        return res;



    }

    @Override
    public void save(VoteCreateDTO createDTO) {
        validate(createDTO);
        voteDao.save(createDTO);

    }

    public void validate(VoteCreateDTO createDTO) {
        Integer artistId = createDTO.getArtist();

        if (artistId == null) {
            throw new IllegalArgumentException("Передано неверное количество артистов");
        }

        ArtistDTO artistDTO = artistService.get(artistId);
        if (artistDTO == null) {
            throw new IllegalArgumentException("Введен несуществующий артист");
        }

        Integer[] genres = createDTO.getGenres();
        if (genres.length < 3 || genres.length > 5) {
            throw new IllegalArgumentException("Жанров должно быть от 3 до 5");
        }

        for (Integer genre : genres) {
            GenreDTO genreDTO = this.genreService.get(genre);
            if (genreDTO == null) {
                throw new IllegalArgumentException("Жанра не существует");

            }
        }

        if (!checkHasNoDuplicates(genres)) {
            throw new IllegalArgumentException("Введено несколько одинаковых жанров");
        }

        if (createDTO.getAbout().isBlank()) {
            throw new IllegalArgumentException("Не введено ничего в поле о себе");
        }



    }


    private boolean checkHasNoDuplicates(Integer[] genres) {

        Set<Integer> tempSet = new HashSet<>();

        for (Integer genre : genres) {
            if (!tempSet.add(genre)) {
                return false;
            }
        }
        return true;

    }
}
