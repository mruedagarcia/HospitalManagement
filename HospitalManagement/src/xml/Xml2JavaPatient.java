package xml;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.eclipse.persistence.exceptions.JAXBException;

import hospital.pojos.Disease;
import hospital.pojos.Doctor;
import hospital.pojos.Medicine;
import hospital.pojos.Symptom;

public class Xml2JavaPatient {
	private static final String PERSISTENCE_PROVIDER = "HospitalManagement-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./xmls/External-Patient.xml");
		Patient patient = (Patient) unmarshaller.unmarshal(file);

		// Print the patient
		System.out.println("Patient:");
		System.out.println("Email: " + patient.getEmail());
		System.out.println("Date of birth: " + patient.getDob());
		System.out.println("Name: " + patient.getName());
		System.out.println("Nurse: " + patient.getNurse());
		List<Doctor> doctors = patient.getDoctors();
		for (Doctor doctor : doctors) {
			System.out.println("Doctor: " + doctor.getName());
		}
	    List<Disease> diseases = patient.getDiseases();
		for (Disease disease : diseases) {
			System.out.println("Disease: " + disease.getName());
		}
		List<Medicine> medicines = patient.getMedicines();
		for (Medicine med : medicines) {
			System.out.println("Medicine: " + med.getName());
		}
		List<Symptom> symptoms = patient.getSymptoms();
		for (Symptom s : symptoms) {
			System.out.println("Symptom: " + s.getName());
		}

		// Store the patient in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the patients are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		//TENEMOS QUE GUARDAR LOS PACIENTES SOLO O TODO?
		/*for (Employee employee : emps) {
			em.persist(employee);
		}
		*/
		
		// End transaction
		tx1.commit();
	}
}
