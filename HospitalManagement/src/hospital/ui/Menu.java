package hospital.ui;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import hospital.pojos.Disease;
import hospital.pojos.Doctor;
import hospital.pojos.Medicine;
import hospital.pojos.Nurse;
import hospital.pojos.Patient;
import hospital.pojos.Role;
import hospital.pojos.User;
import hospital.pojos.Symptom;
import ifaces.DiseaseManager;
import ifaces.DoctorManager;
import ifaces.MedicineManager;
import ifaces.NurseManager;
import ifaces.PatientManager;
import ifaces.SymptomManager;
import ifaces.UserManager;
import jbdc.JDBCDiseaseManager;
import jbdc.JDBCDoctorManager;
import jbdc.JDBCManager;
import jbdc.JDBCMedicineManager;
import jbdc.JDBCNurseManager;
import jbdc.JDBCPatientManager;
import jbdc.JDBCSymptomManager;
import jpa.JPAUserManager;
import xml.Xml2HtmlPatient;

public class Menu {

	private static PatientManager patientManager;
	private static DoctorManager doctorManager;
	private static NurseManager nurseManager;
	private static SymptomManager symptomManager;
	private static DiseaseManager diseaseManager;
	private static MedicineManager medicineManager;
	private static UserManager userManager;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to our Hospital");
		JDBCManager jdbcManager = new JDBCManager();
		patientManager = new JDBCPatientManager(jdbcManager);
		doctorManager = new JDBCDoctorManager(jdbcManager);
		nurseManager = new JDBCNurseManager(jdbcManager);
		symptomManager = new JDBCSymptomManager(jdbcManager);
		diseaseManager = new JDBCDiseaseManager(jdbcManager);
		medicineManager = new JDBCMedicineManager(jdbcManager);
		userManager = new JPAUserManager(); //initialize JPA
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
					loginPatient();
					break;
				}
				case 2: {
					createPatient();
					break;
				}
				case 3: {
					loginDoctor();
					break;
				}
				case 4: {
					createDoctor();
					break;
				}
				case 5: {
					loginNurse();
					break;
				}
				case 6: {
					createNurse();
					break;
				}
				case 0: {
					System.out.println("~~~~~~Our department is closed~~~~~~");
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
		String passwd = Utilities.readString("Password: ");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			User u = new User(email, digest);
			Role role = userManager.getRole("patient");
			// Remember to work with both sides
			u.setRole(role);
			role.addUser(u);
			// Insert the user using userManager
			userManager.createUser(u);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Patient p = new Patient(name, email, severe, phone, Date.valueOf(dob));
		patientManager.addPatient(p);
		
	}

	public static void createDoctor() {
		System.out.println("Type your data:");
		String name = Utilities.readString("Name: ");
		String specialty = Utilities.readString("Specialty: ");
		
		
	}

	public static void createNurse() {
		System.out.println("Type tour data:");
		String name = Utilities.readString("Name: ");
		String email = Utilities.readString("Email: ");
		String passwd = Utilities.readString("Password: ");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			User u = new User(email, digest);
			Role role = userManager.getRole("nurse");
			// Remember to work with both sides
			u.setRole(role);
			role.addUser(u);
			// Insert the user using userManager
			userManager.createUser(u);
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Nurse n = new Nurse(name);
		nurseManager.addNurse(n);
	}

	public static void patientMenu(Integer pId) throws Exception {
		try {
			do {

				Patient p = patientManager.getPatientById(pId);
				System.out.println("1.Change my data:");
				System.out.println("2.See my doctors:");
				System.out.println("3.See my symptoms:");
				System.out.println("4.See my medical history:");
				System.out.println("5.Export patient to a Xml file");
				System.out.println("6.Import patient from a Xml file");
				System.out.println("7.Save as Html");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					patientManager.updatePatient(p);
					break;
				}
				case 2: {
					patientManager.listMyDoctors(pId);
					break;
				}
				case 3: {
					patientManager.listMySymptoms(pId);
					break;
				}
				case 4: {
					patientManager.listMyMedicines(pId);
					patientManager.listMyDiseases(pId);
					break;
				}
				case 5:{
					JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
					String nameFile = Utilities.readString("Introduce the name of the file(.xml): ");
					File file = new File(nameFile);
					marshaller.marshal(p, file);
					marshaller.marshal(p, System.out);
				}
				case 6:{
					JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					String nameFile = Utilities.readString("Introduce the name of the file(.xml): ");//TENGO QUE COMPROBAR QUE SEA UN FILE DEL QUE SE HAYA HECHO MARSHALL
					File file = new File(nameFile);
					Patient patient = (Patient) unmarshaller.unmarshal(file);
                    patientManager.addPatient(patient);
				}
				case 7:{
					String XmlFile = Utilities.readString("Introduce the name of the Xml File: ");
					String XsltFile = Utilities.readString("Introduce the name of the Xslt File: ");
					String HtmlFile = Utilities.readString("Introduce the name of the Htlm File: ");
					Xml2HtmlPatient.simpleTransform(XmlFile, XsltFile, HtmlFile);
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
				Doctor d = doctorManager.getDoctorById(dId);
				System.out.println("1.Change my data:");
				System.out.println("2.See my patients:");
				System.out.println("3.See all patients:");
				System.out.println("4.Diagnosis:");
				System.out.println("0.Exit");
				
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					doctorManager.updateDoctor(d);
					break;
				}
				case 2: {
					doctorManager.listMyPatients(dId);
					break;
				}
				case 3: {
					patientManager.listAllPatients();
					break;
				}
				case 4: {
					List<Disease> diseases = new ArrayList<Disease>();
					List<Symptom> symptoms = new ArrayList<Symptom>();
					List<Medicine> medicines = new ArrayList<Medicine>();
					List<Patient> patients = new ArrayList<Patient>();
					patients = patientManager.listAllPatients();
					System.out.println(patients);
					String name = Utilities.readString("Introduce the name of the patient you want to diagnose: ");
					Patient p = patientManager.getPatientByName(name);
					//doctorManager.assignDoctor(p.getId(), dId);
					do {
						symptoms = symptomManager.listAllSymptoms();
						System.out.println(symptoms);
						name = Utilities.readString("Introduce the name of a symptom (write exit when you finished): ");
						Symptom s = symptomManager.getSymptomByName(name);
						p.addSymptom(s);
					}while(!(name.equals("exit")));
					do {
						diseases = diseaseManager.listAllDiseases();
						System.out.println(diseases);
						name = Utilities.readString("Introduce the name of a disease (write exit when you have finished): ");
						Disease di = diseaseManager.getDiseaseByName(name);
						p.addDisease(di);
					}while(!(name.equals("exit")));
					do {
						medicines = medicineManager.listAllMedicines();
						System.out.println(medicines);
						name = Utilities.readString("Introduce the name of a medicine (write exit when you have finished): ");
						Disease di = diseaseManager.getDiseaseByName(name);
						p.addDisease(di);
					}while(!(name.equals("exit")));
					
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
				List<Patient>patients = new ArrayList<Patient>();
				Nurse n = nurseManager.getNurseById(nId);
				System.out.println("1.Change my data:");
				System.out.println("2.See my patients:");
				System.out.println("3.See all patients:");
				System.out.println("4.Update patient status");
				System.out.println("5.Export nurse to a Xml file");
				System.out.println("6.Import nurse from a Xml file");
				System.out.println("7.Save as Html");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					nurseManager.updateNurse(n);
					break;
				}
				case 2: {
					nurseManager.listMyPatients(nId);
					break;
				}
				case 3: {
					patientManager.listAllPatients();
					break;
				}
				case 4:{
					patients = patientManager.listAllPatients();
					System.out.println(patients);
					String name = Utilities.readString("Introduce the name of the patient to treat:" );
					Patient p = patientManager.getPatientByName(name);
					nurseManager.updatePatientStatus(p);
				}
				case 5: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
					String nameFile = Utilities.readString("Introduce the name of the file(.xml): ");
					File file = new File(nameFile);
					marshaller.marshal(n, file);
					marshaller.marshal(n, System.out);
				}
				case 6: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Nurse.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					String nameFile = Utilities.readString("Introduce the name of the file(.xml): ");
					File file = new File(nameFile);
					Nurse nurse = (Nurse) unmarshaller.unmarshal(file);
                    nurseManager.addNurse(nurse);
				}
				case 7:{
					String XmlFile = Utilities.readString("Introduce the name of the Xml File: ");
					String XsltFile = Utilities.readString("Introduce the name of the Xslt File: ");
					String HtmlFile = Utilities.readString("Introduce the name of the Htlm File: ");
					Xml2HtmlPatient.simpleTransform(XmlFile, XsltFile, HtmlFile);
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

	public static void loginPatient() throws Exception {
		String email = Utilities.readString("Introduce an email:");
		String password = Utilities.readString("Introduce a password:");
		User u = userManager.checkPassword(email, password);
		while (true) {
			if (u != null && u.getRole().getName().equals("patient")) {
				System.out.println("login successful");
				patientMenu(u.getId());
				break;
			}
		}
	}

	public static void loginDoctor() throws Exception {
		String email = Utilities.readString("Introduce an email:");
		String password = Utilities.readString("Introduce a password:");
		User u = userManager.checkPassword(email, password);
		while (true) {
			if (u != null && u.getRole().getName().equals("doctor")) {
				System.out.println("login successful");
				doctorMenu(u.getId());
				break;
			}else {
				System.out.println("Email or password wrong");
			}
		}
	}

	public static void loginNurse() throws Exception {
		String email = Utilities.readString("Introduce an email:");
		String password = Utilities.readString("Introduce a password:");
		User u = userManager.checkPassword(email, password);
		while (true) {
			if (u != null && u.getRole().getName().equals("nurse")) {
				System.out.println("login successful");
				nurseMenu(u.getId());
				break;
			}
		}
	}
}
