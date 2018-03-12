package ch.myapp.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.domain.model.Plz;

public class PlzDAOImpl implements PlzDAO {

	@PersistenceContext(unitName = "myPU", synchronization = SynchronizationType.UNSYNCHRONIZED, type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public PlzDAOImpl() {

		this.em = ConnectionEntityManager.getInstance();
	}

	final static Logger log = LogManager.getLogger(PlzDAOImpl.class);

	public List<Plz> listPlz() {
		em.getEntityManagerFactory().getCache().evictAll();
		List<Plz> results = em.createQuery("SELECT p FROM Plz p").getResultList();
		log.info("---> Plz list  size " + results.size());

		return results;
	}

	@Override
	public Plz create(Plz obj) {
		em.joinTransaction();
		em.persist(obj);

		return obj;
	}

	public int deleteAllPlz() {
		em.joinTransaction();
		int deletedCount = em.createQuery("DELETE FROM Plz").executeUpdate();
		return deletedCount;
	}

}
