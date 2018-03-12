package ch.myapp.cvsreader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
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

public class BasicCSVReader {

	private final static Logger log = LogManager.getLogger(BasicCSVReader.class);

	private final static String RESOURCES_PATH = "src/main/resources/";
	private static final String SAMPLE_CSV_FILE_PATH = "adresse.csv";
	// private static final String SAMPLE_CSV_FILE_PATH = "user.csv";

	public static void main(String[] args) throws IOException {

		log.info("----- IMPORT  START, PLEASE WAIT");

		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(RESOURCES_PATH + SAMPLE_CSV_FILE_PATH),
				StandardCharsets.ISO_8859_1)) {

			// 1. filter line 3
			// 2. convert all content to upper case
			// 3. convert it into a List
			list = stream.filter(line -> line.startsWith("01")).map(String::toUpperCase).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

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
				log.error(e.toString());

			}

		}

		log.info("----- IMPORT  ENDED : " + list.size());

	}

	private static void insertToDataSource(Plz plz) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(plz);

		em.getTransaction().commit();
		em.close();

	}
}