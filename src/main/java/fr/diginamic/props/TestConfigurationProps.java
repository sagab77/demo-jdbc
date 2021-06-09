package fr.diginamic.props;

import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.ResourceBundle;

public class TestConfigurationProps {
    public static void main(String[] args) {



        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties("config.properties");
            String nomAdmin = config.getString("nom");
            System.out.println(nomAdmin);
        } catch (ConfigurationException e) {throw new RuntimeException("Fichier introuvable");
        }



        Configurations configs1 = new Configurations();


        try {
            XMLConfiguration config = configs.xml("config.xml");
            String host = config.getString("database.host");
            System.out.println(host);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }


    }
}
