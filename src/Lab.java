import java.sql.*;
import java.util.Scanner;

/**
 * Lab.java
 * @author Jeremy Krovitz
 * 
 * Adds a new lab to the system and lists all of the labs in the system.
 * 
 * This code was adapted and modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here: 
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Lab {
	private int labId;
	private String labName;
	private int labCost;
	private Database db;
	private Scanner input;
	
    public void createLabTable() {
        String LabTable = "CREATE TABLE IF NOT EXISTS lab (\n"
                + "     lab_id int PRIMARY KEY, \n"
                + "     lab_name Varchar(40), \n"
                + "     lab_cost Varchar(40))";

        db = new Database();
        db.createTable(LabTable);
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
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25d\n",
                    resultSet.getInt("lab_id"),
                    resultSet.getString("lab_name"),
                    resultSet.getInt("lab_cost"));       
        }   
    }	
}
