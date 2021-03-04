import java.sql.*;
import java.util.Scanner;
/**
 * Database.java
 * @author Jeremy Krovitz
 * 
 * Sets up a database connection, creates tables, deletes entities, gets
 * the id of an entity to update and updates the entity.
 */
class Database {
    private Scanner input;
    
    private final String url = "jdbc:postgresql://localhost:5433/postgres";
    private final String user = "postgres";
    private final String password = "postgres";

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
    
    void deleteEntity(String entity, String sql) {
        input = new Scanner(System.in);
        System.out.print("Which " + entity + " id should be deleted? ");
        int entityId = input.nextInt();

        try (Connection connection = connectToDB();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, entityId);
            statement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    int getIdOfEntityToUpdate(String entity) {
        input = new Scanner(System.in);
        System.out.print("\nWhat is the id that corresponds with the " + entity + " that you want to update? ");
        return input.nextInt();
    }

    void updateEntity(int entityId, String updateSelection, String sql, String prompt,
            Integer updateSelectionInteger) {
        input = new Scanner(System.in);
        System.out.print(prompt);

        if (updateSelection != "") {
            updateSelection = input.next();
            try (Connection connection = connectToDB();
                    PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, updateSelection);
                statement.setInt(2, entityId);

                statement.executeUpdate();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        else if (updateSelectionInteger != null) {
            int updateSelectionInt = updateSelectionInteger.intValue();
            updateSelectionInt = input.nextInt();
            try (Connection connection = connectToDB();
                    PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, updateSelectionInt);
                statement.setInt(2, entityId);

                statement.executeUpdate();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
