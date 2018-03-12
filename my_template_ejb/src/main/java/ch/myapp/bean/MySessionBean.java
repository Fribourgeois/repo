package ch.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import ch.myapp.domain.dao.DAOFactory;
import ch.myapp.domain.dao.EleveDAO;
import ch.myapp.domain.dao.EleveDAOImpl;
import ch.myapp.domain.model.Cours;
import ch.myapp.domain.model.Eleve;

/**
 * Session Bean implementation class MySessionBean
 * 
 * @author Francky
 *
 */

@Stateful
public class MySessionBean implements MySessionBeanRemote, MySessionBeanLocal, Serializable {

	// Injection EJB (Session Bean Stateless)


	private static final long serialVersionUID = 1L;


	public List<Eleve> listeEleves() {

		return DAOFactory.getEleveDAO().listEleves();

	}

	public Eleve findEleveById(Long id) {

		Eleve e = DAOFactory.getEleveDAO().find(id);
		return e;
	}

	public Eleve updateEleve(Eleve eleve) {
		Eleve e = DAOFactory.getEleveDAO().update(eleve);
		return e;
	}

	public void deleteEleve(Eleve e) {
		DAOFactory.getEleveDAO().delete(e);

	}

	public Eleve createEleve(Eleve eleve) {
		Eleve e = DAOFactory.getEleveDAO().create(eleve);
		return e;
	}



}