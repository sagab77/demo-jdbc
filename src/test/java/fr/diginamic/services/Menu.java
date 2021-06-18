package fr.diginamic.services;

import fr.diginamic.entites.Country;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

    protected Integer choixMenu;
    String typeRecherche;
    String choixRecherche;

    private static String menu =  "-M E N U-\n"
            +     "|                                                                 |\n"
            +     "|  Sélectionner une donnée à afficher :                           |\n"
            +     "|                                                                 |\n"
            +     "|  1- Population d'une ville                                      |\n"
            +     "|  2- Population d'un département                                 |\n"
            +     "|  3- Population d'une région                                     |\n"
            +     "|  4- Afficher les 10 régions les plus peuplées                   |\n"
            +     "|  5- Afficher les 10 département les plus peuplés                |\n"
            +     "|  6- Afficher les 10 villes les plus peuplées d'un département   |\n"
            +     "|  7- Afficher les 10 villes les plus peuplées d'une région       |\n"
            +     "|  8- Afficher les 10 villes les plus peuplées de France          |\n"
            +     "|                                                                 |\n"
            +     "|  9- Quitter                                                     |\n"
            +     "|_________________________________________________________________|\n";

    public static String displayMenu(Scanner scanner) {
        System.out.println(menu);
        return scanner.nextLine();
    }

    protected void displayTop10(List<Country> listeLieu) {
        Collections.sort(listeLieu, Collections.reverseOrder());
        for (int i = 0; i < 10; i++) {
            listeLieu.get(i).getInfos();
        }
    }

    protected boolean verifSiEntreeOk (List<Country> listeLieu, String code) {
        return listeLieu.stream().anyMatch(lieu -> lieu.getCode().equals(code));
    }

    protected String verifSiEntreeExiste (List<Country> listeLieu, String choixRecherche, Scanner scanner) {
        // tant que ce que rentre l'utilisateur ne correspond à rien
        while (!verifSiEntreeOk(listeLieu, choixRecherche)) {
            System.out.println("La saisie ne correspond à rien");
            System.out.println("1- Saisir de nouveau \n2- Retour");
            Integer newChoix = Integer.parseInt(scanner.nextLine());
            while( newChoix < 1 || newChoix > 2) {
                System.out.println("Choix Incorrect");
                System.out.println("1- Saisir de nouveau \n2- Retour");
                newChoix = Integer.parseInt(scanner.nextLine());
            }
            if (newChoix == 2) {
                return "Quit";
            }
            System.out.print("Merci de saisir un(e) " + typeRecherche + " : ");
            choixRecherche = scanner.nextLine();
        }
        return choixRecherche;
    }

}




