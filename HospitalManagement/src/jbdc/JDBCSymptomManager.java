package jbdc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Patient;
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
	
	@Override
	public List<Symptom> listAllSymptoms() {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM symptoms";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Symptom s = new Symptom(id, name);
				symptoms.add(s);
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return symptoms;

	}
	
	@Override
	public Symptom getSymptomByName(String symptomName) {
		Symptom s = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM symptoms WHERE name=" + symptomName;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				s = new Symptom(name);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

}
