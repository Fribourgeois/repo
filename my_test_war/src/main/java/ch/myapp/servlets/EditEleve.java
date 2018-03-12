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
import ch.myapp.forms.EleveForm;

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
	public static final String CHAMP_PRENOM = "prenom";
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_RESULTAT = "resultat";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";

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

		/* Préparation de l'objet formulaire */
		EleveForm form = new EleveForm();

		/*
		 * Appel au traitement et à la validation de la requête, et récupération du bean
		 * en résultant
		 */
		eleve = form.saveEleve(request);

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, eleve);

		if (form.getErreurs().isEmpty()) {
			/* Si aucune erreur, alors affichage de la fiche récapitulative */

			// Update

			monBean.updateEleve(eleve);
			
			response.sendRedirect(request.getContextPath() + "/listeEleves");

			;
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

}