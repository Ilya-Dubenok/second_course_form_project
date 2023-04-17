package by.itacademy.servletproject.daO.memory;

import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.daO.api.IGenreDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenreMemoryDao implements IGenreDao {

    private final Map<Integer, GenreDTO> genres = new ConcurrentHashMap<>();
    //TODO TO DELETE
    private final Map<Integer, Integer> genreVotes = new ConcurrentHashMap<>();

    private static GenreMemoryDao instance;

    public static GenreMemoryDao getInstance() {
        if (instance == null) {
            instance = new GenreMemoryDao();
        }
        return instance;
    }

    public GenreMemoryDao() {

        {
            GenreDTO dto = new GenreDTO(1, "Hip-hop");
            genres.put(dto.getId(), dto);
            //TODO TO DELETE
            genreVotes.put(dto.getId(), 0);
        }

        {
            GenreDTO dto = new GenreDTO(2, "Диско");
            genres.put(dto.getId(), dto);
            genreVotes.put(dto.getId(), 0);
        }

        {
            GenreDTO dto = new GenreDTO(3, "Реп");
            genres.put(dto.getId(), dto);
            genreVotes.put(dto.getId(), 0);
        }

        {
            GenreDTO dto = new GenreDTO(4, "Эстрада");
            genres.put(dto.getId(), dto);
            genreVotes.put(dto.getId(), 0);
        }

        {
            GenreDTO dto = new GenreDTO(5, "Рэгги");
            genres.put(dto.getId(), dto);
            genreVotes.put(dto.getId(), 0);
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
        //TODO TO DELETE
        this.genreVotes.put(item.getId(), 0);
        return item;
    }

    //TODO TO DELETE
    @Override
    public Map<Integer, Integer> getMapOfVotes() {
        return new HashMap<>(this.genreVotes);
    }


    @Override
    public void putNewVoices(Integer[] genreIDS) {
        for (Integer genreId : genreIDS) {
            genreVotes.compute(genreId, (K, V) ->
                    V == null ? 0 : V + 1);

        }
    }
}
