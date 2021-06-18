package fr.diginamic;

import fr.diginamic.services.DbManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class IntegrationRecensement {

    public static void main(String[] args) throws IOException {



        String filePath = "C:/Users/gabi/IdeaProjects/demo-jdbc/resources/recensement.csv";

        Recensement recensement = new Recensement(filePath);
        Path pathFile = Paths.get(filePath);



        List<String> infoVilles = Files.readAllLines(pathFile);
        String remove = infoVilles.remove(0);

        Connection connection = DbManager.connection();



        Statement  statement = null;

        try {
            statement = connection.createStatement();
            for (String line:infoVilles) {

                String[] tabInfoVilles = line.split(";");
                // Recuperation des colonnes  du fichier CSV
                String codeRegion = tabInfoVilles[0];
                String codeDep = tabInfoVilles[1];
                String nomComunne = tabInfoVilles[5];
                int populationTotale = Integer.parseInt(tabInfoVilles[9].replaceAll(" ",""));

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + "ville(nom, population) " + "VALUES (?,?)");

                preparedStatement.setString(1,nomComunne);
                preparedStatement.setInt(2,populationTotale);

                preparedStatement.executeUpdate();
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.err.println("Echec requete SQL : " + e.getMessage());
        }


    }


}
