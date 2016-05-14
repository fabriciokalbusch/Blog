package br.unisul.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.unisul.model.Postagem;
import br.unisul.model.Usuario;

public class PostagemDAO extends GenericDAO {

	public void savePostagem(Postagem postagem) {
		save(postagem);
	}

	public void atualizaPostagem(Postagem postagem) {
		update(postagem);
	}

	public Postagem findById(Long id) {
		criaEntityManager();
		return entityManager.find(Postagem.class, id);
		
	}

	@SuppressWarnings("unchecked")
	public List<Postagem> findPostagemPorUsuario(Usuario usuario) {
		criaEntityManager();
		try {
			Query query = entityManager.createQuery("from Postagem as post where post.usuario.id = :id order by dataPostagem DESC", Postagem.class);
			query.setParameter("id", usuario.getId());
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	public List<Postagem> findAll() {
		criaEntityManager();
		try {
			return entityManager.createQuery("from Postagem order by dataPostagem DESC", Postagem.class).getResultList();
		} finally {
			entityManager.close();
		}
	}

	public void deletePostagem(Long id) {
		try {
			EntityTransaction transacao = getTransacao();
			try {
				transacao.begin();
				Postagem postagem = entityManager.find(Postagem.class, id);
				entityManager.remove(postagem);
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
			Query query = entityManager.createQuery("from Postagem as post where post.titulo like :titulo order by dataPostagem DESC", Postagem.class);
			query.setParameter("titulo", "%" + procurar + "%");
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

}
