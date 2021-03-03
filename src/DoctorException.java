/**
 * DoctorException.java
 * @author Jeremy Krovitz
 * 
 * Throws doctorException if the doctor's name does not start with dr. or Dr.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here: 
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
@SuppressWarnings("serial")
class DoctorException extends Exception{
	DoctorException(String message) {
		super(message);
	}
}
