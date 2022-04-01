package jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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

		} catch (Exception e) {
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
			String sq1 = "CREATE TABLE patients (" + ("id      INTEGER PRIMARY KEY AUTOINCREMENT,")
					+ ("name TEXT NOT NULL,") + ("email TEXT NOT NULL,") + ("status TEXT,")
					+ ("phone INTEGER NOT NULL,") + ("Dob DATE,") + ("nurseID INTEGER NOT NULL REFERENCES nurse(id) ON DELETE RESTRICT");
			stmt.executeUpdate(sq1);

			// --------->NURSES

			sq1 = "CREATE TABLE nurse(" + ("id INTEGER PRIMARY KEY AUTOINCREMENT,") + ("name TEXT NOT NULL");
			stmt.executeUpdate(sq1);

			// --------->MEDICINES

			sq1 = "CREATE TABLE medicines (" + ("id INTEGER PRIMARY KEY AUTOINCREMENT,") + ("name TEXT NOT NULL");
			stmt.executeUpdate(sq1);

			// --------->HAVE

			sq1 = "CREATE TABLE have (" + ("diseaseId INTEGER,") + ("symptomId INTEGER");
			stmt.executeUpdate(sq1);

			// --------->EXAMINES

			sq1 = "CREATE TABLE examines(" + ("patientId INTEGER PRIMARY KEY,") + ("doctorId INTEGER PRIMARY KEY,") + ("FOREIGN KEY patientId"
					+ "REFERENCES patients(id) ON DELETE CASCADE)" + "FOREIGN KEY");
			stmt.executeUpdate(sq1);

			// --------->DOCTORS

			sq1 = "CREATE TABLE doctors ( " + ("id INTEGER PRIMARY KEY AUTOINCREMENT,") + ("name TEXT NOT NULL,")
					+ ("speciality TEXT NOT NULL");
			stmt.executeUpdate(sq1);

			// --------->DISEASE

			sq1 = "CREATE TABLE disease (" + ("id INTEGER PRIMARY KEY AUTOINCREMENT,") + ("name TEXT NOT NULL");
			stmt.executeUpdate(sq1);

			// --------->CAN BE CURED

			sq1 = "CREATE TABLE CanBeCured (" + ("diseaseId INTEGER,") + ("medicineId INTEGER");
			stmt.executeUpdate(sq1);

			// --------->SHOWS

			sq1 = "CREATE TABLE shows (" + ("patientId INTEGER,") + ("symptomsId INTEGER");
			stmt.executeUpdate(sq1);

			// --------->SQLITE_SEQUENCES

			sq1 = "CREATE TABlE sqlite_sequence (" + ("name TEXT, ") + ("seq TEXT");
			stmt.executeUpdate(sq1);

			// --------->SUFFERS

			sq1 = "CREATE TABLE suffers (" + ("patientId INTEGER,") + ("diseaseId INTEGER");
			stmt.executeUpdate(sq1);

			// --------->SYMPTOMS

			sq1 = "CREATE TABLE symptoms (" + ("id INTEGER PRIMARY KEY AUTOINCREMENT,") + ("name TEXT NOT NULL");
			stmt.executeUpdate(sq1);
			stmt.close(); // close of the statement
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
