# Hospital Management Sytem With Database

When I was taking SEIS 601 at the University of St. Thomas, I completed a version of this project. I was given a program that was already put together but the data for the entities that were part of the hospital system was transient. The task assigned was to change the program so that the data for each entity—Doctor, Facility, Lab, Medicine, Nurse, Patient, Security, and Worker—would live in a separate CSV file that could be read from and written to. The initial version of the project is located here: [SEIS-601 Hospital Management System](https://github.com/jkrovitz/SEIS-601-HospitalManagementSystemTheTurnedInVersion).

I have since enhanced the project, moving each entity that was once stored in a file to being stored in an individual table in a PostgreSQL database.

## Run the Program
1. Make sure you have Java 8 or newer installed on your machine. Download [JavaSE Development Kit 11.0.10](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
2. Install [PostgreSQL V 13.2](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads). Take note of the superuser's username, password, port, and database name.
3. Download the jar file [PostgreSQL JDBC 4.2 Driver, 42.2.19](https://jdbc.postgresql.org/download/postgresql-42.2.19.jar).
4. Clone this repository and open it in the IDE of your choice.
5. Add to the class path the jar file that was downloaded in step 3.
6. In the class called Database, update the database, port, user, and password based on the values noted in step 2. The program should now run successfully!