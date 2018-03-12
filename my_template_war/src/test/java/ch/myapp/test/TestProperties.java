package ch.myapp.test;

import java.io.InputStream;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) {
		try {
			// chargement des propriétés

			Properties prop = new Properties();
			InputStream is = TestProperties.class.getClassLoader().getResourceAsStream("config-test.properties");

			prop.load(is);
			// Affichage des propriétés
			// Récupère la propriété ma.cle
			// Si la propriété n'existe pas, retourne la valeur par défaut "vide"
			System.out.println("key 1 " + prop.getProperty("key1", " est vide"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
