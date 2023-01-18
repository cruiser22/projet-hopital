package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Patient;

public class PatientDAO {
	private Connection connection;

	public PatientDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void createPatient(Patient patient) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO patients (nom, prenom, age, telephone, adresse, id) VALUES (?,?,?,?,?,?)");
			statement.setString(1, patient.getNom());
			statement.setString(2, patient.getPrenom());
			statement.setInt(3, patient.getAge());
			statement.setString(4, patient.getTelephone());
			statement.setString(5, patient.getAdresse());
			statement.setInt(6, patient.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Patient getPatient(int id) {
		Patient patient = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM patients WHERE id = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				patient = new Patient(result.getInt("id"), result.getString("nom"), result.getString("prenom"),
						result.getInt("age"), result.getString("telephone"), result.getString("adresse"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patient;
	}

	public void updatePatient(Patient patient) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE patients SET nom = ?, prenom = ?, age = ?, telephone = ?, adresse = ? WHERE id = ?");
			statement.setString(1, patient.getNom());
			statement.setString(2, patient.getPrenom());
			statement.setInt(3, patient.getAge());
			statement.setString(4, patient.getTelephone());
			statement.setString(5, patient.getAdresse());
			statement.setInt(6, patient.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM patients");
			while (result.next()) {
				patients.add(new Patient(result.getInt("id"), result.getString("nom"), result.getString("prenom"),
						result.getInt("age"), result.getString("telephone"), result.getString("adresse")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}
}
