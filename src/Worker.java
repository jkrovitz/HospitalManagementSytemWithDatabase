/**
 * Worker.java
 * @author Jeremy Krovitz
 * 
 * Adds, lists, updates, and deletes workers from the system.
 * 
 * This code was adapted and modified from Hospital Management System Project in Java by 
 * Ghanendra Yadav on 6 Nov. 2017. Original source code available here: 
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class Worker extends Staff {

    void insertWorker() {
        super.insertStaff("Worker");
    }
    
    void getWorker() {
        super.getStaff("SELECT staff_id, staff_name, desg, sex, salary FROM staff WHERE desg = Worker");
    }
    
    void chooseWorkerUpdate() throws Throwable {
        String sqlA = "UPDATE staff " + "SET staff_name = ? " + "WHERE staff_id = ? and desg = 'Worker'";
        String sqlB = "UPDATE staff " + "SET sex = ? " + "WHERE staff_id = ? and desg = 'Worker'";
        String sqlC = "UPDATE staff " + "SET salary = ? " + "WHERE staff_id = ? and desg = 'Worker'";
        super.chooseStaffUpdate("Worker", sqlA, sqlB, sqlC);
    }
    
    void deleteWorker() {
        super.deleteEntity("Worker", "DELETE FROM staff WHERE staff_id = ? and desg = 'Worker'");
    }
}
