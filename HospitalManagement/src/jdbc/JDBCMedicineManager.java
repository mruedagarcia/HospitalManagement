package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hospital.pojos.Medicine;
import ifaces.MedicineManager;

public class JDBCMedicineManager implements MedicineManager {
	private JDBCManager manager;

	public JDBCMedicineManager(JDBCManager m) {
		this.manager = m;
	}

	/*
	 * @Override public void addMedicine(Medicine m) { try { String sql =
	 * "INSERT INTO medicines (name) VALUES(?)"; PreparedStatement p =
	 * manager.getConnection().prepareStatement(sql); p.setString(1, m.getName());
	 * p.executeUpdate(); }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	@Override
	public List<Medicine> listAllMedicines() {
		List<Medicine> medicines = new ArrayList<Medicine>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM medicines";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
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
	public Medicine getMedicineByName(String medicineName) {
		Medicine m = null;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM medicines WHERE name = " + medicineName;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				m = new Medicine(name);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
}
