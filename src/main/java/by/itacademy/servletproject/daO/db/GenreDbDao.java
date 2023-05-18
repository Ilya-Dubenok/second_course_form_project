package by.itacademy.servletproject.daO.db;

import by.itacademy.servletproject.core.dto.GenreDTO;
import by.itacademy.servletproject.daO.api.IGenreDao;
import by.itacademy.servletproject.utils.db.DataSourceConnector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDbDao implements IGenreDao {

    private final JdbcTemplate template =
            new JdbcTemplate(
                    DataSourceConnector.getInstance().getDataSource()
            );

    private final List<String> DEFAULT_GENRES_LIST = new ArrayList<>(
            List.of(
                    "Hip-hop",
                    "Диско",
                    "Реп",
                    "Эстрада",
                    "Рэгги"
            )
    );


    public GenreDbDao() {
        if (tableIsEmpty()) {
            formDefaultValues();
        }
    }

    private GenreDTO createGenre(ResultSet set) throws SQLException {
        return new GenreDTO(
                set.getInt("id"),
                set.getString("name")
        );

    }

    @Override
    public List<GenreDTO> get() {
        return template.execute(
                "SELECT id, name FROM app.genres;",
                (PreparedStatementCallback<List<GenreDTO>>) pst -> {
                    List<GenreDTO> res = new ArrayList<>();
                    ResultSet set = pst.executeQuery();
                    while (set.next()) {
                        res.add(createGenre(set));
                    }
                    return res;
                }
        );
    }

    @Override
    public GenreDTO get(int id) {
        return template.execute(
                "SELECT id, name FROM app.genres " +
                        "WHERE id=?;"
                ,
                (PreparedStatementCallback<GenreDTO>) pst -> {
                    pst.setInt(1, id);
                    ResultSet set = pst.executeQuery();
                    if (set.next()) {
                        return createGenre(set);
                    } else {
                        return null;
                    }

                }
        );
    }

    @Override
    public GenreDTO save(GenreDTO item) {
        return template.execute(
                "INSERT INTO app.genres (name) " +
                        "VALUES (?) RETURNING id;",
                (PreparedStatementCallback<GenreDTO>) pst -> {
                    pst.setString(1, item.getName());

                    ResultSet set = pst.executeQuery();
                    set.next();
                    return new GenreDTO(set.getInt(1), item.getName());

                }
        );
    }

    private boolean tableIsEmpty() {
        return Boolean.FALSE.equals(template.query("SELECT 1 FROM app.genres LIMIT 1",
                ResultSet::next
        ));

    }

    private void formDefaultValues() {
        for (String defaultGenreName : DEFAULT_GENRES_LIST) {
            save(new GenreDTO(0, defaultGenreName));

        }

    }

}
