package fr.diginamic.props;

import java.util.ResourceBundle;

public class TestConfigurationProps {

    public static void main(String[] args) {
        ResourceBundle config = ResourceBundle.getBundle("config");
        String name = config.getString("nom");
        String firstname = config.getString("prenom");
        //String email = config.getString("mail");

        //Affiche valeur du 1er param du fichier config
        System.out.println(name);

        //affichage ddifférentes clés et valeurs des paramètres
        for (int i = 0; i < config.keySet().size(); i++) {
            System.out.println(config.keySet().toArray()[i] + " - " + config.getString(config.keySet().toArray()[i].toString()));
        }
    }
}




