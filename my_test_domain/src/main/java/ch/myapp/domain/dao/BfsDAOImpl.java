package ch.myapp.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.domain.model.BFSBatiment;

public class BfsDAOImpl implements BfsDAO {

	@PersistenceContext(unitName = "myPU", synchronization = SynchronizationType.UNSYNCHRONIZED, type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public BfsDAOImpl() {

		this.em = ConnectionEntityManager.getInstance();
	}

	final static Logger log = LogManager.getLogger(BfsDAOImpl.class);

	public List<BFSBatiment> listBfs() {
		em.getEntityManagerFactory().getCache().evictAll();
		List<BFSBatiment> results = em.createQuery("SELECT b FROM BFSBatiment b").setMaxResults(5000).getResultList();
		log.info("---> BFS list  size " + results.size());

		return results;
	}

	@Override
	public BFSBatiment create(BFSBatiment obj) {
		em.joinTransaction();
		em.persist(obj);

		return obj;
	}

	public int deleteAllBfs() {
		em.joinTransaction();
		int deletedCount = em.createQuery("DELETE FROM BFSBatiment").executeUpdate();
		return deletedCount;
	}

}
