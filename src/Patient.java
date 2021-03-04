import java.sql.*;
import java.util.Scanner;

/**
 * Patient.java
 * @author Jeremy Krovitz
 * 
 * Adds, lists, updates, and deletes patients from the system.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Patient extends Database {
    private String pName, disease, sex, admitStatus;
    private int age, pId;
    private Scanner input;
    private Database db;
    
    void createPatientTable() {
        super.createTable("CREATE TABLE IF NOT EXISTS patient (\n"
                + "     p_id int PRIMARY KEY, \n"
                + "     p_name Varchar(40), \n"
                + "     disease Varchar(40), \n"
                + "     sex Varchar(40), \n"
                + "     admit_status Varchar(40), \n"
                + "     age int)");
    }
    
    void insertPatient() {
        db = new Database();
        
        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO patient(p_id, p_name, "
                                + "disease, sex, admit_status, age) "
                                + "VALUES(?,?,?,?,?,?) ON CONFLICT (p_id) DO NOTHING",
                Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, getpId());
            statement.setString(2, getpName());
            statement.setString(3, getdisease());
            statement.setString(4, getSex());
            statement.setString(5, getAdmitStatus());
            statement.setInt(6, getAge());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    int getpId() {
        input = new Scanner(System.in);
        System.out.print("Patient ID:-");
        this.pId = input.nextInt();
        return this.pId;
    }
    
    String getpName() {
        input = new Scanner(System.in);
        System.out.print("Patient Name:-");
        this.pName = input.next();
        String[] pNameArray = pName.split("\\s+");
        if (pNameArray.length > 1)
            pName += input.next();
        return this.pName;
    }
    
    String getdisease() {
        input = new Scanner(System.in);
        System.out.print("Disease:-");
        this.disease = input.next();
        String[] diseaseArray = disease.split("\\s+");
        if (diseaseArray.length > 1)
            disease += input.next();
        return this.disease;
    }
    
    String getAdmitStatus() {
        input = new Scanner(System.in);
        System.out.print("Admit Status:-");
        this.admitStatus = input.next();
        return this.admitStatus;
    }
    
    String getSex() {
        input = new Scanner(System.in);
        System.out.print("Gender:-");
        this.sex = input.next();
        return this.sex;
    }
    
    int getAge() {
        input = new Scanner(System.in);
        System.out.print("Age:-");
        this.age = input.nextInt();
        return this.age;
    }
    
    void getPatient() {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();
        
        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT p_id, p_name, disease, sex, admit_status, age FROM patient"
            );
            displayPatient(resultSet);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void displayPatient(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n",
                "Patient ID","Patient Name","Disease","Gender","Admit Status","Age");
        System.out.println("---------------------------------------------"
                + "-------------------------------------------------------"
                + "----------------------------");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25s%-25s%-25s%-25d\n",
                    resultSet.getInt("p_id"),
                    resultSet.getString("p_name"),
                    resultSet.getString("disease"),
                    resultSet.getString("sex"),
                    resultSet.getString("admit_status"),
                    resultSet.getInt("age"));    
        }   
    }
    
    void choosePatientUpdate() throws Throwable {
        String choice;
        input = new Scanner(System.in);

        String promptBasedOnChoice;
        String updateSQL;

        Integer updateSelectionInteger = null;
        String updateSelection = "";

        System.out.print("\nEnter the letter of the update that you would like to make."
                + "\na. Change the patient's name" 
                + "\nb. Change the patient's disease"
                + "\nc. Change gender"
                + "\nd. Change admit status"
                + "\ne. Change age\n");
        choice = input.next();

        switch (choice) {
            case "a":
                this.pId = getIdOfEntityToUpdate("patient");
                promptBasedOnChoice = "\nWhat would you like to change the patient's name to? ";
                updateSQL = "UPDATE patient " + "SET p_name = ? " + "WHERE p_id = ?";
                super.updateEntity(this.pId, this.pName, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "b":
                this.pId = getIdOfEntityToUpdate("patient");
                promptBasedOnChoice = "\nWhat would you like to change the patient's disease to? ";
                updateSQL = "UPDATE patient " + "SET disease = ? " + "WHERE p_id = ?";
                super.updateEntity(this.pId, this.disease, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "c":
                this.pId = getIdOfEntityToUpdate("patient");
                promptBasedOnChoice = "\nWhat would you like to change the patient's gender to? ";
                updateSQL = "UPDATE patient " + "SET sex = ? " + "WHERE p_id = ?";
                super.updateEntity(this.pId, this.sex, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "d":
                this.pId = getIdOfEntityToUpdate("patient");
                promptBasedOnChoice = "\nWhat would you like to change the admit status to? ";
                updateSQL = "UPDATE patient " + "SET admit_status = ? " + "WHERE p_id = ?";
                super.updateEntity(this.pId, this.admitStatus, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "e":
                this.pId = getIdOfEntityToUpdate("patient");
                promptBasedOnChoice = "\nWhat should the patient's age be updated to? ";
                updateSQL = "UPDATE patient " + "SET age = ? " + "WHERE p_id = ?";
                Integer ageInteger = age;
                super.updateEntity(this.pId, updateSelection, updateSQL, promptBasedOnChoice, ageInteger);
                break;
        }
    }
}
