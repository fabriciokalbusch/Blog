package br.unisul.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.unisul.model.Usuario;

public class UsuarioDAO extends GenericDAO {

	public void saveUsuario(Usuario usuario) {
		save(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		update(usuario);
	}

	public Usuario findById(Long id) {
		criaEntityManager();
		return entityManager.find(Usuario.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		criaEntityManager();
		try {
			Query query = entityManager.createQuery("from Usuario as usuario where usuario.tpUsuario = :tpUsuario order by id", Usuario.class);
			query.setParameter("tpUsuario", Usuario.TP_USUARIO_MODERADOR);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	public void deleteAutor(Long id) {
		try {
			EntityTransaction transacao = getTransacao();
			try {
				transacao.begin();
				Usuario usuario = entityManager.find(Usuario.class, id);
				entityManager.remove(usuario);
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

	@SuppressWarnings("unchecked")
	public List<Usuario> findByNomeELogin(String nome, String login) {
		criaEntityManager();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("from Usuario as usuario where usuario.tpUsuario = :tpUsuario ");
			if (nome != null && !nome.isEmpty()) {
				sql.append(" and usuario.nome like :nome ");
			}
			if (login != null && !login.isEmpty()) {
				sql.append(" and usuario.login like :login ");
			}
			sql.append("order by id");
			Query query = entityManager.createQuery(sql.toString(), Usuario.class);
			query.setParameter("tpUsuario", Usuario.TP_USUARIO_MODERADOR);
			if (nome != null && !nome.isEmpty()) {
				query.setParameter("nome", "%" + nome + "%");
			}
			if (login != null && !login.isEmpty()) {
				query.setParameter("login", "%" + login + "%");
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
}
