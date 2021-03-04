import java.sql.*;
import java.util.Scanner;

/**
 * Lab.java
 * @author Jeremy Krovitz
 * 
 * Adds, lists, updates, and deletes labs from the system.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Lab extends Database {
	private int labId;
	private String labName;
	private int labCost;
	private Database db;
	private Scanner input;
	
    public void createLabTable() {
        super.createTable("CREATE TABLE IF NOT EXISTS lab (\n"
                + "     lab_id int PRIMARY KEY, \n"
                + "     lab_name Varchar(40), \n"
                + "     lab_cost Varchar(40))");
    }
    
    public void insertLab() {
        String SQL = "INSERT INTO lab(lab_id, lab_name, "
                + "lab_cost) "
                + "VALUES(?,?,?) ON CONFLICT (lab_id) DO NOTHING";
        db = new Database();
        
        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, getLabId());
            statement.setString(2, getLabName());
            statement.setInt(3, getLabCost());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int getLabId() {
        input = new Scanner(System.in);
        System.out.print("Lab Id:-");
        this.labId = input.nextInt();
        return this.labId;
    }
    
    public String getLabName() {
        input = new Scanner(System.in);
        System.out.print("Lab Name:-");
        this.labName = input.next();
        return this.labName;
    }
    
    public int getLabCost() {
        input = new Scanner(System.in);
        System.out.print("Lab Cost:-");
        this.labCost = input.nextInt();
        return this.labCost;
    }
    
    public void getLab() {
        
        String sql = "SELECT lab_id, lab_name, lab_cost FROM lab";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();
        
        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            displayLab(resultSet);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void displayLab(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s%-25s\n",
                "Lab ID","Lab Name","Lab Cost");
        System.out.println("---------------------------------------------"
                + "-------------");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25d\n",
                    resultSet.getInt("lab_id"),
                    resultSet.getString("lab_name"),
                    resultSet.getInt("lab_cost"));       
        }   
    }
    
    void chooseLabUpdate() throws Throwable {
        String choice;
        input = new Scanner(System.in);

        String promptBasedOnChoice;
        String updateSQL;

        Integer updateSelectionInteger = null;
        String updateSelection = "";

        System.out.print("\nEnter the letter of the update that you would like to make."
                + "\na. Change the lab's name"
                + "\nb. Update the cost of the lab\n");
        choice = input.next();

        switch (choice) {
            case "a":
                this.labId = super.getIdOfEntityToUpdate("lab");
                promptBasedOnChoice = "\nWhat would you like to change the lab's name to? ";
                updateSQL = "UPDATE lab " + "SET lab_name = ? " + "WHERE lab_id = ?";
                super.updateEntity(this.labId, this.labName, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "b":
                this.labId = super.getIdOfEntityToUpdate("lab");
                promptBasedOnChoice = "\nWhat is the new cost of the lab?";
                updateSQL = "UPDATE lab " + "SET lab_cost = ? " + "WHERE lab_id = ?";
                Integer labCostInteger = labCost;
                super.updateEntity(this.labId, updateSelection, updateSQL, promptBasedOnChoice, labCostInteger);
                break;
        }
    }
    
    void deleteLab() {
        super.deleteEntity("lab", "DELETE FROM lab WHERE lab_id = ?");
    }
}
