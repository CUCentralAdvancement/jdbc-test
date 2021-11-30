import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest {

    public static void main(String[] args) throws IOException {

        // Load connection config
        String configFile = "connection.conf";
        InputStream is = new FileInputStream(configFile);
        Properties configProps = new Properties();
        configProps.load(is);

        String url = configProps.getProperty("url");

        Properties connectionProps = new Properties();
        String[] propFields = {"user", "password", "ssl", "sslcert", "sslkey", "sslpassword", "sslrootcert", "sslmode", "sslfactory"};
        
        for (String prop : propFields) {
            connectionProps.setProperty(prop, configProps.getProperty(prop));
        }

        String SQL_SELECT = "SELECT * FROM public.entity_test;";

        try (Connection conn = DriverManager.getConnection(url, connectionProps);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long count = resultSet.getLong("count");
                System.out.printf("Result: %d%n", count);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}