package ch.myapp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ch.myapp.domain.model.Eleve;

public class EleveForm {

	private static final String CHAMP_ID= "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_DATENAISSANCE = "dateNaisance";

	private String resultat;

	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Eleve saveEleve(HttpServletRequest request) {
		

		String id = getValeurChamp(request, CHAMP_ID);
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);

		Eleve eleve = new Eleve();

		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		eleve.setNom(nom);

		try {
			validationPrenom(prenom);
		} catch (Exception e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		eleve.setPrenom(prenom);

		if (erreurs.isEmpty()) {
			resultat = "Succès de l'inscription.";
		} else {
			resultat = "Échec de l'inscription.";
		}

		return eleve;
	}

	private void validationEmail(String email) throws Exception {
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	private void validationMotsDePasse(String motDePasse, String confirmation) throws Exception {
		if (motDePasse != null && confirmation != null) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
			} else if (motDePasse.length() < 3) {
				throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom == null && nom.length() < 3) {
			throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	private void validationPrenom(String prenom) throws Exception {
		if (prenom != null && prenom.length() < 3) {
			throw new Exception("Le prénom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

}
