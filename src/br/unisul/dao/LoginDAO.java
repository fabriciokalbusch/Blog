package br.unisul.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unisul.model.Usuario;

public class LoginDAO extends GenericDAO {

	public Usuario findUsuarioByLoginESenha(String login, String senha) {
		criaEntityManager();
		try {
			Query query = entityManager.createQuery("from Usuario as usuario where usuario.login = :login and usuario.senha = :senha order by id", Usuario.class);
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}
	
}
