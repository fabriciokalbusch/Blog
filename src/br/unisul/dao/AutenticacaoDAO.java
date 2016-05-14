package br.unisul.dao;

import java.util.List;

import br.unisul.model.Tela;

public class AutenticacaoDAO extends GenericDAO {

	public List<Tela> findAll() {
		criaEntityManager();
		try {
			return entityManager.createQuery("from Tela order by id", Tela.class).getResultList();
		} finally {
			entityManager.close();
		}
	}
	
}
