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
	public void updateNurse(Integer nId, String name, String email);

	// Update patient status
	public void updatePatientStatus(int pId, Boolean severe);

	public Nurse getNurseByName(String nurseName);

	public void deleteNurse(int nurseId);

}
