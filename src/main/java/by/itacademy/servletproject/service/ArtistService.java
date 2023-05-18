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

    public ArtistDTO save(ArtistCreateDTO artistCreateDTO) {


        return artistDao.save(
                new ArtistDTO(0, artistCreateDTO.getName())
        );
    }



//    @Override
//    public ArtistDTO save(ArtistCreateDTO item) {
//        int id = this.get().stream().mapToInt(ArtistDTO::getId)
//                .max().orElseThrow();
//
//        ArtistDTO dto = new ArtistDTO();
//        dto.setName(item.getName());
//        dto.setId(id + 1);
//
//        return artistDao.save(dto);
//    }


}
