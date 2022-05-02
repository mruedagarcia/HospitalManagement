package jbdc;

import java.sql.PreparedStatement;
import java.util.List;

import hospital.pojos.Nurse;
import hospital.pojos.Patient;
import ifaces.NurseManager;

public class JDBCNurseManager implements NurseManager{
	
	private JDBCManager manager;

	public JDBCNurseManager(JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void addNurse(Nurse n) {
		try {
			String sql = "INSERT INTO nurses (name) VALUES (?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, n.getName());
			prep.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Nurse> listAllNurses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void assignNurse(int patientId, int nurseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> listMyPatients(int nurseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nurse searchNurseByPatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nurse getNurseById(int nurseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNurse(Nurse n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updatePatientStatus(int patientId) {
		// TODO Auto-generated method stub
		return false;
	}

}
