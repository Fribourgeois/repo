package ch.myapp.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

public class WriteToDb {
	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = null;
		entityManagerFactory = Persistence.createEntityManagerFactory( "myPU" );

		 EntityManager entityManager = entityManagerFactory.createEntityManager();
	        entityManager.getTransaction().begin();
	        // Do stuff...
	        entityManager.getTransaction().commit();
	        entityManager.close();
		
	}

	private static void testDataSource(String dbType) {

	}
}
