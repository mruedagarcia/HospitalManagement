package jbdc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Patient;
import ifaces.PatientManager;

public class JDBCPatientManager implements PatientManager{
	
	private JDBCManager manager;
	
	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
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
	
	@Override
	/*public List<Patient> searchPatientByDoctor(int doctorId){
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String status = rs.getString("status");
				Integer phone = rs.getInt("phone");
				Date dob = rs.getDate("date of birth");
				Patient p = new Patient(id, name, email, status, phone, dob);
				patients.add(p);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patients;
	}*/
	
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
				Date date = rs.getDate("date");
				p = new Patient(name, email, status, phone, date);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	@Override
	public void updatePatient(Patient p) {
		try {
			String sql = "UPDATE patients" + " SET name=?" + " email=?" + " status=?" + " phone=?" + "date of birth=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getStatus());
			ps.setInt(4, p.getPhone());
			ps.setDate(5, p.getDob());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void assignDoctor(int doctorId, int patientId) {
		try {
			String sql = "INSERT INTO examines(patientId, doctorId) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1,  patientId);
			p.setInt(2,  doctorId);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
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
