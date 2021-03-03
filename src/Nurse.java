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
        super.insertStaff("nurse");
    }
    
    void getNurse() {
        String sql = "SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = 'nurse'";
        super.getStaff(sql);
    }
}
