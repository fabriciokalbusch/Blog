package br.unisul.dao;

import java.util.List;

import javax.persistence.EntityTransaction;

import br.unisul.model.Autor;

public class AutorDAO extends GenericDAO {
	
	public void saveAutor(Autor autor) {
		save(autor);
	}

	public List<Autor> findAll() {
		criaEntityManager();
		try {
			return entityManager.createQuery("from Autor order by id", Autor.class).getResultList();
		} finally {
			entityManager.close();
		}
	}

	public Autor findById(Long id) {
		criaEntityManager();
		return entityManager.find(Autor.class, id);
	}
	
	public Autor findByIdUsuario(Long id) {
		criaEntityManager();
		try {
			return entityManager.createQuery("from Autor as autor where autor.usuario.id = :id order by id", Autor.class).setParameter("id", id).getSingleResult();
		} finally {
			entityManager.close();
		}
	}

	public void updateAutor(Autor autor) {
		update(autor);
	}
	
	public void deleteAutor(Long id) {
		try {
			EntityTransaction transacao = getTransacao();
			try {
				transacao.begin();
				Autor autor = entityManager.find(Autor.class, id);
				entityManager.remove(autor.getUsuario());
				entityManager.remove(autor);
				transacao.commit();
			} finally {
				if (transacao.isActive()) {
					transacao.rollback();
				}
			}
		} finally {
			entityManager.close();
		}
	}

}