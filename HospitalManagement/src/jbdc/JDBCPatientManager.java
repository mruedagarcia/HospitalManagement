package jbdc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import hospital.pojos.Patient;

public class JDBCPatientManager {
	
	private JDBCManager manager;
	
	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	public void addPatient(Patient p) {
		try {
			String sql = "INSERT INTO patients (name, email, status, phone, Dob) VALUES (?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setString(2, p.getEmail());
			prep.setString(3, p.getStatus());
			prep.setInt(4, p.getPhone());
			prep.setDate(5, p.getDob());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Patient> searchPatientByDoctor(int doctorId){
		//TODO complete
		return null;
	}
	
	public Patient getPatientById(int id) {
		Patient p = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE id="+id;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String status = rs.getString("status");
				Integer phone = rs.getInt("phone");
				Date date = rs.getDate("Dob"); //i don't know if it is util.date or sql.date
				p = new Patient(name, email, status, phone, date);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void assign(int doctorId, int patientId) {
		try {
			String sql = "INSERT INTO examines(patientId, doctorId) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1,  patientId);
			p.setInt(2,  doctorId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void unassign(int doctorId, int patientId) {
		try {
			String sql = "DELETE FROM examines WHERE patientId=? AND doctorId=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1,  patientId);
			p.setInt(2,  doctorId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletePatient(int patientId) {
		try {
			String sql = "DELETE FROM patients WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, patientId);
			p.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
