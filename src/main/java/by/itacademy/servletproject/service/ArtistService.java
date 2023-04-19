package by.itacademy.servletproject.service;

import by.itacademy.servletproject.core.dto.ArtistCreateDTO;
import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.daO.api.IArtistDao;
import by.itacademy.servletproject.service.api.IArtistService;

import java.util.*;

public class ArtistService implements IArtistService {

    private final IArtistDao artistDao;

    public ArtistService(IArtistDao artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public List<ArtistDTO> get() {
        return artistDao.get();
    }

    @Override
    public ArtistDTO get(int id) {
        return artistDao.get(id);
    }


    @Override
    public String validate(Integer[] artistIDS) {
        String res = "";
        if (artistIDS.length > 1) {
            res = res.concat("Слишком много артистов выбрано");
        }
        if (artistDao.get(artistIDS[0]) == null) {
            res = res.concat("Нет артиста по такому id");
        }
        return res;

    }

    @Override
    public ArtistDTO save(ArtistCreateDTO item) {
        int id = this.get().stream().mapToInt(ArtistDTO::getId)
                .max().orElseThrow();

        ArtistDTO dto = new ArtistDTO();
        dto.setName(item.getName());
        dto.setId(id + 1);

        return artistDao.save(dto);
    }


    @Override
    public Map<ArtistDTO, Integer> getSortedVotesInfo() {
        Map<ArtistDTO, Integer> res = new LinkedHashMap<>();
        Map<Integer, Integer> votes = this.artistDao.getMapOfVotes();
        votes.entrySet()
                .stream()
                .sorted(((o1, o2) -> o2.getValue() - o1.getValue()))
                .forEach(
                        x -> res.put(
                                this.artistDao.get(x.getKey()),
                                x.getValue())
                );

        return res;
    }

    @Override
    public void putVotes(Integer[] artistIDS) throws IllegalArgumentException {
        String validate = this.validate(artistIDS);
        if (!validate.isBlank()) {
            throw new IllegalArgumentException(validate);
        }
        this.artistDao.putNewVoices(artistIDS);
    }
}
