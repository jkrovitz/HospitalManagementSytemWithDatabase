/**
 * Security.java
 * @author Jeremy Krovitz
 * 
 * Adds new security guards to the system and lists all of the security guards in the
 * system. 
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Security extends Staff {
    void insertSecurity() {
        super.insertStaff("security");
    }
    
    void getSecurity() {
        String sql = "SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = security";
        super.getStaff(sql);
    }
}
