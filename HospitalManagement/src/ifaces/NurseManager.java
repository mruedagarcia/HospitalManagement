package ifaces;

import java.util.List;

import hospital.pojos.Medicine;
import hospital.pojos.Nurse;
import hospital.pojos.Patient;

public interface NurseManager {
	public void addNurse(Nurse n);

	// List all nurses
	public List<Nurse> listAllNurses();

	// Assign nurse to patient
	public void assignNurse(int patientId, int nurseId);

	// List nurse patients
	public List<Patient> listMyPatients(int nurseId);

	// Search nurse by a patient
	public Nurse searchNurseByPatient(int patientId);

	// Get nurse by id
	public Nurse getNurseById(int nurseId);

	// Update a nurse
	public void updateNurse(Nurse n);

	// Update patient status
	public void updatePatientStatus(Patient p);
	
	public Nurse getNurseByName(String nurseName);

}
