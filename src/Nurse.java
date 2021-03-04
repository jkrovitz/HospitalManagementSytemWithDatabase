/**
 * Nurse.java
 * @author Jeremy Krovitz
 * 
 * Adds a new nurse to the system and lists all of the nurses in the system.
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
        String sql = "SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = 'Nurse'";
        super.getStaff(sql);
    }
    
    void chooseNurseUpdate() throws Throwable {
        String sqlA = "UPDATE staff " + "SET staff_name = ? " + "WHERE staff_id = ? and desg = 'Nurse'";
        String sqlB = "UPDATE staff " + "SET sex = ? " + "WHERE staff_id = ? and desg = 'Nurse'";
        String sqlC = "UPDATE staff " + "SET salary = ? " + "WHERE staff_id = ? and desg = 'Nurse'";
        super.chooseStaffUpdate("Nurse", sqlA, sqlB, sqlC);
    }
    
    void deleteNurse() {
        String sql = "DELETE FROM staff WHERE staff_id = ? and desg = 'Nurse'";
        String entity = "Nurse";
        
        super.deleteEntity(entity, sql);
    }
}
