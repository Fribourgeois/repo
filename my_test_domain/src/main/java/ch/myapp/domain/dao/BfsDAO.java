package ch.myapp.domain.dao;

import java.util.List;

import ch.myapp.domain.model.BFSBatiment;



public interface BfsDAO {

	public List<BFSBatiment> listBfs();

	public BFSBatiment create(BFSBatiment bfs);

	public int deleteAllBfs();

}
