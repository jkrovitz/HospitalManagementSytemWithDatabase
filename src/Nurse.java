/**
 * Nurse.java
 * @author Jeremy Krovitz
 * 
 * Adds, lists, updates, and deletes nurses from the system.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Nurse extends Staff {
    
    void insertNurse() {
        super.insertStaff("Nurse");
    }
    
    void getNurse() {
        super.getStaff("SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = 'Nurse'");
    }
    
    void chooseNurseUpdate() throws Throwable {
        String sqlA = "UPDATE staff " + "SET staff_name = ? " + "WHERE staff_id = ? and desg = 'Nurse'";
        String sqlB = "UPDATE staff " + "SET sex = ? " + "WHERE staff_id = ? and desg = 'Nurse'";
        String sqlC = "UPDATE staff " + "SET salary = ? " + "WHERE staff_id = ? and desg = 'Nurse'";
        super.chooseStaffUpdate("Nurse", sqlA, sqlB, sqlC);
    }
    
    void deleteNurse() {
        super.deleteEntity("Nurse", "DELETE FROM staff WHERE staff_id = ? and desg = 'Nurse'");
    }
}
