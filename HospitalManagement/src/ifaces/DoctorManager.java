package ifaces;

import java.util.List;

import hospital.pojos.Doctor;
import hospital.pojos.Patient;

public interface DoctorManager {
	
	//List all doctors
	public List<Doctor> listAllDoctors();
	//Add doctor
	public void addDoctor(Doctor d);
	//Update doctor
	public void updateDoctor(Doctor d);
	//List all the patients of a doctor
	public List<Patient> listMyPatients(int doctorId);
	//Assign a doctor to a patient
	public void assignDoctor(int patientId, int doctorId);
	//The doctor relates a symptom to a disease to a medicine
	public void assignSymptomDiseaseMedicine(int symptomId, int diseaseId, int medicineId);
	
}
