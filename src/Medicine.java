import java.sql.*;
import java.util.Scanner;

/**
 * Medicine.java
 * @author Jeremy Krovitz
 * 
 * Adds a new medicine to the system and lists all of the medicines in the system.
 * 
 * This code was adapted and modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here: 
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Medicine {
    private String medName, medComp, expDate;
    private int medCost, medCount, medId;
    private Scanner input;
    private Database db;

    void createMedicineTable() {
        String medicineTable = "CREATE TABLE IF NOT EXISTS medicine (\n"
                + "     med_id int PRIMARY KEY, \n"
                + "     med_name Varchar(40), \n"
                + "     med_comp Varchar(40), \n"
                + "     exp_date Varchar(40), \n"
                + "     med_cost int, \n"
                + "     med_count int)";

        db = new Database();
        db.createTable(medicineTable);
    }

    void insertMedicine() {
        String SQL = "INSERT INTO medicine(med_id, med_name, "
                + "med_comp, exp_date, med_cost, med_count) "
                + "VALUES(?,?,?,?,?,?) ON CONFLICT (med_id) DO NOTHING";
        db = new Database();

        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, getMedId());
            statement.setString(2, getMedName());
            statement.setString(3, getMedComp());
            statement.setString(4, getExpDate());
            statement.setInt(5, getMedCost());
            statement.setInt(6, getMedCount());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    int getMedId() {
        input = new Scanner(System.in);
        System.out.print("Medicine ID:-");
        this.medId = input.nextInt();
        return this.medId;
    }

    String getMedName() {
        input = new Scanner(System.in);
        System.out.print("Medicine Name:-");
        this.medName = input.next();
        return this.medName;
    }

    String getMedComp() {
        input = new Scanner(System.in);
        System.out.print("Company:-");
        this.medComp = input.next();
        return this.medComp;
    }

    String getExpDate() {
        input = new Scanner(System.in);
        System.out.print("Expiration Date:-");
        this.expDate = input.next();
        return this.expDate;
    }

    int getMedCost() {
        input = new Scanner(System.in);
        System.out.print("Cost:-");
        this.medCost = input.nextInt();
        return this.medCost;
    }

    int getMedCount() {
        input = new Scanner(System.in);
        System.out.print("Count:-");
        this.medCount = input.nextInt();
        return this.medCount;
    }

    void getMedicine() {

        String sql = "SELECT med_id, med_name, med_comp, exp_date, med_cost, med_count FROM medicine";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();

        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            displayMedicine(resultSet);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayMedicine(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n",
                "Medicine ID","Medicine Name","Company","Expiration Date","Cost","Count");
        while (resultSet.next()) {
            System.out.printf("%-25d%-25s%-25s%-25s%-25d%-25d\n",
                    resultSet.getInt("med_id"),
                    resultSet.getString("med_name"),
                    resultSet.getString("med_comp"),
                    resultSet.getString("exp_date"),
                    resultSet.getInt("med_cost"),
                    resultSet.getInt("med_count"));       
        }   
    }
}
