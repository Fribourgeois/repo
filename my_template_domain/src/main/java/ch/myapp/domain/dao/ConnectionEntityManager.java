package ch.myapp.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionEntityManager {

	private static EntityManagerFactory emf;
	

	private static EntityManager em;

	final static Logger logger = LogManager.getLogger(ConnectionEntityManager.class);

	/**
	 * Méthode qui va nous retourner notre instance et la créer si elle n'existe
	 * pas...
	 * 
	 * @return
	 */
	public static EntityManager getInstance() {
		if (em == null) {
			emf = Persistence.createEntityManagerFactory("myPU");
			em = emf.createEntityManager();
				
			logger.info("----- Create Entity Manager ");
		}
		return em;
	}
}