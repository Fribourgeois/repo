package ch.myapp.domain.dao;

public interface DAOFactory {

	public static EleveDAO getEleveDAO() {
		return new EleveDAOImpl();
	}

}
