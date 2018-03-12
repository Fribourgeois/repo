package ch.myapp.servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ListeEleves
 */
@WebServlet("/listeEleves")
public class ListeEleves extends HttpServlet {

	@EJB
	private MySessionBeanRemote monBean;

	final static Logger logger = LogManager.getLogger(MyServlet.class);

	private static final long serialVersionUID = 1L;
	public static final String ATT_MESSAGES = "eleves";
	public static final String VUE = "/WEB-INF/jsp/liste_eleves.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		List<Eleve> eleves = monBean.listeEleves();

		request.setAttribute("eleves", eleves);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

}
