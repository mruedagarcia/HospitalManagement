package hospital.ui;

import java.sql.Date;
import java.time.LocalDate;
import hospital.pojos.Patient;
import jbdc.JDBCManager;
import jbdc.JDBCPatientManager;


public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to our Hospital");
		JDBCManager jdbcManager = new JDBCManager();
		new JDBCPatientManager(jdbcManager);
		try {
			do {

				System.out.println("1.Create patient:");
				System.out.println("2.Choose patient:");
				System.out.println("3.Create doctor:");
				System.out.println("4.Choose doctor:");
				System.out.println("5.Create nurse:");
				System.out.println("6.Choose nurse:");
				System.out.println("7.Discharge a patient:");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					createPatient();
					System.out.println("Patient registered");
					break;
				}
				case 2: {

					break;
				}
				case 3: {

					break;
				}
				case 4: {

					break;
				}
				case 5: {

					break;
				}
				case 6: {
					break;
				}
				case 7: {
					break;
				}
				case 0: {
					System.exit(0);// close the menu
				}
				default: {

					break;// close the loop
				}
				}
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createPatient()throws Exception{
		System.out.println("Type your data:");
		String name = Utilities.readString("Name: ");
		String email = Utilities.readString("Email: ");
		String status = Utilities.readString("Status: ");
		Integer phone = Utilities.readInt("Phone: ");
		LocalDate dob = Utilities.readDate();
		Patient p = new Patient(name, email, status, phone, Date.valueOf(dob));
		
	}
}
