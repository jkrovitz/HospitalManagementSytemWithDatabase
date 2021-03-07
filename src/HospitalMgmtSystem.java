import java.util.*;

/**
 * HospitalMgmtSystem.java
 * @author Jeremy Krovitz
 * 
 * Driver class that handles navigation through the Hospital Management System software.
 * 
 * This code was modified from Hospital Management System Project in Java by Ghanendra Yadav
 * on 6 Nov. 2017. Original source code available here:
 * https://www.programmingwithbasics.com/2017/11/hospital-management-system-project-in.html
 */
class HospitalMgmtSystem {

    private static Doctor doctor;
    private static Patient patient;
    private static Medicine medicine;
    private static Facility facility;
    private static Lab lab;
    private static Staff staff;
    private static Scanner input;
    
    public static void main(String args[]) throws Throwable {
        welcome();
        displayCalendar();
        createTables();
        chooseEntities();
    }

    private static void welcome() {
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println(" *** Welcome to Hospital Management System Project in Java ***");
        System.out.println("---------------------------------------------------------------------------------------------------");
    }
    
    private static void displayCalendar() {
        String months[] = {

                "January", "February", "March", "April",

                "May", "June", "July", "August",

                "September", "October", "November", "December" };

        String days[] = {

                "Sunday", "Monday", "Tuesday", "Wednesday",

                "Thursday", "Friday", "Saturday" };

        Calendar calendar = Calendar.getInstance();

        int mins = calendar.get(Calendar.MINUTE);
        String minutes = String.format("%02d", mins);

        int secs = calendar.get(Calendar.SECOND);
        String seconds = String.format("%02d", secs);

        System.out.print(
                "Date: " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1] + ", " + months[calendar.get(Calendar.MONTH)]
                        + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR));

        String timeString = calendar.get(Calendar.HOUR_OF_DAY) + ":" + minutes + ":" + seconds;

        int h1 = (int) timeString.charAt(0) - '0';
        int h2 = (int) timeString.charAt(1) - '0';

        int hh = h1 * 10 + h2;

        // AM OR PM
        String amOrPmStr;
        if (hh < 12) {
            amOrPmStr = "AM";
        } else
            amOrPmStr = "PM";

        hh %= 12;

        // Handle 00 and 12 case separately
        if (hh == 0) {
            System.out.print("\t\t\t\t\t\tTime: " + "12");

            // Printing minutes and seconds
            for (int i = 2; i < 8; ++i) {
                System.out.print(timeString.charAt(i));
            }
        } else {
            System.out.print("\t\t\t\t\t\tTime: " + hh);

            // Printing minutes and seconds
            for (int i = 2; i < 8; ++i) {
                System.out.print(timeString.charAt(i));
            }
        }

        // After time is printed
        System.out.println(" " + amOrPmStr);
    }
    
    private static void createTables() throws Throwable {
        doctor = new Doctor();
        doctor.createDoctorTable();

        patient = new Patient();
        patient.createPatientTable();

        medicine = new Medicine();
        medicine.createMedicineTable();

        facility = new Facility();
        facility.createFacilityTable();

        lab = new Lab();
        lab.createLabTable();

        staff = new Staff();
        staff.createStaffTable();
    }
    
    private static void chooseEntities() throws Throwable {
        input = new Scanner(System.in);

        int choice, status = 1;
        
        while (status == 1) {
            mainMenu();

            choice = input.nextInt();
            switch (choice) {

                case 1: {
                    doctorOption();
                    break;
                }

                case 2: {
                    patientOption();
                    break;
                }

                case 3: {
                    medicineOption();
                    break;
                }

                case 4: {
                    labOption();
                    break;
                }

                case 5: {
                    facilityOption();
                    break;
                }

                case 6: {
                    staffOption();
                    break;
                }

            }

        }
        input.close();
    }
    
    private static void mainMenu() {
        System.out.println("\n MAIN MENU");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("1.Doctors 2. Patients 3.Medicines 4.Laboratories 5. Facilities 6. Staff ");
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    private static void doctorOption() throws Throwable {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **DOCTOR SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        int s1 = 1, c1 = 1;
        input = new Scanner(System.in);

        while (s1 == 1) {
            doctor = new Doctor();
            System.out.println("1. Add New Entry"
                    + "\n2. Existing Doctors List"
                    + "\n3. Update Doctor"
                    + "\n4. Delete Doctor");
            c1 = input.nextInt();
            switch (c1)
            {
                case 1: {
                    doctor.insertDoctor();
                    break;
                }
                case 2: {
                    doctor.getDoctor();
                    break;
                }
                case 3: {
                    doctor.chooseDoctorUpdate();
                    break;
                }
                case 4: {
                    doctor.deleteDoctor();
                    break;
                }
            }
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s1 = input.nextInt();
        }

    }

    private static void patientOption() throws Throwable {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **PATIENT SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        int c1, s2 = 1;
        input = new Scanner(System.in);

        while (s2 == 1) {
            patient = new Patient();
            System.out.println("1.Add New Entry"
                    + "\n2.Existing Patients List"
                    + "\n3.Update Patient");
            c1 = input.nextInt();
            switch (c1) {
                case 1: {
                    patient.insertPatient();
                    break;
                }

                case 2: {
                    patient.getPatient();
                    break;
                }
                
                case 3: {
                    patient.choosePatientUpdate();
                    break;
                }
            }
            System.out.println();
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s2 = input.nextInt();
        }
    }

    private static void medicineOption() throws Throwable {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **MEDICINE SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        int c1, s3 = 1;
        input = new Scanner(System.in);

        while (s3 == 1) {
            medicine = new Medicine();
            System.out.println("1. Add New Entry"
                    + "\n2. Existing Medicines List"
                    + "\n3. Update Medicine"
                    + "\n4. Delete Medicine");
            c1 = input.nextInt();

            switch (c1) {
                case 1: {
                    medicine.insertMedicine();
                    break;
                }
                case 2: {
                    medicine.getMedicine();
                    break;
                }
                case 3: {
                    medicine.chooseMedicineUpdate();
                    break;
                }
                case 4: {
                    medicine.deleteMedicine();
                    break;
                }
            }
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s3 = input.nextInt();
        }
    }

    private static void labOption() throws Throwable {
        int c1, s4 = 1;
        input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **LABORATORY SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        while (s4 == 1) {
            lab = new Lab();
            System.out.println("1. Add New Entry"
                    + "\n2. Existing Laboratories List"
                    + "\n3. Update Lab"
                    + "\n4. Delete Lab");
            c1 = input.nextInt();
            
            switch (c1) {
                case 1: {
                    lab.insertLab();
                    break;
                }
                case 2: {
                    lab.getLab();
                    break;
                }
                case 3: {
                    lab.chooseLabUpdate();
                    break;
                }
                case 4: {
                    lab.deleteLab();
                    break;
                }
            }

            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s4 = input.nextInt();
        }
    }

    private static void facilityOption() throws Throwable {
        int c1, s5 = 1;
        input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **HOSPITAL FACILITY SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        while (s5 == 1)

        {
            facility = new Facility();
            System.out.println("1. Add New Facility"
                    + "\n2. Existing Facilities List"
                    + "\n3. Update Facility"
                    + "\n4. Delete Facility");
            c1 = input.nextInt();

            switch (c1) {
                case 1: {
                    facility.insertFacility();
                    break;
                }
                case 2: {
                    facility.getFacility();
                    break;
                }
                case 3: {
                    facility.chooseFacilityUpdate();
                    break;
                }
                case 4: {
                    facility.deleteFacility();
                    break;
                }
            }
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s5 = input.nextInt();
        }

    }

    private static void staffOption() throws Throwable {
        String c2;
        int s6 = 1;
        input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **STAFF SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        while (s6 == 1)

        {
            final String a = "a", b = "b", c = "c";
            System.out.println("a. Nurses\nb. Workers\nc. Security");

            System.out.println("\nFor Main Menu, Press 0");

            if ((input.hasNextInt()) && (input.nextInt() != 1)) {
                break;
            }

            else if (input.hasNext()) {

                c2 = input.next();

                switch (c2)
                {
                    case a: {
                        nurseOption();
                        break;
                    }
                    case b: {
                        workerOption();
                        break;
                    }
                    case c: {
                        securityOption();
                        break;
                    }
                }
            }
        }
    }
    
    private static void nurseOption() throws Throwable {
        int sub1 = 1, c3;
        input = new Scanner(System.in);

        while (sub1 == 1) {
            Nurse nurse = new Nurse();
            System.out.println("1. Add New Entry"
                    + "\n2. Existing Nurses List"
                    + "\n3. Update Nurse"
                    + "\n4. Delete Nurse");

            c3 = input.nextInt();

            switch (c3) {

                case 1: {
                    nurse.insertNurse();
                    break;
                }
                case 2: {
                    nurse.getNurse();
                    break;
                }
                case 3: {
                    nurse.chooseNurseUpdate();
                    break;
                }
                case 4: {
                    nurse.deleteNurse();
                    break;
                }
            }
            System.out.println("\nPress 1 to go back or press 0 to return to Staff Menu");

            if ((input.hasNextInt()) && (input.nextInt() != 1)) {
                break;
            }

        }
        System.out.println("\nReturn to Staff Menu, Press 1");
        input.nextInt();
    }

    private static void workerOption() throws Throwable {
        int sub2 = 1, c3;
        input = new Scanner(System.in);

        while (sub2 == 1) {
            Worker worker = new Worker();
            System.out.println("1. Add New Entry"
                    + "\n2. Existing Worker List"
                    + "\n3. Update Worker"
                    + "\n4. Delete Worker");
            c3 = input.nextInt();

            switch (c3) {
                case 1: {
                    worker.insertWorker();
                    break;
                }
                case 2: {
                    worker.getWorker();
                    break;
                }
                case 3: {
                    worker.chooseWorkerUpdate();
                    break;
                }
                case 4: {
                    worker.deleteWorker();
                    break;
                }
            }
            System.out.println("\nPress 1 to go back or press 0 to return to Staff Menu");

            if ((input.hasNextInt()) && (input.nextInt() != 1)) {
                break;
            }

        }
        System.out.println("\nReturn to Staff Menu, Press 1");
        input.nextInt();
    }

    private static void securityOption() throws Throwable {

        int sub3 = 1, c3;
        input = new Scanner(System.in);

        while (sub3 == 1) {
            Security security = new Security();
            System.out.println("1. Add New Entry"
                    + "\n2. Existing Security List"
                    + "\n3. Update Security"
                    + "\n4. Delete Security");
            c3 = input.nextInt();

            switch (c3) {
                case 1: {
                    security.insertSecurity();
                    break;
                }
                case 2: {
                    security.getSecurity();
                    break;
                }
                case 3: {
                    security.chooseSecurityUpdate();
                    break;
                }
                case 4: {
                    security.deleteSecurity();
                    break;
                }
            }

            System.out.println("\nPress 1 to go back or press 0 to return to Staff Menu");

            if ((input.hasNextInt()) && (input.nextInt() != 1)) {
                break;
            }

        }
        System.out.println("\nReturn to Staff Menu, Press 1");
        input.nextInt();
    }
}
