package jbdc;

import java.sql.PreparedStatement;

import hospital.pojos.Symptom;
import ifaces.SymptomManager;

public class JDBCSymptomManager implements SymptomManager{
private JDBCManager manager;
public JDBCSymptomManager(JDBCManager m) {
	this.manager=m;
}
	@Override
	public void addSymptom(Symptom s) {
		try {
			String sql = "INSERT INTO symptoms (name) VALUES(?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, s.getName());
			p.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
