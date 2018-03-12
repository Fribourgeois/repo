package ch.myapp.domain.dao;

import java.util.List;

import ch.myapp.domain.model.Eleve;


public interface EleveDAO {

	public Eleve find(Long id);

	public  List<Eleve> listEleves() ;

	public Eleve update(Eleve eleve);

	public void delete(Eleve eleve);

	public Eleve create(Eleve eleves);
}


