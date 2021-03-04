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
class Doctor extends Database {
    private String specialist, appoint, docQual, dName;
    private int dId, dRoom;
    private Database db;
    private Scanner input;
    
    void createDoctorTable() throws Throwable {
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

            connection.close();

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

    void getDoctor() throws Throwable {

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
        System.out.println("---------------------------------------------"
                + "-------------------------------------------------------"
                + "------------------------------------");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25s%-25s%-25s%-25d\n", resultSet.getInt("d_id"),
                    resultSet.getString("d_name"), resultSet.getString("specialist"), resultSet.getString("appoint"),
                    resultSet.getString("doc_qual"), resultSet.getInt("d_room"));
        }
    }

    void deleteDoctor() {
        String sql = "DELETE FROM doctor WHERE d_id = ?";
        String entity = "doctor";
        
        super.deleteEntity(entity, sql);
    }

    void chooseDoctorUpdate() throws Throwable {
        String choice;
        input = new Scanner(System.in);

        String promptBasedOnChoice;
        String updateSQL;

        Integer updateSelectionInteger = null;
        String updateSelection = "";

        System.out.print("\nEnter the letter of the update that you would like to make."
                + "\na. Change the doctor's name"
                + "\nb. Change the doctor's appointment times"
                + "\nc. Change specialist"
                + "\nd. Change qualifications"
                + "\ne. Change room number\n");
        choice = input.next();

        switch (choice) {
            case "a":
                this.dId = super.getIdOfEntityToUpdate("doctor");
                promptBasedOnChoice = "\nWhat would you like to change the doctor's name to?";
                updateSQL = "UPDATE doctor " + "SET d_name = ? " + "WHERE d_id = ?";
                super.updateEntity(this.dId, this.dName, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "b":
                this.dId = super.getIdOfEntityToUpdate("doctor");
                promptBasedOnChoice = "\nWhen would you like to update the doctor's appointment times to take place?";
                updateSQL = "UPDATE doctor " + "SET appoint = ? " + "WHERE d_id = ?";
                super.updateEntity(this.dId, this.appoint, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "c":
                this.dId = super.getIdOfEntityToUpdate("doctor");
                promptBasedOnChoice = "\nWhat would you like to change specialist to? ";
                updateSQL = "UPDATE doctor " + "SET specialist = ? " + "WHERE d_id = ?";
                super.updateEntity(this.dId, this.specialist, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "d":
                this.dId = super.getIdOfEntityToUpdate("doctor");
                promptBasedOnChoice = "\nWhat would you like to change the qualifications to? ";
                updateSQL = "UPDATE doctor " + "SET doc_qual = ? " + "WHERE d_id = ?";
                super.updateEntity(this.dId, this.docQual, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "e":
                this.dId = super.getIdOfEntityToUpdate("doctor");
                promptBasedOnChoice = "\nWhat is the doctor's new room number? ";
                updateSQL = "UPDATE doctor " + "SET d_room = ? " + "WHERE d_id = ?";
                Integer dRoomInteger = dRoom;
                super.updateEntity(this.dId, updateSelection, updateSQL, promptBasedOnChoice, dRoomInteger);
                break;
        }
    }
}
