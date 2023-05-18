package by.itacademy.servletproject.utils.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceConnector {

    private final DataSource dataSource;



    private static class Holder{
        private static final DataSourceConnector instance = new DataSourceConnector();
    }

    private DataSourceConnector(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:postgresql://localhost:5432/voting";
        Properties props = new Properties();

        props.setProperty("dataSource.user", "postgres");
        props.setProperty("jdbcUrl", url);
        HikariConfig config = new HikariConfig(props);
        this.dataSource = new HikariDataSource(config);

    };


    public static DataSourceConnector getInstance(){
        return Holder.instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
