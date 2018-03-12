package ch.myapp.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.model.Plz;

/**
 * @author Francky
 *
 */
public class BasicCSVReader {

	private final static Logger log = LogManager.getLogger(BasicCSVReader.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		int nbArgs = args.length;

		// au minimum 2 arguments
		if (nbArgs < 2 || nbArgs > 2) {
			log.info("---------------------------------------------------------");
			log.info("----- Veuillez spécifier comme paramètre :");
			log.info("----- 1) Effacer le contenu de la base de données: Y/N");
			log.info("----- 2) Nom du fichier: par exemple /tmp/adresse.csv");
			log.info("---------------------------------------------------------");
			throw new IllegalArgumentException();
		}

		log.info("---------------------------------------------------------");
		log.info("----- IMPORT  DEMARRE, VEUILLEZ PATIENTER ");

		String deleteDb = args[0];
		String fileToPath = args[1];

		// Pas de fichier spécifié ou fichier non trouvé
		if (fileToPath.isEmpty() || !checkFile(fileToPath) == true) {
			log.error("----- Fichier non trouvé ! ");
			throw new FileNotFoundException();
		}

		boolean bDelete;
		switch (deleteDb) {
		case "Y":
			bDelete = true;

			break;
		case "N":
			bDelete = true;
			break;

		default:
			log.error("----- Argument invalide (Y/N) : " + deleteDb);
			throw new IllegalArgumentException();
		}

		// import
		readFile(args[1], bDelete);

	}

	// le fichier est lu

	/**
	 * @param fileToPath
	 * @param delRecords
	 */
	private static void readFile(String fileToPath, boolean delRecords) {

		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileToPath), StandardCharsets.ISO_8859_1)) {

			// 1. filter line 3
			// 2. convert all content to upper case
			// 3. convert it into a List
			list = stream.filter(line -> line.startsWith("01")).map(String::toUpperCase).collect(Collectors.toList());

			// pas d'enrtegistrements ou problème de fichiers
			if (list.isEmpty()) {
				log.error("----- Problème avec le format du fichier !!!");
				throw new IllegalArgumentException();

			}

		} catch (IOException e) {
			log.error("----- Problème avec la lecture du fichier " + e.getMessage());
		}

		// effacer les données si nécessaire
		if (delRecords)
			deleteRecords();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
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

				insertToDataSource(plz);

			} catch (Exception e) {
				log.error("----- Problème dans la lecture du contenu" + e.getMessage());
			}
		}

		log.info("----- IMPORT  TERMINE : " + list.size());
		log.info("---------------------------------------------------------");
	}

	/**
	 * @param plz
	 */
	private static void insertToDataSource(Plz plz) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(plz);

		em.getTransaction().commit();
		em.close();

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

	/**
	 * 
	 */
	private static void deleteRecords() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		int deletedCount = em.createQuery("DELETE FROM Plz").executeUpdate();
		em.getTransaction().commit();
		em.close();
		log.info("---------------------------------------------------------");
		log.info("----- Nombre de données effacées : " + deletedCount);
		log.info("---------------------------------------------------------");

	}
}