package ch.myapp.domain.dao;

public interface DAOFactory {

	public static EleveDAO getEleveDAO() {
		return new EleveDAOImpl();

	}

	public static PlzDAO getPlzDAO() {
		return new PlzDAOImpl();

	}
	
	public static BfsDAO getBfsDAO() {
		return new BfsDAOImpl();

	}

}
