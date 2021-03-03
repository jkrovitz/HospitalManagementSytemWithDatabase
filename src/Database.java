import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Database.java
 * @author Jeremy Krovitz
 * 
 * Sets up a database connection and creates tables.
 */
class Database {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "123";

    Connection connectToDB() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    void createTable(String table) {
        Connection c = null;
        Statement stmt = null;

        try {
            c = connectToDB();
            stmt = c.createStatement();
            stmt.executeUpdate(table);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
