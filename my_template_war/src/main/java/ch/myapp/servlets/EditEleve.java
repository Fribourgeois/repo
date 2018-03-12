package ch.myapp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.bean.MySessionBeanRemote;
import ch.myapp.domain.model.Eleve;

/**
 * Servlet implementation class Accueil
 * 
 * @param <E>
 */
@WebServlet("/editEleve")
public class EditEleve<E> extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String ATT_MESSAGES = "eleve";
	public static final String VUE = "/WEB-INF/jsp/edit_eleve.jsp";
	public static final String CHAMP_NOM = "nom";
	public static final String CHAMP_PRENOM = "nom";
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_RESULTAT = "resultat";

	@EJB
	private MySessionBeanRemote monBean;
	final static Logger logger = LogManager.getLogger(EditEleve.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = (Long) Long.parseLong(request.getParameter("eleveId"));

		Eleve eleve = monBean.findEleveById(id);

		request.setAttribute("eleve", eleve);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Récupération des champs du formulaire. */
		String resultat;
		Map<String, String> erreurs = new HashMap<String, String>();

		Long id = (Long) Long.parseLong(request.getParameter("id"));

		Eleve eleve = monBean.findEleveById(id);

		String nom = request.getParameter(CHAMP_NOM);
		String prenom = request.getParameter(CHAMP_PRENOM);
		eleve.setNom(nom);
		eleve.setPrenom(prenom);

		monBean.updateEleve(eleve);

		/* Validation du champ nom. */
		try {
			validationNom(nom);
		} catch (Exception e) {
			erreurs.put(CHAMP_NOM, e.getMessage());
		}

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			resultat = "Succès de la mise à jour.";
		} else {
			resultat = "Échec de la mise à jour.";
		}

		/* Stockage du résultat et des messages d'erreur dans l'objet request */
		request.setAttribute(ATT_ERREURS, erreurs);
		request.setAttribute(ATT_RESULTAT, resultat);

		/* Transmission de la paire d'objets request/response à notre JSP */
		this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/liste_eleves.jsp").forward(request, response);

	}

	/**
	 * Valide le nom d'utilisateur saisi.
	 */
	private void validationNom(String nom) throws Exception {
		if (nom != null && nom.trim().length() < 3) {
			throw new Exception("Le nom del'élève doit contenir au moins 3 caractères.");
		}
	}

}