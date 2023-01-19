package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Visite;

public class VisiteDAO {

	private Connection connection;

	public VisiteDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void createVisit(Visite visite) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO visites (id, idpatient, date, medecin, numSalle, tarif) VALUES (?,?,?,?,?,?)");
			statement.setInt(1, visite.getId());
			statement.setInt(2, visite.getIdPatient());
			statement.setString(3, visite.getDate().toString());
			statement.setString(4, visite.getMedecin());
			statement.setInt(5, visite.getNumSalle());
			statement.setInt(6, visite.getTarif());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Visite> getVisiteByMedecin(String medecin) {
		ArrayList<Visite> visites = new ArrayList<Visite>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM visites where medecin ='" + medecin + "'");
			while (result.next()) {
				visites.add(new Visite(result.getInt(1), result.getInt(2), result.getString(4),
						LocalDateTime.parse(result.getString(3)), result.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visites;
	}
	
	public ArrayList<Visite> getVisiteBypatientId(int patientId) {
		ArrayList<Visite> visites = new ArrayList<Visite>();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM visites where idpatient =" +patientId);
			while (result.next()) {
				visites.add(new Visite(result.getInt(1), result.getInt(2), result.getString(4),
						LocalDateTime.parse(result.getString(3)), result.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visites;
		
		
	}
	

}
