package jbdc;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Doctor;

public class JDBCDoctorManager {
	
	private JDBCManager manager;
	
	public JDBCDoctorManager(JDBCManager m) {
		this.manager = m;
	}
	
	public List<Doctor> listAllDoctors(){
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT *  FROM doctors";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String specialty = rs.getString("specialty");
				Doctor d = new Doctor(id, name, specialty);
				doctors.add(d);
			}
			rs.close();
			stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		return doctors;
	}
}
