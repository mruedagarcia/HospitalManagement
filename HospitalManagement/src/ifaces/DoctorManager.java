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
	//List all patients
	public List<Patient> listAllPatients();
	//The doctor relates a symptom to a disease to a medicine
	public void assignSymptomDiseaseMedicine(int symptomId, int diseaseId, int medicineId);
	//Listar sintomas en symptom luego listar diseases relacionados con los symptoms luego listar medicines relacionados con esos symptoms luego en la tabla del verbo asignar symptom a disease
	//y disease a medicine
	
	
}
