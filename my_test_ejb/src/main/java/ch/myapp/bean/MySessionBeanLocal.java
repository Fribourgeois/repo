package ch.myapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import ch.myapp.domain.dao.DAOFactory;
import ch.myapp.domain.model.BFSBatiment;
import ch.myapp.domain.model.Cours;
import ch.myapp.domain.model.Eleve;
import ch.myapp.domain.model.Plz;

@Local
public interface MySessionBeanLocal extends Serializable {

	Eleve findEleveById(Long id);

	Eleve updateEleve(Eleve eleve);

	void deleteEleve(Eleve eleve);

	Eleve createEleve(Eleve eleve);

	List<Eleve> listeEleves();

	List<Plz> listPlz();

	Plz createPLz(Plz plz);

	int deleteAllPlz();
	
	List<BFSBatiment> listBfs();
}