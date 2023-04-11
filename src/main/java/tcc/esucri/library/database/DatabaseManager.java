package tcc.esucri.library.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseManager {

	private static EntityManager entity = null;

	public static EntityManager getEntity() {
		if (entity == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("LibraryManagerPU");
			entity = factory.createEntityManager();
		}
		return entity;
	}
}
