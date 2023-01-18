package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Visite;

public class VisiteDAO {

	private Connection connection;

	public VisiteDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
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

}
