package by.itacademy.servletproject.service;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.core.dto.VoteDTO;
import by.itacademy.servletproject.daO.api.IVoteDao;
import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.api.IVoteService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VoteService implements IVoteService {


    private final IArtistService artistService;

    private final IGenreService genreService;

    private final IVoteDao voteDao;


    public VoteService(IArtistService artistService, IGenreService genreService, IVoteDao voteDao) {
        this.artistService = artistService;
        this.genreService = genreService;
        this.voteDao = voteDao;
    }


    @Override
    public void save(VoteCreateDTO createDTO) {
        validate(createDTO);
        VoteDTO vote = new VoteDTO(
                createDTO.getArtist(),
                createDTO.getGenres(),
                createDTO.getAbout()
        );
        voteDao.save(vote);

    }

    @Override
    public List<VoteDTO> get() {
        return voteDao.get();
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
