import java.sql.*;
import java.util.Scanner;

/**
 * Patient.java
 * @author Jeremy Krovitz
 * 
 * Adds a new patient to the system and lists all of the patients in the
 * system. 
 * 
 * This code was adapted and modified from Hospital Management
 * System Project in Java by Ghanendra Yadav on 6 Nov. 2017. Original
 * source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Patient
{
    private String pName, disease, sex, admitStatus;
    private int age, pId;
    private Scanner input;
    private Database db;
    
    void createPatientTable() {
        String patientTable = "CREATE TABLE IF NOT EXISTS patient (\n"
                + "     p_id int PRIMARY KEY, \n"
                + "     p_name Varchar(40), \n"
                + "     disease Varchar(40), \n"
                + "     sex Varchar(40), \n"
                + "     admit_status Varchar(40), \n"
                + "     age int)";

        db = new Database();
        db.createTable(patientTable);
    }
    
    void insertPatient() {
        String SQL = "INSERT INTO patient(p_id, p_name, "
                + "disease, sex, admit_status, age) "
                + "VALUES(?,?,?,?,?,?) ON CONFLICT (p_id) DO NOTHING";
        db = new Database();
        
        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(SQL,
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
        
        String sql = "SELECT p_id, p_name, disease, sex, admit_status, age FROM patient";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();
        
        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
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
                this.pId = getIdOfPatientToUpdate();
                promptBasedOnChoice = "\nWhat would you like to change the patient's name to? ";
                updateSQL = "UPDATE patient " + "SET p_name = ? " + "WHERE p_id = ?";
                updatePatient(this.pId, this.pName, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "b":
                this.pId = getIdOfPatientToUpdate();
                promptBasedOnChoice = "\nWhat would you like to change the patient's disease to? ";
                updateSQL = "UPDATE patient " + "SET disease = ? " + "WHERE p_id = ?";
                updatePatient(this.pId, this.disease, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "c":
                this.pId = getIdOfPatientToUpdate();
                promptBasedOnChoice = "\nWhat would you like to change the patient's gender to? ";
                updateSQL = "UPDATE patient " + "SET sex = ? " + "WHERE p_id = ?";
                updatePatient(this.pId, this.sex, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "d":
                this.pId = getIdOfPatientToUpdate();
                promptBasedOnChoice = "\nWhat would you like to change the admit status to? ";
                updateSQL = "UPDATE patient " + "SET admit_status = ? " + "WHERE p_id = ?";
                updatePatient(this.pId, this.admitStatus, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "e":
                this.pId = getIdOfPatientToUpdate();
                promptBasedOnChoice = "\nWhat should the patient's age be updated to? ";
                updateSQL = "UPDATE patient " + "SET age = ? " + "WHERE p_id = ?";
                Integer ageInteger = age;
                updatePatient(this.pId, updateSelection, updateSQL, promptBasedOnChoice, ageInteger);
                break;
        }
    }

    private int getIdOfPatientToUpdate() {
        input = new Scanner(System.in);
        System.out.print("\nWhat is the id that corresponds with the patient that you want to update? ");
        return input.nextInt();
    }

    private void updatePatient(int pId, String updateSelection, String sql, String prompt,
            Integer updateSelectionInteger) {
        input = new Scanner(System.in);
        System.out.print(prompt);

        db = new Database();

        if (updateSelection != "") {
            updateSelection = input.next();
            try (Connection connection = db.connectToDB();
                    PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, updateSelection);
                statement.setInt(2, this.pId);

                statement.executeUpdate();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        else if (updateSelectionInteger != null) {
            int updateSelectionInt = updateSelectionInteger.intValue();
            updateSelectionInt = input.nextInt();
            try (Connection connection = db.connectToDB();
                    PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, updateSelectionInt);
                statement.setInt(2, this.pId);

                statement.executeUpdate();
                connection.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
