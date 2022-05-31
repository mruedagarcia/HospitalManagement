package jbdc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	@Override
	public List<Disease> listAllDiseases() {
		List<Disease> diseases = new ArrayList<Disease>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM diseases";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Disease d = new Disease(id, name);
				diseases.add(d);
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diseases;

	}
	@Override
	public Disease getDiseaseByName(String diseaseName) {
		Disease d = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM diseases WHERE name='" + diseaseName+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				d = new Disease(name);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
}
