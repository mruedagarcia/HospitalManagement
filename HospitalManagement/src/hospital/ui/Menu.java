package hospital.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import hospital.pojos.DateOfBirth;
import hospital.pojos.Patient;
import ifaces.PatientManager;
import jbdc.JDBCManager;
import jbdc.JDBCPatientManager;

public class Menu {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static PatientManager patientManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to our Hospital");
		JDBCManager jdbcManager = new JDBCManager();
		patientManager = new JDBCPatientManager(jdbcManager);
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
		DateOfBirth dob = Utilities.readDate("Date of birth:");
		Patient p = new Patient(name, email, status, phone, dob);
	}
}
