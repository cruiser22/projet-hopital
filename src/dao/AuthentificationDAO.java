package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Personnel;

public class AuthentificationDAO {
    private Connection connection;

    public AuthenticationDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO authentification (login, password, nom, metier) VALUES (?,?,?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getNom());
            statement.setInt(4, user.getMetier());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User getUser(String login, String password) {
    	User user = null;
    	try {
    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM authentification WHERE login = ? AND password = ?");
    	statement.setString(1, login);
    	statement.setString(2, password);
    	ResultSet result = statement.executeQuery();
    	if (result.next()) {
    	user = new User();
    	user.setLogin(result.getString("login"));
    	user.setPassword(result.getString("password"));
    	user.setNom(result.getString("nom"));
    	user.setMetier(result.getInt("metier"));
    	}
    	} catch (SQLException e) {
    	e.printStackTrace();
    	}
    	return user;
    	}
    	public Personnel getUser(String login) {
    		Personnel user = null;
    	try {
    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM authentification WHERE login = ?");
    	statement.setString(1, login);
    	ResultSet result = statement.executeQuery();
    	if (result.next()) {
    	user = new Personnel();
    	user.setLogin(result.getString("login"));
    	user.setPassword(result.getString("password"));
    	user.setNom(result.getString("nom"));
    	user.setMetier(result.getInt("metier"));
    	}
    	} catch (SQLException e) {
    	e.printStackTrace();
    	}
    	return user;
    	}

    	public void updateUser(Personnel personnel) {
    	try {
    	PreparedStatement statement = connection.prepareStatement("UPDATE authentification SET password = ?, nom = ?, metier = ? WHERE login = ?");
    	statement.setString(1, personnel.getPassword());
    	statement.setString(2, personnel.getNom());
    	statement.setInt(3, personnel.getMetier());
    	statement.setString(4, personnel.getLogin());
    	statement.executeUpdate();
    	} catch (SQLException e) {
    	e.printStackTrace();
    	}
    	}
