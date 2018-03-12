

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import ch.myapp.bean.MySessionBean;
import ch.myapp.bean.MySessionBeanRemote;
import ch.myapp.domain.dao.EleveDAO;
import ch.myapp.domain.model.Cours;
import ch.myapp.domain.model.Eleve;

public class EJBTester {

	final static Logger logger = LogManager.getLogger(EleveDAO.class);

	BufferedReader brConsoleReader = null;
	Properties props;
	InitialContext ctx;
	InputStream fis = null;
	{
		props = new Properties();
		try {

			fis = EJBTester.class.getResourceAsStream("jndi.properties");
			if (fis == null) {
				fis = EJBTester.class.getResourceAsStream("/jndi.properties");
			}
			props.load(fis);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {

			final String moduleName = "my_template_ear-0.0.1-SNAPSHOT/my_template_ejb-0.0.1-SNAPSHOT";
			final String beanName = MySessionBean.class.getSimpleName();
			final String viewClassName = MySessionBeanRemote.class.getName();

			System.out.println("NOM DU BEAN " + "ejb:" + "/" + moduleName + '/' + beanName + '!' + viewClassName);

			/*
			 * Properties props = new Properties();
			 * props.put(Context.INITIAL_CONTEXT_FACTORY,
			 * "org.jboss.naming.remote.client.InitialContextFactory");
			 * props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
			 * props.put("jboss.naming.client.ejb.context", true);
			 * props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			 * props.put(
			 * "jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
			 * "false"); ctx = new InitialContext(props);
			 */

			ctx = new InitialContext(props);

			MySessionBeanRemote myBean = (MySessionBeanRemote) ctx
					.lookup("ch.myapp.bean.MySessionBeanRemote#ch.myapp.bean.MySessionBeanRemote");

			/*
			 * MySessionBeanRemote ref = (MySessionBeanRemote) ctx .lookup("ejb:" + "/" +
			 * moduleName + '/' + beanName + '!' + viewClassName);
			 */

			logger.info("----- F I N D  E L E V E -----");
			logger.info("------------------------------");

			// get eleve
			Eleve e = myBean.findEleveById(new Long(1));
			System.out.println("Eleve: " + e.getNom() + " " + e.toString());

			Set<Cours> c = e.getCours();

			for (Iterator iterator = c.iterator(); iterator.hasNext();) {
				Cours cours = (Cours) iterator.next();
				logger.info("----------------------------------");
				logger.info("cours --->:" + cours.getName() + " " + cours.getId());

			}

			logger.info("----- UPDATE E L E V E -->" + e.getId() + "  -----");
			logger.info("-----------------------------------");

			e.setNom(e.getNom() + " UPDATE ");
			myBean.updateEleve(e);

			// new eleve

			logger.info("----- C R E A T E  E L E V E -----");
			logger.info("----------------------------------");

			Eleve e1 = new Eleve();
			e1.setNom("BORCARD61");
			e1.setPrenom("François");
			e1.setDateNaissance(new DateTime("1964-05-10").toDate());
			e1.setAdresse("Impasse des Vignes 4");

			e1 = myBean.createEleve(e1);
			logger.info("----- CREATION de l'élève " + e1.getId());

			logger.info("----- DELETE  E L E V E " + e.getId() + "  -----");
			logger.info("-------------------------------------");

			myBean.deleteEleve(e1);

			logger.info("----- LISTE  E L E V E   -----");
			logger.info("-------------------------------");
			List<Eleve> eleves = myBean.listeEleves();
			for (Iterator iterator = eleves.iterator(); iterator.hasNext();) {
				Eleve eleve = (Eleve) iterator.next();

				logger.info(eleve.toString());

			}

		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {

		EJBTester ejbTester = new EJBTester();

		// ejbTester.testEmbeddedObjects();
	}

	private void showGUI() {
		System.out.println("**********************");
		System.out.println("Welcome to Book Store");
		System.out.println("**********************");
		System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
	}

	@Test(enabled = false)
	private void testEmbeddedObjects() {
		/*
		 * try { int choice = 1;
		 * 
		 * MySessionBeanRemote bean = (MySessionBeanRemote) ctx
		 * .lookup("ch.myapp.bean.MySessionBeanRemote#ch.myapp.bean.MySessionBeanRemote"
		 * );
		 * 
		 * while (choice != 2) { String coursName; String eleveName;
		 * 
		 * showGUI(); String strChoice = brConsoleReader.readLine(); choice =
		 * Integer.parseInt(strChoice); if (choice == 1) {
		 * System.out.print("Enter cours name: "); coursName =
		 * brConsoleReader.readLine(); System.out.print("Enter eleve name: "); eleveName
		 * = brConsoleReader.readLine(); Book book = new Book();
		 * book.setName(coursName); //Eleve eleve = new Eleve();
		 * eleve.setNom(eleveName); //Set<Eleve> eleves = new HashSet<Eleve>(); //
		 * eleves.add(eleve); // cours.setEleve(eleves);
		 * 
		 * //bean.addCours(book); } else if (choice == 2) { break; } }
		 * 
		 * List<Book> coursList = bean.getCours();
		 * System.out.println("Book(s) entered so far: " + coursList.size()); int i = 0;
		 * for (Cours cours : coursList) { System.out.println((i + 1) + ". " +
		 * cours.getName()); System.out.print("Author: ");
		 * 
		 * Eleve[] eleves = (Eleve[]) cours.getEleves().toArray(); for (int j = 0; j <
		 * eleves.length; j++) { System.out.println(eleves[j]); } i++;
		 * 
		 * } } catch (Exception e) { System.out.println(e.getMessage());
		 * e.printStackTrace(); } finally { try { if (brConsoleReader != null) {
		 * brConsoleReader.close(); } } catch (IOException ex) {
		 * System.out.println(ex.getMessage()); } } }
		 */
	}
}