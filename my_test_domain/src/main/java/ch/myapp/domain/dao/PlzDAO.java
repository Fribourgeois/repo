package ch.myapp.domain.dao;

import java.util.List;

import ch.myapp.domain.model.Plz;

public interface PlzDAO {

	public List<Plz> listPlz();

	public Plz create(Plz plz);

	public int deleteAllPlz();

}
