package ch.myapp.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.domain.model.Eleve;

public class EleveDAOImpl implements EleveDAO {

	@PersistenceContext(unitName = "myPU", synchronization = SynchronizationType.UNSYNCHRONIZED, type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public EleveDAOImpl() {

		this.em = ConnectionEntityManager.getInstance();
	}

	final static Logger log = LogManager.getLogger(EleveDAOImpl.class);

	public List<Eleve> listEleves() {
		em.getEntityManagerFactory().getCache().evictAll();
		List<Eleve> results = em.createQuery("SELECT c FROM Eleve c").getResultList();
		log.info("---> eleve list  size " + results.size());

		return results;
	}

	@Override
	public Eleve find(Long id) {
		log.info("---> eleve find : " + id);
		Eleve e = em.find(Eleve.class, id);
		e.getCours().size();
		return e;
	}

	@Override
	public Eleve create(Eleve obj) {
		em.persist(obj);
		return obj;
	}

	@Override
	public Eleve update(Eleve obj) {
		em.merge(obj);
		return obj;

	}

	@Override
	public void delete(Eleve e) {
		em.remove(e);

	}

}
