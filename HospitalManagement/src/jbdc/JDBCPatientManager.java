package jbdc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Disease;
import hospital.pojos.Doctor;
import hospital.pojos.Medicine;
import hospital.pojos.Patient;
import hospital.pojos.Symptom;
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
			prep.setBoolean(3, p.getSevere());
			prep.setInt(4, p.getPhone());
			prep.setDate(5, p.getDob()); 
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/*public List<Patient> searchPatientByDoctor(int dId){
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE doctorId="+dId;
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
				boolean status = rs.getBoolean("status");
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
			ps.setBoolean(3, p.getSevere());
			ps.setInt(4, p.getPhone());
			ps.setDate(5, p.getDob());
			ps.executeUpdate();
		} catch (Exception e) {
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

	@Override
	public List<Patient> listAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Integer id =rs.getInt("id");
				String email =rs.getString("email");
				Date dob = rs.getDate("dob");
				Integer phone = rs.getInt("phone");
				String name = rs.getString("name");
				Boolean status = rs.getBoolean("status");
				Patient p = new Patient(id, name, email, status, phone, dob);
				patients.add(p);
			}
	
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patients;
		
		
	}

	@Override
	public List<Doctor> listMyDoctors(int pId) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctors WHERE patientId="+pId;
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

	@Override
	public List<Symptom> listMySymptoms(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disease> listMyDiseases(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> listMyMedicines(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient searchPatientByNurse(int nurseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Patient searchPatientByDoctor(int doctorId) {
		// TODO Auto-generated method stub
		return null;
	}
}
