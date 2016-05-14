package br.unisul.dao;

import br.unisul.model.Comentario;

public class ComentarioDAO extends GenericDAO{

	public void salvaComentario(Comentario comentario) {
		save(comentario);
	}

	
	
}
