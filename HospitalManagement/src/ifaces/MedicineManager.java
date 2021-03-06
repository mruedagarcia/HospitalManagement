package ifaces;

import java.util.List;

import hospital.pojos.Disease;
import hospital.pojos.Medicine;

public interface MedicineManager {
//public void addMedicine(Medicine m);
	public List<Medicine> listAllMedicines();

	public Medicine getMedicineByName(String medicineName);
	public Medicine getMedicineById(int id);
	
}
