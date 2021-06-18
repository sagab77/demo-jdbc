package fr.diginamic.services;

import fr.diginamic.Recensement;
import fr.diginamic.entites.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecherchePopulation extends Menu {

    public RecherchePopulation(int choixMenu, String typeRecherche) {
        this.choixMenu = choixMenu;
        this.typeRecherche = typeRecherche;

    }

    public void traiter(Scanner scanner) {
        System.out.print("Saisir une ville : ");
        choixRecherche = scanner.nextLine();

        try (Connection connection = DbManager.connection()) {

            Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM ville "
                        + "WHERE nom = '" + choixRecherche + "'");
                if (result.next()) {
                    Integer id = result.getInt("id");
                    String nom = result.getString("nom");
                    Integer population = result.getInt("population");
                    System.out.println(nom.toUpperCase() + " - " + population + " hab");
                } else {

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void traiter(Scanner scanner, Recensement recensement) {
        System.out.print("Saisir un(e) " + typeRecherche + " : ");
        choixRecherche = scanner.nextLine();

        List<Country> choixListe = new ArrayList<>();

        if (choixMenu == 1) {

            choixListe = recensement.getPays().getListeVille();
        } else if (choixMenu == 2) {
            choixListe = recensement.getPays().getListeDep();
        } else { // choix 3
            choixListe = recensement.getPays().getListeRegion();
        }

        choixRecherche = verifSiEntreeExiste(choixListe, choixRecherche, scanner);
        if (choixRecherche.equals("Quit")) {
            return;
        }


        String message = null;
        for (Country country : choixListe) {
            if (choixRecherche.equals(country.getCode())) {
                country.getInfos();
            } else {

            }
        }
        System.out.println("\nAppuyer sur une touche pour continuer");
        scanner.nextLine();
        return;
    }
}



