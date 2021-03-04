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
        super.insertStaff("Security");
    }
    
    void getSecurity() {
        String sql = "SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = Security";
        super.getStaff(sql);
    }
    
    void chooseSecurityUpdate() throws Throwable {
        String sqlA = "UPDATE staff " + "SET staff_name = ? " + "WHERE staff_id = ? and desg = 'Security'";
        String sqlB = "UPDATE staff " + "SET sex = ? " + "WHERE staff_id = ? and desg = 'Security'";
        String sqlC = "UPDATE staff " + "SET salary = ? " + "WHERE staff_id = ? and desg = 'Security'";
        super.chooseStaffUpdate("Security", sqlA, sqlB, sqlC);
    }
    
    void deleteSecurity() {
        String sql = "DELETE FROM staff WHERE staff_id = ? and desg = 'Security'";
        String entity = "Security";
        
        super.deleteEntity(entity, sql);
    }
}
