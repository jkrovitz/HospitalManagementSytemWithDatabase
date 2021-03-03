import java.sql.*;
import java.util.Scanner;

/**
 * Staff.java
 * 
 * @author Jeremy Krovitz
 * 
 *         Parent class creates new staff of type Nurse, Worker, and Security as
 *         well as lists all of the instances for each type—Nurse, Worker, and
 *         Security— in the system.
 * 
 *         This code was adapted and modified from Hospital Management System
 *         Project in Java by Ghanendra Yadav on 6 Nov. 2017. Original source
 *         code available here:
 *         https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Staff {
    private String staffName, sex;
    private int salary, staffId;
    private Database db;
    private Scanner input;
    
    public void createStaffTable() {
        String staffTable = "CREATE TABLE IF NOT EXISTS staff (\n"
                + "     staff_id int PRIMARY KEY, \n"
                + "     staff_name Varchar(40), \n"
                + "     desg Varchar(40), \n"
                + "     sex Varchar(40), \n"
                + "     salary int)";

        db = new Database();
        db.createTable(staffTable);
    }
    
    public void insertStaff(String desg) {
        String SQL = "INSERT INTO staff(staff_id, staff_name, "
                + "desg, sex, salary) "
                + "VALUES(?,?, ?,?,?) ON CONFLICT (staff_id) DO NOTHING";
        db = new Database();
        
        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(SQL,
                Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, getStaffId());
            statement.setString(2, getStaffName());
            statement.setString(3, desg);
            statement.setString(4, getSex());
            statement.setInt(5, getSalary());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int getStaffId() {
        input = new Scanner(System.in);
        System.out.print("Staff Id:-");
        this.staffId = input.nextInt();
        return this.staffId;
    }
    
    public String getStaffName() {
        input = new Scanner(System.in);
        System.out.print("Staff Name:-");
        this.staffName = input.next();
        return this.staffName;
    }
    
    public String getSex() {
        input = new Scanner(System.in);
        System.out.print("Gender:-");
        this.sex = input.next();
        return this.sex;
    }
    
    public int getSalary() {
        input = new Scanner(System.in);
        System.out.print("Salary:-");
        this.salary = input.nextInt();
        return this.salary;
    }
    
    public void getStaff(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();
        
        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            displayStaff(resultSet);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void displayStaff(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s\n",
                "Staff ID","Staff Name","Designation","Gender","Salary");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25s%-25s%-25d\n",
                    resultSet.getInt("staff_id"),
                    resultSet.getString("staff_name"),
                    resultSet.getString("desg"),
                    resultSet.getString("sex"),
                    resultSet.getInt("salary"));         
        }   
    }
}
