package ifaces;

import java.util.List;

import hospital.pojos.Medicine;
import hospital.pojos.Symptom;

public interface MedicineManager {
public void addMedicine(Medicine m);
public Medicine getMedicineByName(String medicineName);
public List<Medicine> listAllMedicines();
}
