import java.sql.*;
import java.util.Scanner;

/**
 * Facility.java
 * @author Jeremy Krovitz
 * 
 * Adds, lists, updates, and deletes facilities from the system.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Facility extends Database {
    private String facilityName;
    private int facilityId;
    private Database db;
    private Scanner input;

    void createFacilityTable() {
        super.createTable("CREATE TABLE IF NOT EXISTS facility (\n" 
                + "     facility_id int PRIMARY KEY, \n"
                + "     facility_name Varchar(40))");
    }

    void insertFacility() {
        String SQL = "INSERT INTO facility(facility_id, facility_name) "
                + "VALUES(?,?) ON CONFLICT (facility_id) DO NOTHING";
        db = new Database();

        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, getFacilityId());
            statement.setString(2, getFacilityName());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    int getFacilityId() {
        input = new Scanner(System.in);
        System.out.print("Facility ID:-");
        this.facilityId = input.nextInt();
        return this.facilityId;
    }

    String getFacilityName() {
        input = new Scanner(System.in);
        System.out.print("Facility Name:-");
        this.facilityName = input.next();
        return this.facilityName;
    }

    void getFacility() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();

        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT facility_id, facility_name FROM facility");
            displayFacility(resultSet);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void displayFacility(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s\n", "Facility ID", "Facility Name");
        System.out.println("-----------------------------------------");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s\n", resultSet.getInt("facility_id"), resultSet.getString("facility_name"));
        }
    }
    
    void chooseFacilityUpdate() throws Throwable {
        this.facilityId = super.getIdOfEntityToUpdate("facility");
        super.updateEntity(this.facilityId, this.facilityName,
                "UPDATE facility " + "SET facility_name = ? " + "WHERE facility_id = ?",
                "\nWhat would you like to change the facility's name to? ", null);
    }
    
    void deleteFacility() {
        super.deleteEntity("facility", "DELETE FROM facility WHERE facility_id = ?");
    }
}