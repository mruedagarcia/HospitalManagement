package jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCManager {
	private Connection c = null;

	public JDBCManager() {
		try {
			Class.forName("org.sqlite.JDBC");
			// here we get the Connection
			c = DriverManager.getConnection("jdbc:sqlite:./db/HospitalManagement.db");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");
			this.createTables();// here we do the call of our DataBase's Tables
			this.insertIntoTables();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertIntoTables() {
		try {
			List<String> s = new ArrayList<String>();
			s.add("headache");
			s.add("stomache");
			s.add("nasal congestion");
			s.add("tiredness");
			s.add("overall pain");
			s.add("bone pain");
			s.add("tachycardia");
			s.add("blackout");
			s.add("loss of smell");
			s.add("puke");
			List<String> d = new ArrayList<String>();
			d.add("migraines");
			d.add("stomach flu");
			d.add("cancer");
			d.add("COVID");
			d.add("heart disease");
			d.add("diabetes");
			d.add("flu");
			d.add("alzheimer");
			d.add("bronchitis");
			d.add("arthritis");
			List<String> m = new ArrayList<String>();
			m.add("ibuprofen");
			m.add("gelocatil");
			m.add("antibiotic");
			m.add("quimiotherapy");
			m.add("remdesivir");
			m.add("insulin");
			m.add("benazepril");
			m.add("nolotil");
			m.add("enantyum");
			m.add("naproxeno");
			
			Statement stmt = c.createStatement();
			for (String name : s) {// for each symptom, we take each id in the list of Symptoms
				String sql = "INSERT INTO symptoms (name) VALUES ('" + name + "');";
				stmt.executeUpdate(sql);
			}
			for (String name : d) {// for each symptom, we take each id in the list of Symptoms
				String sql = "INSERT INTO diseases (name) VALUES ('" + name + "');";
				stmt.executeUpdate(sql);
			}
			for (String name : m) {// for each symptom, we take each id in the list of Symptoms
				String sql = "INSERT INTO medicines (name) VALUES ('" + name + "');";
				stmt.executeUpdate(sql);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	private void createTables() {// creation of tables
		try {
			Statement stmt = c.createStatement();
			// PATIENTS
			String sq1 = "CREATE TABLE patients (" + "id      INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, " + "email TEXT NOT NULL, " + "severe BOOLEAN, " + "phone INTEGER NOT NULL, "
					+ "dob DATE , " + "nurseID INTEGER REFERENCES nurses(id) ON DELETE RESTRICT" + ");";
			stmt.executeUpdate(sq1);

			// --------->NURSES

			sq1 = "CREATE TABLE nurses(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL," + "email TEXT NOT NULL" +");";
			stmt.executeUpdate(sq1);

			// --------->MEDICINES

			sq1 = "CREATE TABLE medicines (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL" + ");";
			stmt.executeUpdate(sq1);

			// --------->HAVE

			sq1 = "CREATE TABLE have (" + "diseaseId INTEGER," + "symptomId INTEGER,"
					+ "FOREIGN KEY (diseaseId) REFERENCES diseases(id)"
					+ "FOREIGN KEY(symptomId) REFERENCES symptoms(id)" + "PRIMARY KEY (diseaseId, symptomId)" + ");";
			stmt.executeUpdate(sq1);

			// --------->EXAMINES

			sq1 = "CREATE TABLE examines(" + "patientId INTEGER, " + "doctorId INTEGER, "
					+ "FOREIGN KEY (patientId) REFERENCES patients(id) ON DELETE CASCADE, "
					+ "FOREIGN KEY (doctorId) REFERENCES doctors(id) ON DELETE CASCADE, "
					+ "PRIMARY KEY (patientId, doctorId)" + ");";
			stmt.executeUpdate(sq1);

			// --------->DOCTORS

			sq1 = "CREATE TABLE doctors ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "specialty TEXT NOT NULL," + "email TEXT NOT NULL" +");";
			stmt.executeUpdate(sq1);

			// --------->DISEASE

			sq1 = "CREATE TABLE diseases (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL" + ");";
			stmt.executeUpdate(sq1);

			// --------->CAN BE CURED

			sq1 = "CREATE TABLE CanBeCured (" + "diseaseId INTEGER," + "medicineId INTEGER,"
					+ "FOREIGN KEY (diseaseId) REFERENCES diseases(id),"
					+ "FOREIGN KEY (medicineId) REFERENCES medicines(id)" + "PRIMARY KEY (diseaseId, medicineId)"
					+ ");";
			stmt.executeUpdate(sq1);

			// --------->SHOWS

			sq1 = "CREATE TABLE shows (" + "patientId INTEGER," + "symptomsId INTEGER,"
					+ "FOREIGN KEY (patientId) REFERENCES patients(id),"
					+ "FOREIGN KEY (symptomsId) REFERENCES symptoms(id)" + "PRIMARY KEY (patientId, symptomsId)" + ");";
			stmt.executeUpdate(sq1);

			// --------->SUFFERS

			sq1 = "CREATE TABLE suffers (" + "patientId INTEGER," + "diseaseId INTEGER,"
					+ "FOREIGN KEY (patientId) REFERENCES patients(id),"
					+ "FOREIGN KEY (diseaseId) REFERENCES diseases(id)" + "PRIMARY KEY (patientId, diseaseId)" + ");";
			stmt.executeUpdate(sq1);

			// --------->SYMPTOMS

			sq1 = "CREATE TABLE symptoms (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL" + ");";
			stmt.executeUpdate(sq1);

			
			stmt.executeUpdate(sq1);
			// stmt.close(); close of the statement
		} catch (SQLException e) {
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}
}
