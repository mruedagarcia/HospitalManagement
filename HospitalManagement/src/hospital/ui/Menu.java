package hospital.ui;

import java.sql.Date;
import java.time.LocalDate;

import hospital.pojos.Doctor;
import hospital.pojos.Nurse;
import hospital.pojos.Patient;
import jbdc.JDBCDoctorManager;
import jbdc.JDBCManager;
import jbdc.JDBCNurseManager;
import jbdc.JDBCPatientManager;

public class Menu {

	private static JDBCPatientManager patientManager;
	private static JDBCDoctorManager doctorManager;
	private static JDBCNurseManager nurseManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to our Hospital");
		JDBCManager jdbcManager = new JDBCManager();
		patientManager = new JDBCPatientManager(jdbcManager);
		doctorManager = new JDBCDoctorManager(jdbcManager);
		nurseManager = new JDBCNurseManager(jdbcManager);
		try {
			do {

				System.out.println("1.Login as a patient:");
				System.out.println("2.Register as a new patient:");
				System.out.println("3.Login as a doctor:");
				System.out.println("4.Register as a new doctor:");
				System.out.println("5.Login as a nurse:");
				System.out.println("6.Register as a new nurse:");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					// loginPatient();
					break;
				}
				case 2: {
					createPatient();
					break;
				}
				case 3: {
					// loginDoctor();
					break;
				}
				case 4: {
					createDoctor();
					break;
				}
				case 5: {
					// loginNurse();
					break;
				}
				case 6: {
					createNurse();
					break;
				}
				case 0: {
					jdbcManager.disconnect();
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

	public static void choosePatient() throws Exception {
		System.out.println(patientManager.listAllPatients());
		Integer patientId = Utilities.readInt("Choose a patient, type its id:");
		patientMenu(patientId);
	}

	public static void chooseDoctor() throws Exception {
		System.out.println(doctorManager.listAllDoctors());
		Integer doctorId = Utilities.readInt("Choose a doctor, type its id:");
		doctorMenu(doctorId);
	}

	public static void chooseNurse() throws Exception {
		System.out.println(nurseManager.listAllNurses());
		Integer nurseId = Utilities.readInt("Choose a nurse, type its id:");
		patientMenu(nurseId);
	}

	public static void createPatient() throws Exception {
		System.out.println("Type your data:");
		String name = Utilities.readString("Name: ");
		String email = Utilities.readString("Email: ");
		boolean severe = Utilities.readBoolean("Severe (yes/no): ");
		Integer phone = Utilities.readInt("Phone: ");
		LocalDate dob = Utilities.readDate();
		Patient p = new Patient(name, email, severe, phone, Date.valueOf(dob));
		patientManager.addPatient(p);
		// TODO go back
	}

	public static void createDoctor() {
		System.out.println("Type your data:");
		String name = Utilities.readString("Name: ");
		String specialty = Utilities.readString("Specialty: ");
		Doctor d = new Doctor(name, specialty);
		doctorManager.addDoctor(d);
	}

	public static void createNurse() {
		System.out.println("Type tour data:");
		String name = Utilities.readString("Name: ");
		Nurse n = new Nurse(name);
		nurseManager.addNurse(n);
	}

	public static void patientMenu(Integer pId) throws Exception {
		try {
			do {

				System.out.println("1.Change my data:");
				System.out.println("2.See my doctors:");
				System.out.println("3.See my symptoms:");
				System.out.println("4.See my medical history:");
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
				case 0: {
					System.exit(0);
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

	public static void doctorMenu(Integer dId) throws Exception {
		try {
			do {

				System.out.println("1.Change my data:");
				System.out.println("2.See my patients:");
				System.out.println("3.See all patients:");
				System.out.println("4.Save treatment:");
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
				case 0: {
					System.exit(0);
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

	public static void nurseMenu(Integer nId) throws Exception {
		try {
			do {

				System.out.println("1.Change my data:");
				System.out.println("2.See my patients:");
				System.out.println("3.See all patients:");
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
				case 0: {
					System.exit(0);
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
}
