package by.itacademy.servletproject.service;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreCreateDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.daO.api.IGenreDao;
import by.itacademy.servletproject.service.api.IGenreService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenreService implements IGenreService {

    private final IGenreDao genreDao;

    public GenreService(IGenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<GenreDTO> get() {
        return genreDao.get();
    }

    @Override
    public GenreDTO get(int id) {
        return genreDao.get(id);
    }

    @Override
    public GenreDTO save(GenreCreateDTO item) {
        int id = this.get().stream().mapToInt(GenreDTO::getId)
                .max().orElseThrow();

        GenreDTO dto = new GenreDTO();
        dto.setName(item.getName());
        dto.setId(id + 1);


        return genreDao.save(dto);
    }





}
