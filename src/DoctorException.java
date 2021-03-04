/**
 * DoctorException.java
 * @author Jeremy Krovitz
 * 
 * Throws doctorException if the doctor's name does not start with dr. or Dr.
 */
@SuppressWarnings("serial")
class DoctorException extends Exception{
	DoctorException(String message) {
		super(message);
	}
}
