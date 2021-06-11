package fr.diginamic.xml;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.util.Iterator;

public class TestConfigurationXML {

    public static void main(String[] args) {

        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml("config.xml");
            String lastname = config.getString("user-infos.lastname");

            System.out.println(lastname);

            Iterator<String> keyIt = config.getKeys();
            while (keyIt.hasNext())
            {
                String currentKey = keyIt.next();
                String currentValue = config.getString(currentKey);
                System.out.println(currentKey + " - " + currentValue);
            }

        } catch (org.apache.commons.configuration2.ex.ConfigurationException e) {
            e.printStackTrace();
        }


    }
}
