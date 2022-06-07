package jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Doctor;
import hospital.pojos.Nurse;
import hospital.pojos.Patient;
import ifaces.NurseManager;

public class JDBCNurseManager implements NurseManager {

	private JDBCManager manager;

	public JDBCNurseManager(JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void addNurse(Nurse n) {
		try {
			String sql = "INSERT INTO nurses (name, email) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, n.getName());
			prep.setString(2, n.getEmail());
			prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void assignNurse(int patientId, int nurseId) {
		try {
			String sq1 = "INSERT INTO treat (patientId,nurseId) VALUES (?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sq1);
			p.setInt(1, patientId);
			p.setInt(2, nurseId);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Patient> listMyPatients(int nurseId) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sq1 = "SELECT * FROM patients AS p JOIN treat AS t ON p.id = t.patientId JOIN nurses AS n ON t.nurseId = n.id"
					+ " WHERE n.id=" + nurseId;
			ResultSet rs = stmt.executeQuery(sq1);
			while (rs.next()) {
				Integer id = rs.getInt("idPatient");
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
	public Nurse searchNurseByPatient(int patientId) {
		Nurse n = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sq1 = "SELECT * FROM nurse WHERE nurseId=" + patientId;
			ResultSet rs = stmt.executeQuery(sq1);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				n = new Nurse(name, id, email);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Nurse getNurseById(int nurseId) {
		Nurse n = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sq1 = "SELECT * FROM nurses WHERE id=" + nurseId;
			ResultSet rs = stmt.executeQuery(sq1);
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				n = new Nurse(name, email);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public void deleteNurse(int nurseId) {
		try {
			String sql = "DELETE FROM nurses WHERE id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, nurseId);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePatientStatus(int pId, Boolean severe) {

		try {
			String sq1 = "UPDATE patients SET severe = ? WHERE id =?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sq1);
			ps.setBoolean(1, severe);
			ps.setInt(2, pId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Nurse> listAllNurses() {
		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sq1 = "SELECT * FROM nurses";
			ResultSet rs = stmt.executeQuery(sq1);
			while (rs.next()) {
				String name = rs.getString("name");
				Integer id = rs.getInt("id");
				String email = rs.getString("email");
				Nurse n1 = new Nurse(name, id, email);
				nurses.add(n1);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nurses;
	}

	@Override
	public Nurse getNurseByName(String nurseName) {
		Nurse n = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM nurses WHERE name='" + nurseName + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				n = new Nurse(name, email);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// System.out.println("Error");
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public void updateNurse(Integer nId, String name, String email) {
		try {
			String sql = "UPDATE nurses SET name=?, email=? WHERE id=?";
			PreparedStatement ps = manager.getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, nId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
