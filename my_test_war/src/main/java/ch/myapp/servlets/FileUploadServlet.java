package ch.myapp.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.bean.MySessionBeanRemote;
import ch.myapp.domain.model.Plz;

@WebServlet("/uploadservlet")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final static Logger log = LogManager.getLogger(FileUploadServlet.class);
	@EJB
	private MySessionBeanRemote monBean;

	public static String ATT_RESULTAT = "resultat";
	public static final String VUE = "/WEB-INF/jsp/list_plz.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();

					if (!item.isFormField()) {
						String fileName = item.getName();

						String root = getServletContext().getRealPath("/");
						File path = new File(root + "/uploads");
						if (!path.exists()) {
							boolean status = path.mkdirs();
						}
						File uploadedFile = new File(path + "/" + fileName);

						// Pas de fichier spécifié ou fichier non trouvé
						if (fileName.isEmpty() || !checkFile(fileName) == true) {
							log.error("----- Fichier non trouvé ! ");
						
							HttpSession session = request.getSession();

							session.setAttribute(ATT_RESULTAT, "Fichier non trouvé ! ");

							// this.getServletContext().getRequestDispatcher(VUE).forward(request,
							// response);
							response.sendRedirect(request.getContextPath() + "/loadPlz");

							throw new FileNotFoundException();
						}

						// saves the file on disk
						item.write(uploadedFile);

						int tot = createPlz(uploadedFile, request);

						request.setAttribute(ATT_RESULTAT, "L'import s'est terminée avec :" + String.valueOf(tot));

						List<Plz> plz = monBean.listPlz();

						request.setAttribute("plz", plz);

						this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Create plz in the database
	 * 
	 */
	int createPlz(File file, HttpServletRequest request) {

		log.info("----- L'IMPORT EST DEMARRE");
		// supression des anciens plz
		int nbreEfface = monBean.deleteAllPlz();

		log.info("----- Anciennes données effacées --> " + nbreEfface);

		List<String> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1)) {
			// 1. filter line 3
			// 2. convert all content to upper case
			// 3. convert it into a List
			list = stream.filter(line -> line.startsWith("01")).map(String::toUpperCase).collect(Collectors.toList());

			// pas d'enrtegistrements ou problème de fichiers
			if (list.isEmpty()) {
				log.error("----- Problème avec le format du fichier !!!");
				request.setAttribute(ATT_RESULTAT, "Problème avec le format du fichier !!!");
				throw new IllegalArgumentException();

			}

		} catch (IOException e) {
			log.error("----- Problème avec la lecture du fichier " + e.getMessage());
		}

		for (Iterator it = list.iterator(); it.hasNext();) {
			String string = (String) it.next();

			try {
				String[] var = string.split(";");
				Plz plz = new Plz();
				plz.setRecArt(var[0]);
				plz.setOrnp(Integer.parseInt(var[1]));
				plz.setBfsNr(Integer.parseInt(var[2]));
				plz.setPlzTyp(Integer.parseInt(var[3]));
				plz.setPostLeitZahl(Integer.parseInt(var[4]));
				plz.setPlzZz(var[5]);
				plz.setgPlz(Integer.parseInt(var[6]));

				plz.setOrtBez18(var[7]);
				plz.setOrtBez27(var[8]);
				plz.setKanton(var[9]);
				plz.setSprachCode(Integer.parseInt(var[10]));

				if (var[11].length() > 0) {
					plz.setSprachCodeAbw(Integer.parseInt(var[11]));
				}
				if (var[12].length() > 0) {
					plz.setBriefZdurch(Integer.parseInt(var[12]));
				}

				java.util.Date date_util = new java.util.Date();
				String s = var[13];
				SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
				date_util = sdf.parse(s);
				java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
				plz.setGiltAbDatum(date_sql);
				plz.setPlzBriefZust(Integer.parseInt(var[14]));
				// save to tb
				monBean.createPLz(plz);

			} catch (Exception e) {
				log.error(e.toString());

			}
		}

		log.info("----- L'IMPORT EST TERMINEE AVEC : " + list.size());

		return list.size();

	}

	/**
	 * @param pathToFile
	 * @return
	 */
	private static boolean checkFile(String pathToFile) {
		File f = new File(pathToFile);
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;

	}

}