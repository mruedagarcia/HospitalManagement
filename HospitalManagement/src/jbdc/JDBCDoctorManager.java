package jbdc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Disease;
import hospital.pojos.Doctor;
import hospital.pojos.Medicine;
import hospital.pojos.Patient;
import hospital.pojos.Symptom;
import ifaces.DoctorManager;

public class JDBCDoctorManager implements DoctorManager{
	
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

	@Override
	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctors (name, specialty) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getSpecialty()); 
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateDoctor(Doctor d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> listMyPatients(int doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignDoctor(int patientId, int doctorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignSymptomDiseaseMedicine(int symptomId, int diseaseId, int medicineId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Disease searchDiseaseBySymptoms(List<Symptom> symptoms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicine> searchMedicineByDisease(Disease d) {
		// TODO Auto-generated method stub
		return null;
	}
}
