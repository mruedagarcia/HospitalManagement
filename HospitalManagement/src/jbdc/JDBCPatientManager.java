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
			String sql = "INSERT INTO patients (name, email, severe, phone, Dob) VALUES (?,?,?,?,?)";
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
	public Patient searchPatientByDoctor(int dId){
		Patient p = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients WHERE doctorId="+dId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Boolean severe = rs.getBoolean("severe");
				Integer phone = rs.getInt("phone");
				Date dob = rs.getDate("date of birth");
				p = new Patient(id, name, email, severe, phone, dob);
			}
			rs.close();
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
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
				boolean severe = rs.getBoolean("severe");
				Integer phone = rs.getInt("phone");
				Date date = rs.getDate("date");
				p = new Patient(name, email, severe, phone, date);
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
			String sql = "UPDATE patients" + " SET name=?" + " email=?" + " severe=?" + " phone=?" + "date of birth=?";
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
				Boolean severe = rs.getBoolean("severe");
				Patient p = new Patient(id, name, email, severe, phone, dob);
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
			String sql = "SELECT * FROM patients AS p JOIN examines AS e ON p.id = e.patientId JOIN doctors AS d ON e.doctorId = d.id"
					+ "WHERE patientId="+pId;
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
	public List<Symptom> listMySymptoms(int pId) {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients AS p JOIN suffers AS s ON p.id = s.patientId JOIN diseases AS d ON s.diseaseId ="
					+ "d.id JOIN have AS h ON d.id=h.diseaseId JOIN symptoms AS symp ON h.symptomId = symp.id WHERE symp.id="+pId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String name = rs.getString("name");
				Integer id = rs.getInt("id");
				Symptom s = new Symptom(name, id);
				symptoms.add(s);
		}
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return symptoms;
	}

	@Override
	public List<Disease> listMyDiseases(int dId) {
		List<Disease> diseases = new ArrayList<Disease>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients AS  p JOIN suffers AS s ON p.id = s.patientId JOIN diseases AS d ON s.diseaseId="
					+ "d.id WHERE d.id="+dId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = rs.getString("name");
				Disease d = new Disease(id, name);
				diseases.add(d);
		}
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return diseases;
	}

	@Override
	public List<Medicine> listMyMedicines(int pId) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients AS p JOIN suffers AS s ON p.id = s.patientId JOIN diseases AS d ON s.diseaseId = d.id JOIN CanBeCured"
					+ "AS c ON d.id =c.diseaseId JOIN medicines AS m ON c.medicineId = m.id WHERE p.id = "+pId;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = rs.getString("name");
				Medicine m = new Medicine(id,name);
				medicines.add(m);
				
		}
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return medicines;
	}

	@Override
	public Patient searchPatientByNurse(int nurseId) {
		Patient p = new Patient();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients AS p JOIN nurses AS n ON p.nurseId = n.id WHERE n.id="+nurseId;
			//String sql = "SELECT * FROM patients AS p JOIN nurses AS n USING(nurseId)";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				Integer id =rs.getInt(1);//when there are alias like p or n, we should put the number of the column instead of the attribute
				String name = rs.getString("name");
				String email =rs.getString("email");
				Boolean severe = rs.getBoolean("severe");
				Integer phone = rs.getInt("phone");
				Date dob = rs.getDate("dob");
				p = new Patient(id, name, email, severe, phone, dob);
				
		}
			rs.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public static void main(String[] args) {
		//TODO 
	}
}
