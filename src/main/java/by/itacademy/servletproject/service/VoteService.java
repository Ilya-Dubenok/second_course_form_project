package by.itacademy.servletproject.service;

import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.core.dto.VoteCreateDTO;
import by.itacademy.servletproject.service.api.IArtistService;
import by.itacademy.servletproject.service.api.IGenreService;
import by.itacademy.servletproject.service.api.IVoteService;

public class VoteService implements IVoteService {

    private IArtistService artistService;

    private IGenreService genreService;


    public VoteService(IArtistService artistService, IGenreService genreService) {
        this.artistService = artistService;
        this.genreService = genreService;
    }

    @Override
    public void save(VoteCreateDTO createDTO) {
        validate(createDTO);

    }

    public void validate(VoteCreateDTO createDTO) {
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

    }
}
