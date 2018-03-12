package ch.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import ch.myapp.domain.dao.DAOFactory;
import ch.myapp.domain.dao.EleveDAO;
import ch.myapp.domain.dao.EleveDAOImpl;
import ch.myapp.domain.model.BFSBatiment;
import ch.myapp.domain.model.Cours;
import ch.myapp.domain.model.Eleve;
import ch.myapp.domain.model.Plz;

/**
 * Session Bean implementation class MySessionBean
 * 
 * @author Francky
 *
 */

@Stateless
public class MySessionBean implements MySessionBeanRemote, MySessionBeanLocal, Serializable {

	// Injection EJB (Session Bean Stateless)
	// @EJB(name = "eleveDAO")

	private static final long serialVersionUID = 1L;

	public List<Eleve> listeEleves() {

		return DAOFactory.getEleveDAO().listEleves();

	}

	public List<Plz> listPlz() {

		return DAOFactory.getPlzDAO().listPlz();

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
	
	public Plz createPLz(Plz plz) {
		Plz p = DAOFactory.getPlzDAO().create(plz);
		return p;
	}
	
	public int deleteAllPlz(){
		int tot = DAOFactory.getPlzDAO().deleteAllPlz();
		return tot;
	}
	
	public List<BFSBatiment> listBfs() {

		return DAOFactory.getBfsDAO().listBfs();

	}

}