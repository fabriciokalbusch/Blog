package br.unisul.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.unisul.model.Postagem;
import br.unisul.model.Tag;

public class TagDAO extends GenericDAO {

	public void salvaTodasAsTags(List<Tag> tags) {
		for (Tag tag : tags) {
			save(tag);
		}
	}

	@SuppressWarnings("unchecked")
	public void excluiTagsDestaPostagem(Long id) {
		try {
			EntityTransaction transacao = getTransacao();
			try {
				transacao.begin();
				Query query = entityManager.createQuery("from Tag as tag where tag.postagem.id = :id", Tag.class);
				query.setParameter("id", id);
				List<Tag> tags = query.getResultList();
				for (Tag tag : tags) {
					entityManager.remove(tag);
				}
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
	public List<Postagem> findBySearch(String procurar) {
		criaEntityManager();
		try {
			Query query = entityManager.createQuery("from Tag as tag where tag.descricao = :descricao", Tag.class);
			query.setParameter("descricao", procurar);
			List<Tag> tags = query.getResultList();
			Set<Postagem> conjuntoPostagem = new HashSet<Postagem>();
			for (Tag tag : tags) {
				conjuntoPostagem.add(tag.getPostagem());
			}
			return new ArrayList<Postagem> (conjuntoPostagem);
		} finally {
			entityManager.close();
		}
	}


	
	
}
