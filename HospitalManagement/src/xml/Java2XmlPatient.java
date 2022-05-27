package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


import hospital.pojos.Patient;

public class Java2XmlPatient {
private static EntityManager em;
private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

private static void printPatients() {
	Query q1 = em.createNativeQuery("SELECT * FROM patients", Patient.class);
	List<Patient> ps = (List<Patient>) q1.getResultList();
	for (Patient p : ps) {
		System.out.println(p);
	}
}

public static void main(String[] args) throws Exception {
	// Get the entity manager
	em = Persistence.createEntityManagerFactory("HospitalManagement-provider").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
			
	// Create the JAXBContext
	JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
	// Get the marshaller
	Marshaller marshaller = jaxbContext.createMarshaller();
	
	// Pretty formatting
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
	
	// Choose the patient to turn into an XML
	printPatients();//NO SÉ SI ES ASÍ
	System.out.print("Choose a patient to turn into an XML file:");
	int patient_id = Integer.parseInt(reader.readLine());
	Query q2 = em.createNativeQuery("SELECT * FROM patients WHERE id = ?", Patient.class);
	q2.setParameter(1, patient_id);
	Patient p = (Patient) q2.getSingleResult();
	
	// Use the Marshaller to marshal the Java object to a file
	File file = new File("./xmls/Patient File.xml");
	marshaller.marshal(p, file);
	// Printout
	marshaller.marshal(p, System.out);

}

}
