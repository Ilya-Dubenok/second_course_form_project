package by.itacademy.servletproject.daO.memory;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.daO.api.IArtistDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArtistMemoryDao implements IArtistDao {


    private final Map<Integer, ArtistDTO> artists = new ConcurrentHashMap<>();

    public ArtistMemoryDao() {

        {
            ArtistDTO artistDTO = new ArtistDTO(1, "Леонтьев");
            artists.put(artistDTO.getId(), artistDTO);

        }

        {
            ArtistDTO artistDTO = new ArtistDTO(2, "Пугачёва");
            artists.put(artistDTO.getId(), artistDTO);

        }

        {
            ArtistDTO artistDTO = new ArtistDTO(3, "Моцарт");
            artists.put(artistDTO.getId(), artistDTO);

        }

        {
            ArtistDTO artistDTO = new ArtistDTO(4, "50cent");
            artists.put(artistDTO.getId(), artistDTO);

        }

        {
            ArtistDTO artistDTO = new ArtistDTO(5, "Морген");
            artists.put(artistDTO.getId(), artistDTO);

        }

        {
            ArtistDTO artistDTO = new ArtistDTO(6, "Хлеб");
            artists.put(artistDTO.getId(), artistDTO);

        }


    }

    @Override
    public List<ArtistDTO> get() {
        return new ArrayList<>(this.artists.values());
    }

    @Override
    public ArtistDTO get(int id) {
        return this.artists.get(id);
    }

    @Override
    public ArtistDTO save(ArtistDTO item) {
        this.artists.put(item.getId(), item);
        return item;
    }
}
