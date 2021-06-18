package fr.diginamic;

import fr.diginamic.entites.Departement;
import fr.diginamic.entites.Pays;
import fr.diginamic.entites.Region;
import fr.diginamic.entites.Ville;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recensement {

    private final int compteurVilles;
    private final Map<String, Region> mapRegions = new HashMap<>();
    private final Map<String, Departement> mapDep = new HashMap<>();
    private final Pays france;



    public Recensement(String file) throws IOException {

        Path pathFile = Paths.get(file);
        // Récupération des lignes du fichier CSV dans une liste de String
        List<String> infoVilles = Files.readAllLines(pathFile);
        infoVilles.remove(0);

        france = new Pays("France");

        int compteur = 0;
        for (String line : infoVilles) {

            String[] tabInfosVilles = line.split(";"); // Conversion de chaque ligne CSV en tableau de String

            String codeRegion = tabInfosVilles[0];
            String nomRegion = tabInfosVilles[1];
            String codeDep = tabInfosVilles[2];
            String codeCommune = tabInfosVilles[5];
            String nomCommune = tabInfosVilles[6];
            int populationTotale = Integer.parseInt(tabInfosVilles[9].replaceAll(" ", ""));


            if (!mapRegions.containsKey(codeRegion)) {
                Region region = new Region(codeRegion, nomRegion, france);
                mapRegions.put(codeRegion, region);
            }

            if (!mapDep.containsKey(codeDep)) {
                Departement dep = new Departement(codeDep, codeDep, mapRegions.get(codeRegion), france);
                mapDep.put(codeDep, dep);
            }

            Ville ville = new Ville(codeCommune, codeCommune, mapDep.get(codeDep), mapRegions.get(codeRegion), populationTotale, france);

            compteur++;
        }



        this.compteurVilles = compteur;
    }



    public int getCompteurVilles() {
        return compteurVilles;
    }

    public Pays getPays() {
        return france;
    }

}




