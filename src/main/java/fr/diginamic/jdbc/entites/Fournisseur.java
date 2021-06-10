package fr.diginamic.jdbc.entites;

public class Fournisseur {

    private int id;
    private String nom;

    public Fournisseur(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                " nom =" + nom;
    }
}
