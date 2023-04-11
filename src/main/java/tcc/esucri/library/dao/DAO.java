package tcc.esucri.library.dao;

import java.util.List;

import javax.persistence.Query;

import tcc.esucri.library.database.DatabaseManager;

@SuppressWarnings("unchecked")
abstract public class DAO<T> {

	public void add(T object) {
		DatabaseManager.getEntity().getTransaction().begin();
		DatabaseManager.getEntity().persist(object);
		DatabaseManager.getEntity().getTransaction().commit();
	}

	public void update(T object) {
		DatabaseManager.getEntity().getTransaction().begin();
		DatabaseManager.getEntity().merge(object);
		DatabaseManager.getEntity().getTransaction().commit();
	}

	public void remove(T object) {
		DatabaseManager.getEntity().getTransaction().begin();
		DatabaseManager.getEntity().remove(object);
		DatabaseManager.getEntity().getTransaction().commit();
	}

	protected T get(Class<T> classType, int id) {
		return (DatabaseManager.getEntity().find(classType, id));
	}

	protected List<T> getAll(String HQL) {
		Query query = DatabaseManager.getEntity().createQuery(HQL);
		return query.getResultList();
	}

	protected List<T> getAll(String HQL, int maxResult) {
		Query query = DatabaseManager.getEntity().createQuery(HQL).setMaxResults(maxResult);
		return query.getResultList();
	}

	protected List<T> getAll(String HQL, Object... args) {
		Query query = DatabaseManager.getEntity().createQuery(HQL);
		for (int i = 0; i < args.length; i++) {
			query.setParameter((i + 1), args[i]);
		}
		return query.getResultList();
	}

	protected T find(String HQL) {
		Query query = DatabaseManager.getEntity().createQuery(HQL);
		return (T) query.getSingleResult();
	}

	protected T find(String HQL, Object... args) {
		Query query = DatabaseManager.getEntity().createQuery(HQL);
		for (int i = 0; i < args.length; i++) {
			query.setParameter((i + 1), args[i]);
		}
		return (T) query.getSingleResult();
	}

}
