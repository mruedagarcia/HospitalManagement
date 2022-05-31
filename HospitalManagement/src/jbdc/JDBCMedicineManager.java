package jbdc;

import java.sql.PreparedStatement;

import hospital.pojos.Medicine;
import ifaces.MedicineManager;

public class JDBCMedicineManager implements MedicineManager{
private JDBCManager manager;
public JDBCMedicineManager(JDBCManager m) {
	this.manager=m;
}

@Override
public void addMedicine(Medicine m) {
	try {
		String sql = "INSERT INTO medicines (name) VALUES(?)";
		PreparedStatement p = manager.getConnection().prepareStatement(sql);
		p.setString(1, m.getName());
		p.executeUpdate();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
}
