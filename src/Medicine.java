import java.sql.*;
import java.util.Scanner;

/**
 * Medicine.java
 * @author Jeremy Krovitz
 * 
 * Adds, lists, updates, and deletes medicine from the system.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Medicine extends Database {
    private String medName, medComp, expDate;
    private int medCost, medCount, medId;
    private Scanner input;
    private Database db;

    void createMedicineTable() {
        super.createTable("CREATE TABLE IF NOT EXISTS medicine (\n"
                + "     med_id int PRIMARY KEY, \n"
                + "     med_name Varchar(40), \n"
                + "     med_comp Varchar(40), \n"
                + "     exp_date Varchar(40), \n"
                + "     med_cost int, \n"
                + "     med_count int)");
    }

    void insertMedicine() {
        db = new Database();

        try (Connection connection = db.connectToDB();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO medicine(med_id, med_name, "
                        + "med_comp, exp_date, med_cost, med_count) "
                        + "VALUES(?,?,?,?,?,?) ON CONFLICT (med_id) "
                        + "DO NOTHING",
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

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        db = new Database();

        try {
            connection = db.connectToDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT med_id, med_name, med_comp, exp_date, med_cost, med_count FROM medicine ORDER BY med_id"
            );
            displayMedicine(resultSet);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayMedicine(ResultSet resultSet) throws SQLException {
        System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n",
                "Medicine ID","Medicine Name","Company","Expiration Date","Cost","Count");
        System.out.println("---------------------------------------------"
                + "-------------------------------------------------------"
                + "------");
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
    
    void chooseMedicineUpdate() throws Throwable {
        String choice;
        input = new Scanner(System.in);

        String promptBasedOnChoice;
        String updateSQL;

        Integer updateSelectionInteger = null;
        String updateSelection = "";

        System.out.print("\nEnter the letter of the update that you would like to make."
                + "\na. Change the medicine's name"
                + "\nb. Change the company's name"
                + "\nc. Update the expiration date"
                + "\nd. Update the medicine cost"
                + "\ne. Update the quantity\n");
        choice = input.next();

        switch (choice) {
            case "a":
                this.medId = super.getIdOfEntityToUpdate("medicine");
                promptBasedOnChoice = "\nWhat would you like to change the medicine's name to?";
                updateSQL = "UPDATE medicine " + "SET med_name = ? " + "WHERE med_id = ?";
                super.updateEntity(this.medId, this.medName, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "b":
                this.medId = super.getIdOfEntityToUpdate("medicine");
                promptBasedOnChoice = "\nWhat is the new medicine company?";
                updateSQL = "UPDATE medicine " + "SET med_comp = ? " + "WHERE med_id = ?";
                super.updateEntity(this.medId, this.medComp, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "c":
                this.medId = super.getIdOfEntityToUpdate("medicine");
                promptBasedOnChoice = "\nWhat should the updated expiration date be? ";
                updateSQL = "UPDATE medicine " + "SET exp_date = ? " + "WHERE med_id = ?";
                super.updateEntity(this.medId, this.expDate, updateSQL, promptBasedOnChoice, updateSelectionInteger);
                break;

            case "d":
                this.medId = super.getIdOfEntityToUpdate("medicine");
                promptBasedOnChoice = "\nWhat is the new medicine cost? ";
                updateSQL = "UPDATE medicine " + "SET med_cost = ? " + "WHERE med_id = ?";
                Integer medCostInteger = medCost;
                super.updateEntity(this.medId, updateSelection, updateSQL, promptBasedOnChoice, medCostInteger);
                break;

            case "e":
                this.medId = super.getIdOfEntityToUpdate("medicine");
                promptBasedOnChoice = "\nWhat is the quantity of medicine on hand? ";
                updateSQL = "UPDATE medicine " + "SET med_count = ? " + "WHERE med_id = ?";
                Integer medCountInteger = medCount;
                super.updateEntity(this.medId, updateSelection, updateSQL, promptBasedOnChoice, medCountInteger);
                break;
        }
    }
    
    void deleteMedicine() {        
        super.deleteEntity("medicine", "DELETE FROM medicine WHERE med_id = ?");
    }
}
