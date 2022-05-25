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
import hospital.pojos.Nurse;
import hospital.pojos.Patient;
import hospital.pojos.Symptom;
import ifaces.DoctorManager;

public class JDBCDoctorManager implements DoctorManager {

	private JDBCManager manager;

	public JDBCDoctorManager(JDBCManager m) {
		this.manager = m;
	}

	public List<Doctor> listAllDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT *  FROM doctors";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String specialty = rs.getString("specialty");
				String email = rs.getString("email");
				Doctor d = new Doctor(id, name, specialty, email);
				doctors.add(d);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public void addDoctor(Doctor d) {
		try {
			String sql = "INSERT INTO doctors (name, specialty, email) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, d.getName());
			prep.setString(2, d.getSpecialty());
			prep.setString(3, d.getEmail());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateDoctor(Doctor d) {
		try {
			String sql = "UPDATE doctors" + " SET name=?" + " specialty=?" + "email=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setString(1, d.getName());
			ps.setString(2, d.getSpecialty());
			ps.setString(3, d.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Patient> listMyPatients(int doctorId) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patients AS p JOIN examines AS e ON p.id = e.patientId JOIN doctors AS d ON e.doctorId = d.id"
					+ "WHERE d.id=" + doctorId;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Boolean severe = rs.getBoolean("severe");
				Integer phone = rs.getInt("phone");
				Date dob = rs.getDate("dob");
				Patient p = new Patient(id, name, email, severe, phone, dob);
				patients.add(p);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public void assignDoctor(int patientId, int doctorId) {
		try {
			String sql = "INSERT INTO examines (patientId, doctorId) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, patientId);
			p.setInt(2, doctorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void assignSymptomDisease(int symptomId, int diseaseId) {
		try {
			String sql = "INSERT INTO have (symptomId, diseaseId) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, symptomId);
			p.setInt(2, diseaseId);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public Doctor getDoctorById(int dId) {
		Doctor d = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sq1 = "SELECT * FROM doctors WHERE id=" + dId;
			ResultSet rs = stmt.executeQuery(sq1);
			while (rs.next()) {
				String name = rs.getString("name");
				String specialty = rs.getString("specialty");
				String email = rs.getString("email");
				d = new Doctor(name, specialty, email);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public Disease searchDiseaseBySymptoms(List<Symptom> symptoms) {
		Disease d = new Disease();
		ResultSet rs = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			for (Symptom symptom : symptoms) {// for each symptom, we take each id in the list of Symptoms
				Integer id = symptom.getId();
				String sql = "SELECT * FROM diseases AS d JOIN have AS h ON d.id = h.diseaseId WHERE h.symptomId ="
						+ id;
				rs = stmt.executeQuery(sql);
			}
			while (rs.next()) {
				Integer id = rs.getInt(1);// when there are alias like p or n, we should put the number of the column
											// instead of the attribute
				String name = rs.getString("name");
				d = new Disease(id, name);

			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public List<Medicine> searchMedicineByDisease(Disease d) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM medicines AS m JOIN CanBeCured AS c ON m.id = c.medicineId"
					+ " WHERE c.diseaseId=" + d.getId();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt(1);// when there are alias like p or n, we should put the number of the column
											// instead of the attribute
				String name = rs.getString("name");
				Medicine m = new Medicine(id, name);
				medicines.add(m);

			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return medicines;
	}

	@Override
	public void assignDiseaseMedicine(int diseaseId, int medicineId) {
		try {
			String sql = "INSERT INTO CanBeCured (diseaseId, medicineId) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, diseaseId);
			p.setInt(2, medicineId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
