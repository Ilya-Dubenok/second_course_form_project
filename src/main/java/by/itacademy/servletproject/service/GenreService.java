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

    @Override
    public String validate(Integer[] artistIDS) {
        String res = "";
        if (artistIDS.length < 3 || artistIDS.length > 5) {
            res = res.concat("Выбрано неверное количество жанров");
        }
        for (Integer artistID : artistIDS) {
            if (genreDao.get(artistIDS[0]) == null) {
                res = res.concat("Нет артиста по такому id: " + artistID);
            }
        }

        return res;

    }

    @Override
    public void putVotes(Integer[] genreIDs) throws IllegalArgumentException {
        String validate = this.validate(genreIDs);
        if (!validate.isBlank()) {
            throw new IllegalArgumentException(validate);
        }
        this.genreDao.putNewVoices(genreIDs);
    }

    @Override
    public Map<GenreDTO, Integer> getSortedVotesInfo() {
        Map<GenreDTO, Integer> res = new LinkedHashMap<>();
        Map<Integer, Integer> votes = this.genreDao.getMapOfVotes();
        votes.entrySet()
                .stream()
                .sorted(((o1, o2) -> o2.getValue() - o1.getValue()))
                .forEach(
                        x -> res.put(
                                this.genreDao.get(x.getKey()),
                                x.getValue())
                );

        return res;
    }

}
