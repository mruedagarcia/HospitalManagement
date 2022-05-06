package ifaces;

import java.util.List;

import hospital.pojos.Disease;
import hospital.pojos.Doctor;
import hospital.pojos.Medicine;
import hospital.pojos.Patient;
import hospital.pojos.Symptom;

public interface PatientManager {

	// Adding patient
	public void addPatient(Patient p);

	// List all patients
	public List<Patient> listAllPatients();

	// Shows a list of all the doctors of a patient
	public List<Doctor> listMyDoctors(int patientId);

	// Shows a list of all the symptoms of a patient
	public List<Symptom> listMySymptoms(int patientId);

	// Shows a list of all diseases of a patient
	public List<Disease> listMyDiseases(int patientId);

	// Shows a list of all medicines of a patient
	public List<Medicine> listMyMedicines(int patientId);

	// Search patients of nurse
	public Patient searchPatientByNurse(int nurseId);

	// Search patients of a doctor
	public Patient searchPatientByDoctor(int doctorId);

	// Get patient by id
	public Patient getPatientById(int patientId);

	// Removes a patient from database
	public void deletePatient(int patientId);

	// Update a patient
	public void updatePatient(Patient p);
}
