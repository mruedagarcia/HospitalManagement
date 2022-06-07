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
import hospital.pojos.Doctors;
import hospital.pojos.Medicine;
import hospital.pojos.Nurse;
import hospital.pojos.Nurses;
import hospital.pojos.Patient;
import hospital.pojos.Patients;
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
import jdbc.JDBCDiseaseManager;
import jdbc.JDBCDoctorManager;
import jdbc.JDBCManager;
import jdbc.JDBCMedicineManager;
import jdbc.JDBCNurseManager;
import jdbc.JDBCPatientManager;
import jdbc.JDBCSymptomManager;
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
		userManager = new JPAUserManager(); // initialize JPA
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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Patient p = new Patient(name, email, severe, phone, Date.valueOf(dob));
		patientManager.addPatient(p);

	}

	public static void createDoctor() {
		System.out.println("Type your data:");
		String name = Utilities.readString("Name: ");
		String specialty = Utilities.readString("Specialty: ");
		String email = Utilities.readString("Email: ");
		String passwd = Utilities.readString("Password: ");

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwd.getBytes());
			byte[] digest = md.digest();
			User u = new User(email, digest);
			Role role = userManager.getRole("doctor");
			// Remember to work with both sides
			u.setRole(role);
			role.addUser(u);
			// Insert the user using userManager
			userManager.createUser(u);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Doctor d = new Doctor(name, specialty, email);
		doctorManager.addDoctor(d);
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
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Nurse n = new Nurse(name, email);
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
				System.out.println("5.Export patients to a Xml file");
				System.out.println("6.Import patients from a Xml file");
				System.out.println("7.Save as Html");
				System.out.println("8.Delete account");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					String name = Utilities.readString("Introduce a new name: ");
					String email = Utilities.readString("Introduce a new email: ");
					boolean severe = Utilities.readBoolean("Introduce a new severe (yes/no): ");
					Integer phone = Utilities.readInt("Introduce a new phone: ");
					LocalDate dob = Utilities.readDate();
					patientManager.updatePatient(pId, name, email, severe, phone, Date.valueOf(dob));
					break;
				}
				case 2: {
					List<Doctor> doctors = new ArrayList<Doctor>();
					doctors = patientManager.listMyDoctors(pId);
					System.out.println(doctors);
					break;
				}
				case 3: {
					List<Symptom> symptoms = new ArrayList<Symptom>();
					symptoms = patientManager.listMySymptoms(pId);
					System.out.println(symptoms);
					break;
				}
				case 4: {
					System.out.println(patientManager.listMyMedicines(pId));
					System.out.println(patientManager.listMyDiseases(pId));
					break;
				}
				case 5: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					List<Patient> allPatients = patientManager.listAllPatients();// we list them
					String nameFileXml = Utilities
							.readString("Introduce the name of the xml file(./src/xml/nameFile.xml): ");
					File file = new File(nameFileXml);
					Patients patients = new Patients(allPatients);
					marshaller.marshal(patients, file);
					marshaller.marshal(patients, System.out);
					break;
				}
				case 6: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Patients.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					String nameFile = Utilities.readString("Introduce the name of the file(./src/xml/nameFile.xml): ");
					File file = new File(nameFile);// we read the file with the name
					Patients patients = (Patients) unmarshaller.unmarshal(file);// we recuperate the information of the
																				// file readed
					List<Patient> ps = patients.getPatients();
					for (Patient patient : ps) {
						patientManager.addPatient(patient);
					}
					break;
				}
				case 7: {
					String XmlFile = Utilities.readString("Introduce the name of the Xml File: ");
					String XsltFile = Utilities.readString("Introduce the name of the Xslt File: ");
					String HtmlFile = Utilities
							.readString("Introduce the name of the Htlm File that you want to create (.html): ");
					Xml2HtmlPatient.simpleTransform(XmlFile, XsltFile, HtmlFile);
					break;
				}
				case 8: {
					patientManager.deletePatient(pId);
					System.out.println("Account deleted");
					System.exit(0);
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
				System.out.println("5.Export doctors to a Xml file");
				System.out.println("6.Import doctors from a Xml file");
				System.out.println("7.Save as Html");
				System.out.println("8.Delete account");
				System.out.println("0.Exit");

				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					String name = Utilities.readString("Introduce your new name: ");
					String specialty = Utilities.readString("Introduce your new specialty: ");
					String email = Utilities.readString("Introduce your new email: ");
					doctorManager.updateDoctor(dId, name, specialty, email);
					break;
				}
				case 2: {
					System.out.println(doctorManager.listMyPatients(dId));
					break;
				}
				case 3: {
					System.out.println(patientManager.listAllPatients());
					break;
				}
				case 4: { // assigning doctors w/ patients & assigning symptoms, medicines and diseases
					List<Disease> diseases = new ArrayList<Disease>();
					List<Symptom> symptoms = new ArrayList<Symptom>();
					List<Medicine> medicines = new ArrayList<Medicine>();
					List<Patient> patients = new ArrayList<Patient>();
					patients = patientManager.listAllPatients();
					System.out.println(patients);
					int id = Utilities.readInt("Introduce the id of the patient to diagnose:");
					Patient p = patientManager.getPatientById(id);
					doctorManager.assignDoctor(p, dId);
					p.addDoctor(d);
					int id_;
					// Doctor doctor = doctorManager.getDoctorById(dId);
					// doctor.addPatient(p);
					// p.addDoctor(doctor);
					do {
						symptoms = symptomManager.listAllSymptoms();
						System.out.println(symptoms);
						id_ = Utilities.readInt("Introduce the id of a symptom (write 0 when you have finished): ");
						Symptom s = symptomManager.getSymptomById(id_);
						System.out.println(s);
						p.addSymptom(s);
						doctorManager.assignSymptom(p, s);
					} while (id_ != 0);
					do {
						diseases = diseaseManager.listAllDiseases();
						System.out.println(diseases);
						id_ = Utilities.readInt("Introduce the id of a disease (write 0 when you have finished): ");
						Disease di = diseaseManager.getDiseaseById(id_);
						doctorManager.assignDisease(p, di);
						p.addDisease(di);
					} while (id_ != 0);
					do {
						medicines = medicineManager.listAllMedicines();
						System.out.println(medicines);
						id_ = Utilities.readInt("Introduce the id of a medicine (write 0 when you have finished): ");
						Medicine med = medicineManager.getMedicineById(id_);
						doctorManager.assignMedicine(p, med);
						p.addMedicine(med);
					} while (id_ != 0);

					break;
				}
				case 5: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Doctors.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					List<Doctor> allDoctors = doctorManager.listAllDoctors();// we list them
					String nameFileXml = Utilities
							.readString("Introduce the name of the xml file(./src/xml/nameFile.xml): ");
					File file = new File(nameFileXml);
					Doctors doctors = new Doctors(allDoctors);// we put allDoctor in our pojo Doctors
					marshaller.marshal(doctors, file);// marshall of the doctor to the file
					marshaller.marshal(doctors, System.out);
					break;
				}
				case 6: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Doctors.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					String nameFile = Utilities.readString("Introduce the name of the file(./src/xml/nameFile.xml): ");
					File file = new File(nameFile);// we read the file with the name
					Doctors doctors = (Doctors) unmarshaller.unmarshal(file);// we recuperate the information of the
																				// file readed
					List<Doctor> ds = doctors.getDoctors();
					for (Doctor doctor : ds) {
						doctorManager.addDoctor(doctor);
					}
					break;
				}
				case 7: {
					String XmlFile = Utilities.readString("Introduce the name of the Xml File: ");
					String XsltFile = Utilities.readString("Introduce the name of the Xslt File: ");
					String HtmlFile = Utilities
							.readString("Introduce the name of the Htlm File that you want to create (.html): ");
					Xml2HtmlPatient.simpleTransform(XmlFile, XsltFile, HtmlFile);
					break;
				}
				case 8: {
					doctorManager.deleteDoctor(dId);
					System.out.println("Account deleted");
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
				List<Patient> patients = new ArrayList<Patient>();
				Nurse n = nurseManager.getNurseById(nId);
				System.out.println("1.Change my data:");
				System.out.println("2.See my patients:");
				System.out.println("3.See all patients:");
				System.out.println("4.Update patient status");
				System.out.println("5.Export nurses to a Xml file");
				System.out.println("6.Import nurses from a Xml file");
				System.out.println("7.Save as Html");
				System.out.println("8.Delete account");
				System.out.println("0.Exit");
				int choice = Utilities.readInt("----->Choose an option:<------\n");
				switch (choice) {
				case 1: {
					String name = Utilities.readString("Introduce a new name: ");
					String email = Utilities.readString("Introduce a new email: ");
					nurseManager.updateNurse(nId, name, email);
					break;
				}
				case 2: {// !!!!!!!!!!!!!!
					System.out.println(nurseManager.listMyPatients(nId));
					break;
				}
				case 3: {
					System.out.println(patientManager.listAllPatients());
					break;
				}
				case 4: {
					patients = patientManager.listAllPatients();
					System.out.println(patients);
					int id = Utilities.readInt("Introduce the id of the patient to treat:");
					nurseManager.assignNurse(id, nId);
					Boolean severe = Utilities.readBoolean("Introduce the severity:(yes/no)");
					nurseManager.updatePatientStatus(id, severe);
					break;
				}
				case 5: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Nurses.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					List<Nurse> allNurses = nurseManager.listAllNurses();// we list them
					String nameFileXml = Utilities
							.readString("Introduce the name of the xml file(./src/xml/nameFile.xml): ");
					File file = new File(nameFileXml);
					Nurses nurses = new Nurses(allNurses);
					marshaller.marshal(nurses, file);
					marshaller.marshal(nurses, System.out);
					break;
				}
				case 6: {
					JAXBContext jaxbContext = JAXBContext.newInstance(Nurses.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					String nameFile = Utilities.readString("Introduce the name of the file(./src/xml/nameFile.xml): ");
					File file = new File(nameFile);// we read the file with the name
					Nurses nurses = (Nurses) unmarshaller.unmarshal(file);// we recuperate the information of the
																			// file readed
					List<Nurse> ns = nurses.getNurses();
					for (Nurse nurse : ns) {
						nurseManager.addNurse(nurse);
					}
					break;
				}
				case 7: {
					String XmlFile = Utilities.readString("Introduce the name of the Xml File: ");
					String XsltFile = Utilities.readString("Introduce the name of the Xslt File: ");
					String HtmlFile = Utilities
							.readString("Introduce the name of the Htlm File that you want to create (.html): ");
					Xml2HtmlPatient.simpleTransform(XmlFile, XsltFile, HtmlFile);
					break;
				}
				case 8: {
					nurseManager.deleteNurse(nId);
					System.out.println("Account deleted");
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

	public static void loginPatient() throws Exception {
		List<Patient> patients = patientManager.listAllPatients();
		String email = Utilities.readString("Introduce an email:");
		String password = Utilities.readString("Introduce a password:");
		User u = userManager.checkPassword(email, password);
		while (true) {
			if (u != null && u.getRole().getName().equals("patient")) {
				System.out.println("login successful");
				for (Patient p : patients) {
					if (u.getEmail().equals(p.getEmail())) {
						patientMenu(p.getId());
					}
				}
				break;
			} else {
				System.out.println("Email or password wrong");
				break;

			}
		}
	}

	public static void loginDoctor() throws Exception {
		List<Doctor> doctors = doctorManager.listAllDoctors();
		String email = Utilities.readString("Introduce an email:");
		String password = Utilities.readString("Introduce a password:");
		User u = userManager.checkPassword(email, password);
		while (true) {
			if (u != null && u.getRole().getName().equals("doctor")) {
				System.out.println("login successful");
				for (Doctor d : doctors) {
					if (u.getEmail().equals(d.getEmail())) {
						doctorMenu(d.getId());
					}
				}
				// pick the doctor w the same email as user and then in doctorMenu(d.getId())

				break;
			} else {
				System.out.println("Email or password wrong");
				break;
			}
		}

	}

	public static void loginNurse() throws Exception {
		List<Nurse> nurses = nurseManager.listAllNurses();
		String email = Utilities.readString("Introduce an email:");
		String password = Utilities.readString("Introduce a password:");
		User u = userManager.checkPassword(email, password);
		while (true) {
			if (u != null && u.getRole().getName().equals("nurse")) {
				System.out.println("login successful");
				for (Nurse n : nurses) {
					if (u.getEmail().equals(n.getEmail())) {
						nurseMenu(n.getId());
					}
				}
				break;
			} else {
				System.out.println("Email or password wrong");
				break;
			}
		}
	}
}
