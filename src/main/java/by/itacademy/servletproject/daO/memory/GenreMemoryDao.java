package by.itacademy.servletproject.daO.memory;

import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.daO.api.IGenreDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenreMemoryDao implements IGenreDao {

    private final Map<Integer, GenreDTO> genres = new HashMap<>();

    public GenreMemoryDao() {

        {
            GenreDTO dto = new GenreDTO(1, "Hip-hop");
            genres.put(dto.getId(), dto);
        }

        {
            GenreDTO dto = new GenreDTO(2, "Диско");
            genres.put(dto.getId(), dto);
        }

        {
            GenreDTO dto = new GenreDTO(3, "Реп");
            genres.put(dto.getId(), dto);
        }

        {
            GenreDTO dto = new GenreDTO(4, "Эстрада");
            genres.put(dto.getId(), dto);
        }

        {
            GenreDTO dto = new GenreDTO(5, "Рэгги");
            genres.put(dto.getId(), dto);
        }
    }

    @Override
    public List<GenreDTO> get() {
        return new ArrayList<>(this.genres.values());
    }

    @Override
    public GenreDTO get(int id) {
        return genres.get(id);
    }

    @Override
    public GenreDTO save(GenreDTO item) {
        this.genres.put(item.getId(), item);
        return item;
    }
}
