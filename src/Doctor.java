import java.util.Scanner;
import java.sql.*;
/**
 * Doctor.java
 * @author Jeremy Krovitz
 * 
 * Adds new doctor to the system and lists all doctors in the system.
 * 
 * This code was adapted and modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here: 
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Doctor {
    private String specialist, appoint, docQual, dName;
    private int dId, dRoom;
    private Database db;
    private Scanner input;
    
    void createDoctorTable() {
        String doctorTable = "CREATE TABLE IF NOT EXISTS doctor (\n"
                + "     d_id int PRIMARY KEY, \n"
                + "     d_name Varchar(40), \n"
                + "     specialist Varchar(40), \n"
                + "     appoint Varchar(40), \n"
                + "     doc_qual Varchar(40), \n"
                + "     d_room int)";

        db = new Database();
        db.createTable(doctorTable);
    }
    
    void insertDoctor() {
        String SQL = "INSERT INTO doctor(d_id, d_name, " + "specialist, doc_qual, appoint, d_room) "
                + "VALUES(?,?,?,?,?,?) ON CONFLICT (d_id) DO NOTHING";
        db = new Database();

        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, getdId());
            statement.setString(2, getdName());
            statement.setString(3, getSpecialist());
            statement.setString(4, getDocQual());
            statement.setString(5, getAppoint());
            statement.setInt(6, getdRoom());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    int getdId() {
        input = new Scanner(System.in);
        System.out.print("Doctor ID:-");
        this.dId = input.nextInt();
        return this.dId;
    }

    String getdName() {
        input = new Scanner(System.in);
        System.out.print("Doctor Name:-");
        this.dName = input.next();
        return this.dName;
    }

    String getSpecialist() {
        input = new Scanner(System.in);
        System.out.print("Specialist:-");
        this.specialist = input.next();
        return this.specialist;
    }

    String getAppoint() {
        input = new Scanner(System.in);
        System.out.print("Appointment Times:-");
        this.appoint = input.next();
        return this.appoint;
    }

    String getDocQual() {
        input = new Scanner(System.in);
        System.out.print("Doctor Qualifcations:-");
        this.docQual = input.next();
        return this.docQual;
    }

    int getdRoom() {
        input = new Scanner(System.in);
        System.out.print("Doctor Room Number:-");
        this.dRoom = input.nextInt();
        return this.dRoom;
    }

    void getDoctor() {

        String sql = "SELECT d_id, d_name, specialist, appoint, doc_qual, d_room FROM doctor ORDER BY d_id";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();

        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            displayDoctor(resultSet);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void displayDoctor(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n", "Doctor ID", "Doctor Name", "Specialist",
                "Appointment Times", "Qualificaitons", "Room Number");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25s%-25s%-25s%-25d\n", resultSet.getInt("d_id"),
                    resultSet.getString("d_name"), resultSet.getString("specialist"), resultSet.getString("appoint"),
                    resultSet.getString("doc_qual"), resultSet.getInt("d_room"));
        }
    }
    
    void getDoctorIdToDelete() {
        input = new Scanner(System.in);
        System.out.print("Which doctor id should be deleted?");
        this.dId = input.nextInt();
        deleteDoctor(dId);
    }
    
    private void deleteDoctor(int dId) {
        String sql = "DELETE FROM doctor WHERE d_id = ?";
        db = new Database();
        
        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, dId);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }
}
