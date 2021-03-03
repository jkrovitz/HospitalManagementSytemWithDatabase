import java.io.*;
import java.util.*;
import java.util.Calendar;

/**
 * HospitalMgmtSystem.java
 * @author Jeremy Krovitz
 * 
 * Driver class that handles navigation through the Hospital Management System software.
 * 
 * This code was adapted and modified from Hospital Management System Project in Java by Ghanendra Yadav
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

        int choice, status = 1;
        input = new Scanner(System.in);

        welcome();

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

    private static void welcome() {
        String months[] = {

                "Jan", "Feb", "Mar", "Apr",

                "May", "Jun", "Jul", "Aug",

                "Sep", "Oct", "Nov", "Dec" };

        Calendar calendar = Calendar.getInstance();

        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println(" *** Welcome to Hospital Management System Project in Java ***");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.print("Date: " + months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " "
                + calendar.get(Calendar.YEAR));

        System.out.println("\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE)
                + ":" + calendar.get(Calendar.SECOND));
    }

    private static void mainMenu() {
        System.out.println("\n MAIN MENU");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("1.Doctors 2. Patients 3.Medicines 4.Laboratories 5. Facilities 6. Staff ");
        System.out.println("-----------------------------------------------------------------------------------");
    }

    private static void doctorOption() throws Throwable {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **DOCTOR SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        int s1 = 1, c1 = 1;
        input = new Scanner(System.in);

        while (s1 == 1) {
            doctor = new Doctor();
            System.out.println("1.Add New Entry\n2.Existing Doctors List\n3.Delete Doctor\n4.Update Doctor");
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
                    doctor.deleteDoctor();
                    break;
                }
                case 4: {
                    doctor.chooseUpdate();
                    break;
                }
            }
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s1 = input.nextInt();
        }

    }

    private static void patientOption() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **PATIENT SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        int c1, s2 = 1;
        input = new Scanner(System.in);

        while (s2 == 1) {
            patient = new Patient();
            System.out.println("1.Add New Entry\n2.Existing Patients List");
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
            }
            System.out.println();
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s2 = input.nextInt();
        }
    }

    private static void medicineOption() throws IOException {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **MEDICINE SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        int c1, s3 = 1;
        input = new Scanner(System.in);

        while (s3 == 1) {
            medicine = new Medicine();
            System.out.println("1.Add New Entry\n2. Existing Medicines List");
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

            }
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s3 = input.nextInt();
        }
    }

    private static void labOption() throws IOException {
        int c1, s4 = 1;
        input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **LABORATORY SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        while (s4 == 1)

        {
            lab = new Lab();
            System.out.println("1.Add New Entry \n2.Existing Laboratories List");
            c1 = input.nextInt();

            switch (c1)

            {
                case 1: {
                    lab.insertLab();
                    break;
                }

                case 2:

                {
                    lab.getLab();
                    break;
                }
            }

            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s4 = input.nextInt();
        }
    }

    private static void facilityOption() throws IOException {
        int c1, s5 = 1;
        input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **HOSPITAL FACILITY SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        while (s5 == 1)

        {
            facility = new Facility();
            System.out.println("1.Add New Facility\n2.Existing Facilities List");
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
            }
            System.out.println("\nReturn to Back Press 1 and for Main Menu Press 0");
            s5 = input.nextInt();
        }

    }

    private static void staffOption() throws IOException {
        String c2;
        int s6 = 1;
        input = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" **STAFF SECTION**");
        System.out.println("--------------------------------------------------------------------------------");

        while (s6 == 1)

        {
            final String a = "a", b = "b", c = "c";
            System.out.println("a.Nurses\nb.Workers\nc.Security");

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

    private static void nurseOption() throws IOException {
        int sub1 = 1, c3;
        input = new Scanner(System.in);

        while (sub1 == 1) {
            Nurse nurse = new Nurse();
            System.out.println("1.Add New Entry \n2.Existing Nurses List");

            c3 = input.nextInt();

            switch (c3) {

                case 1: {

                    nurse.insertNurse();
                    break;
                }
                case 2: {
                    System.out.println(
                            "--------------------------------------------------------------------------------");
                    System.out.println("id \t Name \t Gender \t Salary");
                    System.out.println(
                            "--------------------------------------------------------------------------------");

                    nurse.getNurse();
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

    private static void workerOption() throws IOException {
        int sub2 = 1, c3;
        input = new Scanner(System.in);

        while (sub2 == 1) {
            Worker worker = new Worker();
            System.out.println("1.Add New Entry \n2.Existing Worker List");
            c3 = input.nextInt();

            switch (c3) {
                case 1: {

                    worker.insertWorker();
                    break;
                }
                case 2: {
                    System.out.println(
                            "--------------------------------------------------------------------------------");
                    System.out.println("id \t Name \t Gender \t Salary");
                    System.out.println(
                            "--------------------------------------------------------------------------------");

                    worker.getWorker();
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

    private static void securityOption() throws IOException {

        int sub3 = 1, c3;
        input = new Scanner(System.in);

        while (sub3 == 1) {
            Security security = new Security();
            System.out.println("1.Add New Entry \n2.Existing Security List");
            c3 = input.nextInt();

            switch (c3) {
                case 1: {

                    security.insertSecurity();
                    break;
                }
                case 2: {
                    System.out.println(
                            "--------------------------------------------------------------------------------");
                    System.out.println("id \t Name \t Gender \t Salary");
                    System.out.println(
                            "--------------------------------------------------------------------------------");

                    security.getSecurity();
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
