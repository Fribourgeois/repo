package ch.myapp.csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.model.BFSBatiment;

/**
 * @author Francky
 *
 */
public class BFSImportCSV {

	private final static Logger log = LogManager.getLogger(BFSImportCSV.class);

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
			log.info("----- 2) Nom du fichier: par exemple /tmp/FR.csv");
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

		// effacer les données si nécessaire
		if (delRecords)
			deleteRecords();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {

			br = new BufferedReader(new FileReader(fileToPath));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] var = line.split(cvsSplitBy);
				BFSBatiment bfs = new BFSBatiment();

				if (isInteger(var[0])) {
					bfs.setEgid(Integer.parseInt(var[0]));
					bfs.setEdid(Integer.parseInt(var[1]));
					bfs.setGdekt(var[2]);
					bfs.setGdenr(Integer.parseInt(var[3]));
					bfs.setGdename(var[4]);
					bfs.setStrname(var[5]);
					bfs.setDeinr(var[6]);
					bfs.setPlz4(Integer.parseInt(var[7]));
					bfs.setPlzz(Integer.parseInt(var[8]));
					bfs.setPlzname(var[9]);
					bfs.setGkode(var[10]);
					bfs.setGkodn(var[11]);
					bfs.setStrsp(var[12]);

					insertToDataSource(bfs);
				}

			}

		} catch (FileNotFoundException e) {
			log.error("----- Fichier non trouvé : " + e.getMessage());
		} catch (IOException e) {
			log.error("----- Problème à l'ouverture du fichier : " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					log.error("----- Problème à l'ouverture du fichier : " + e.getMessage());

				}
			}
		}
		log.info("----- IMPORT  TERMINE : ");
		log.info("---------------------------------------------------------");
	}

	private static boolean isInteger(String s) {
		boolean isValid = true;
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException nfe) {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * @param plz
	 */
	private static void insertToDataSource(BFSBatiment bfs) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(bfs);

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
		int deletedCount = em.createQuery("DELETE FROM BFSBatiment").executeUpdate();
		em.getTransaction().commit();
		em.close();
		log.info("---------------------------------------------------------");
		log.info("----- Nombre de données effacées : " + deletedCount);
		log.info("---------------------------------------------------------");

	}
}