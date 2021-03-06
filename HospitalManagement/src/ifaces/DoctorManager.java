package ifaces;

import java.util.List;

import hospital.pojos.Disease;
import hospital.pojos.Doctor;
import hospital.pojos.Medicine;
import hospital.pojos.Patient;
import hospital.pojos.Symptom;

public interface DoctorManager {

	// List all doctors
	public List<Doctor> listAllDoctors();

	// Add doctor
	public void addDoctor(Doctor d);

	// Update doctor
	public void updateDoctor(Integer dId, String name, String specialty, String email);

	// Get a doctor by id
	public Doctor getDoctorById(int dId);

	// List all the patients of a doctor
	public List<Patient> listMyPatients(int doctorId);

	// Assign a doctor to a patient
	public void assignDoctor(Patient pa, int doctorId);

	// The doctor relates a symptom to a disease to a medicine
	public void assignSymptomDisease(int symptomId, int diseaseId);

	public void assignDiseaseMedicine(int diseaseId, int medicineId);

	// search a disease by a symptom
	public Disease searchDiseaseBySymptoms(List<Symptom> symptoms);

	// search a medicine by a disease
	public List<Medicine> searchMedicineByDisease(Disease d);

	public List<Doctor> listAllDoctor();

	public Doctor getDoctorByName(String doctorName);

	public void assignSymptom(Patient p, Symptom s);

	public void assignDisease(Patient p, Disease d);

	public void deleteDoctor(int doctorId);

	public void assignMedicine(Patient p, Medicine m);
}
