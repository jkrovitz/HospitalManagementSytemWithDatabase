/**
 * Worker.java
 * @author Jeremy Krovitz
 * 
 * Adds a new worker to the system and lists all of the workers in the
 * system. 
 * 
 * This code was modified from Hospital Management
 * System Project in Java by Ghanendra Yadav on 6 Nov. 2017. Original
 * source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Worker extends Staff {

    void insertWorker() {
        super.insertStaff("worker");
    }
    
    void getWorker() {
        String sql = "SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = worker";
        super.getStaff(sql);
    }
}
