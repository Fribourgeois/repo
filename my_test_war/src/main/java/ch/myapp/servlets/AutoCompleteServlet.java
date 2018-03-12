package ch.myapp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import ch.myapp.bean.MySessionBeanRemote;
import ch.myapp.domain.model.Plz;

@WebServlet("/autocomplete/*")
public class AutoCompleteServlet extends HttpServlet {
	@EJB
	private MySessionBeanRemote monBean;

	final static Logger logger = LogManager.getLogger(MyServlet.class);

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		List<Plz> plz = monBean.listPlz();
		Collections.sort(plz, new Plz());
		// Map real data into JSON
		response.setContentType("application/json");

		final String param = request.getParameter("term");
		final List<AutoCompleteData> result = new ArrayList<AutoCompleteData>();
		for (final Plz plzs : plz) {
			if (plzs.getOrtBez27().toLowerCase().startsWith(param.toLowerCase())) {
				result.add(new AutoCompleteData(String.valueOf(plzs.getOrtBez27()), plzs.getOrtBez27()));

			}
		}

		response.getWriter().write(new Gson().toJson(result));

	}

}
