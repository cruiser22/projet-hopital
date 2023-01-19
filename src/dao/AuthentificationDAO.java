package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Personnel;

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
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM authentification WHERE login = ? AND password = ?");
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    personnel = new Personnel(result.getString(("login")),(result.getString("password")),(result.getString("nom")),(result.getInt("metier")));
                  
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return personnel;
        }
        }