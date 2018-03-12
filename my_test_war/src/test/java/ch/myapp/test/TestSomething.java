package ch.myapp.test;

import java.io.InputStream;
import java.util.Properties;

public class TestSomething {

	public static void main(String[] args) {

		String nom = null;
		try {
			validationNom(nom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void validationNom(String nom) throws Exception {
		if (nom != null && nom.length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
		if (nom == null) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		} else {

		}
	}
}
