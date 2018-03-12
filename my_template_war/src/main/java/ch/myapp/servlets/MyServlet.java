package ch.myapp.servlets;

import java.io.IOException;

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
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	public static final String ATT_MESSAGES = "eleve";
	public static final String VUE = "/WEB-INF/eleve.jsp";

	private static final long serialVersionUID = 1L;
	
	@EJB
	private MySessionBeanRemote  monBean;

	final static Logger logger = LogManager.getLogger(MyServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Eleve eleve = monBean.findEleveById(new Long(1));

		request.setAttribute("eleve", eleve);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long coursId = new Long(request.getParameter("coursId"));

		logger.info("Cours id ----->" + coursId);

		this.getServletContext().getRequestDispatcher("/WEB-INF/liste_eleves.jsp").forward(request, response);

	}

}
