package fr.diginamic.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbManager {

    public static ResourceBundle configFile = ResourceBundle.getBundle("config");

    static {
        String driverClass = configFile.getString("db.driver");

        try {
            Class.forName(driverClass);

        } catch (ClassNotFoundException e) {
            System.out.println("Echec de chargement du Driver");
        }
    }

    public static Connection connection() {
        String dbUrl = configFile.getString("db.url");
        String dbUserName = configFile.getString("db.user.name");
        String dbUserPwd = configFile.getString("db.user.pwd");
        // Demande de connection a une DB de type MySQL

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbUserPwd);
			System.out.println("Connexion ouverte : " + !connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Echec de connexion à la base de données : " + e.getMessage());
        }
        return connection;
    }
}






