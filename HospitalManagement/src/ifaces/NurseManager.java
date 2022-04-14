package ifaces;

import java.util.List;

import hospital.pojos.Nurse;
import hospital.pojos.Patient;

public interface NurseManager {
	public void addNurse(Nurse n);
	//List all nurses
	public List<Nurse> listAllNurses();
	//Assign nurse to patient
	public void assignNurse(int patientId, int nurseId);
	//List nurse patients
	public List<Patient> listMyPatients();
	//Search nurse by a patient
	public Patient searchNurseByPatient(int patientId);
	//Search patients of a doctor
	public Patient searchPatientByDoctor(int doctorId);
	//Get nurse by id
	public Patient getNurseById(int nurseId);
	//Update a nurse
	public void updateNurse(Nurse n);
	
}
