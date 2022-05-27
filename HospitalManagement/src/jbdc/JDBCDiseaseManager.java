package jbdc;

import java.sql.PreparedStatement;

import hospital.pojos.Disease;
import ifaces.DiseaseManager;

public class JDBCDiseaseManager implements DiseaseManager{

	private JDBCManager manager;
	public JDBCDiseaseManager(JDBCManager m) {
		this.manager=m;
	}
	@Override
	public void addDisease(Disease d) {
		try {
			String sql = "INSERT INTO diseases (name) VALUES(?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, d.getName());
			p.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
