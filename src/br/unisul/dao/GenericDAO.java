package br.unisul.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.unisul.util.PersistenceManager;

public class GenericDAO {

	protected EntityManager entityManager;

	protected EntityTransaction getTransacao() {
		criaEntityManager();
		return this.entityManager.getTransaction();
	}

	protected void criaEntityManager() {
		this.entityManager = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	protected Object save(Object object) {
		try {
			EntityTransaction transacao = getTransacao();
			try {
				transacao.begin();
				entityManager.persist(object);
				transacao.commit();
			} finally {
				if (transacao.isActive()) {
					transacao.rollback();
				}
			}
		} finally {
			entityManager.close();
		}
		return object;
	}
	
	protected Object update(Object object) {
		try {
			EntityTransaction transacao = getTransacao();
			try {
				transacao.begin();
				entityManager.merge(object);
				transacao.commit();
			} finally {
				if (transacao.isActive()) {
					transacao.rollback();
				}
			}
		} finally {
			entityManager.close();
		}
		return object;
	}
	
}
