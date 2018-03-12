package ch.myapp.domain.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ch.myapp.domain.model.Eleve;

@Stateful
public class EleveDAOImpl implements EleveDAO {

	@PersistenceContext(unitName = "myPU")
	private EntityManager em;

	public EleveDAOImpl() {

		this.em = ConnectionEntityManager.getInstance();
	}

	final static Logger logger = LogManager.getLogger(EleveDAOImpl.class);

	public List<Eleve> listEleves() {

		List<Eleve> results = em.createQuery("SELECT c FROM Eleve c").getResultList();
		logger.info("---> eleve list  size " + results.size());

		em.joinTransaction();
		return results;
	}

	@Override
	public Eleve find(Long id) {
		logger.info("---> eleve find : " + id);
		Eleve e = em.find(Eleve.class, id);
		e.getCours().size();
		return e;
	}

	@Override
	public Eleve create(Eleve obj) {
		em.joinTransaction();
		em.persist(obj);
		em.flush();
		return obj;
	}

	@Override
	public Eleve update(Eleve obj) {
		em.joinTransaction();
		em.merge(obj);
		em.flush();
		return obj;

	}

	@Override
	public void delete(Eleve e) {
		em.joinTransaction();
		em.remove(e);
		em.flush();

	}

}
