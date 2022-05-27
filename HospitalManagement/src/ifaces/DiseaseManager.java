package ifaces;

import java.util.List;

import hospital.pojos.Disease;

public interface DiseaseManager {
public void addDisease(Disease d);
public List<Disease> listAllDiseases();
public Disease getDiseaseByName(String diseaseName);
}
