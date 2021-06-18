package fr.diginamic.entites;


// La classe mere pour toutes classes ( Ville,Departement,Region et Pays)

import java.util.ArrayList;
import java.util.List;

public abstract class Country implements Comparable {
    protected Integer population = 0;
    protected String code;
    protected String nom;
    protected List<Country> listeVille = new ArrayList<>();

    public Country(String nom, String code) {
        this.code = code;
        this.nom = nom;
    }


    public void getInfos() {
        System.out.println(this.nom + " - " + this.population + " hab");
    }

    public Integer getPopulation() {
        return population;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public List<Country> getListeVille() {
        return listeVille;
    }

    @Override
    public int compareTo(Object o) {
        Country autreLieu = (Country) o;
        return this.population.compareTo(autreLieu.population);
    }

}




