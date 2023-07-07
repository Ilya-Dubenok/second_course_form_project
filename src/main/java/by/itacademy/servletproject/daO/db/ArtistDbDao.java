package by.itacademy.servletproject.daO.db;

import by.itacademy.servletproject.core.dto.ArtistDTO;
import by.itacademy.servletproject.daO.api.IArtistDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistDbDao implements IArtistDao {

    private final JdbcTemplate template;

    private final List<String> DEFAULT_ARTISTS_LIST = new ArrayList<>(
            List.of(
                    "Леонтьев",
                    "Пугачёва",
                    "Моцарт",
                    "50cent",
                    "Морген",
                    "Хлеб"
            )
    );


    public ArtistDbDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        if (tableIsEmpty()) {
            formDefaultValues();
        }

    }


    private ArtistDTO createArtist(ResultSet set) throws SQLException {
        return new ArtistDTO(
                set.getInt("id"),
                set.getString("name")
        );

    }

    @Override
    public List<ArtistDTO> get() {
        return template.execute(
                "SELECT id, name FROM app.artists;",
                (PreparedStatementCallback<List<ArtistDTO>>) pst -> {
                    List<ArtistDTO> res = new ArrayList<>();
                    ResultSet set = pst.executeQuery();
                    while (set.next()) {
                        res.add(createArtist(set));
                    }
                    return res;
                }
        );

    }

    @Override
    public ArtistDTO get(int id) {
        return template.execute(
                "SELECT id, name FROM app.artists " +
                        "WHERE id=?;"
                ,
                (PreparedStatementCallback<ArtistDTO>) pst -> {
                    pst.setInt(1, id);
                    ResultSet set = pst.executeQuery();
                    if (set.next()) {
                        return createArtist(set);
                    } else {
                        return null;
                    }

                }
        );
    }

    @Override
    public ArtistDTO save(ArtistDTO item) {
        return template.execute(
                "INSERT INTO app.artists (name) " +
                        "VALUES (?) RETURNING id;",
                (PreparedStatementCallback<ArtistDTO>) pst -> {
                    pst.setString(1, item.getName());

                    ResultSet set = pst.executeQuery();
                    set.next();
                    return new ArtistDTO(set.getInt(1), item.getName());

                }
        );
    }

    private boolean tableIsEmpty() {
        return Boolean.FALSE.equals(template.query("SELECT 1 FROM app.artists LIMIT 1",
                ResultSet::next
        ));

    }

    private void formDefaultValues() {
        for (String defaultArtistName : DEFAULT_ARTISTS_LIST) {
            save(new ArtistDTO(0, defaultArtistName));

        }

    }


}
