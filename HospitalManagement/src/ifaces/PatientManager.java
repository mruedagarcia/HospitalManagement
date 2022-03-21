package ifaces;

import java.util.List;

import hospital.pojos.Patient;

public interface PatientManager {

	//Adding patient
	public void addPatient(Patient p);
	//Search patients of a doctor
	public List<Patient> searchPatientByDoctor(int doctorId);
	//Get patient by id
	public Patient getPatientById(int patientId);
	//Removes a patient from database
	public void deletePatient(int patientId);
	//Assign a doctor to a patient
	public void assign(int patientId, int doctorId);
	//Unassign a doctor to a patient
	public void unassign(int patientId, int doctorId);
}
