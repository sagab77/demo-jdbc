package fr.diginamic;

import fr.diginamic.services.Menu;
import fr.diginamic.services.RecherchePopMax;
import fr.diginamic.services.RecherchePopulation;

import java.io.IOException;
import java.util.Scanner;

public class AdminApplication {

    public static void main(String[] args) throws IOException {

        String filePath = "C:/Users/gabi/IdeaProjects/demo-jdbc/resources/recensement.csv";
        Recensement recensement = new Recensement(filePath);

//		System.out.println("Notre recensement compte " + recens.getCompteurVilles() + " villes françaises.\n");

        Scanner scanner = new Scanner(System.in);

        int choix = 0;
        while (choix != 9) {
            choix = Integer.parseInt(Menu.displayMenu(scanner));
            if (choix < 1 || choix > 9) {
                System.out.println("Saisir une option entre 1 et 9 : ");
            } else {
                switch (choix) {
                    case 1:
                        RecherchePopulation recherche1 = new RecherchePopulation(1, "ville");
                        recherche1.traiter(scanner);
                        break;
                    case 2:
                        RecherchePopulation recherche2 = new RecherchePopulation(2, "département");
                        recherche2.traiter(scanner, recensement);
                        break;
                    case 3:
                        RecherchePopulation recherche3 = new RecherchePopulation(3, "région");
                        recherche3.traiter(scanner, recensement);
                        break;
                    case 4:
                        RecherchePopMax recherche4 = new RecherchePopMax(4, "région");
                        recherche4.traiter(scanner, recensement);
                        break;
                    case 5:
                        RecherchePopMax recherche5 = new RecherchePopMax(5, "département");
                        recherche5.traiter(scanner, recensement);
                        break;
                    case 6:
                        RecherchePopMax recherche6 = new RecherchePopMax(6, "département");
                        recherche6.traiter(scanner, recensement);
                        break;
                    case 7:
                        RecherchePopMax recherche7 = new RecherchePopMax(7, "région");
                        recherche7.traiter(scanner, recensement);
                        break;
                    case 8:
                        RecherchePopMax recherche8 = new RecherchePopMax(8, "ville");
                        recherche8.traiter(scanner, recensement);
                        break;
                    case 9:
                        scanner.close();
                        System.out.println("Sortie du programme");
                        break;
                }
            }
        }
    }
}




