package ifaces;

import java.util.List;

import hospital.pojos.Patient;
import hospital.pojos.Symptom;

public interface SymptomManager {
	public void addSymptom(Symptom s);
	public List<Symptom>listAllSymptoms();
	public Symptom getSymptomByName(String symptomName);

}
