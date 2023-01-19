package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Medecin;
import model.Personnel;
import model.Secretaire;

public class AuthentificationDAO {
	private Connection connection;

	public AuthentificationDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hopital", "root", "root");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Personnel verif(String login, String password) {
		Personnel personnel = null;
		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM authentification WHERE login = ? AND password = ?");
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				personnel = new Personnel(result.getString(("login")), (result.getString("password")),
						(result.getString("nom")), (result.getInt("metier")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	public void createMedecin(Medecin medecin) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO authentification  VALUES (?,?,?,?)");
			statement.setString(1, medecin.getLogin());
			statement.setString(2, medecin.getPassword());
			statement.setString(3, medecin.getNom());
			statement.setInt(4, medecin.getMetier());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteMedecin(String login) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM authentification WHERE login = ?");
			statement.setString(1, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Medecin getMedecin(String login) {
		Medecin medecin = null;
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM authentification WHERE login = ?");
			statement.setString(1, login);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				medecin = new Medecin(result.getString(1), result.getString(2), result.getString(3), result.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return medecin;
	}

	public void createSecretaire(Secretaire secretaire) {
		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO authentification VALUES (?,?,?,?)");
			statement.setString(1, secretaire.getLogin());
			statement.setString(2, secretaire.getPassword());
			statement.setString(3, secretaire.getNom());
			statement.setInt(4, secretaire.getMetier());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSecretaire(String login) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM authentification WHERE login = ?");
			statement.setString(1, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}