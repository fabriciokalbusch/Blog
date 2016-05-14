package br.unisul.servlet.publico;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.ComentarioDAO;
import br.unisul.dao.PostagemDAO;
import br.unisul.model.Comentario;
import br.unisul.model.Postagem;
import br.unisul.servlet.ServletBase;

@WebServlet("/comentario")
public class ComentarioServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Comentario comentario = new Comentario();
		comentario.setNome(request.getParameter("nome"));
		comentario.setDescricao(request.getParameter("comentario"));
		comentario.setDataComentario(new Date());
		comentario.setPostagem(consultaPostagem(Long.valueOf(request.getParameter("idPostagem"))));
		
		ComentarioDAO dao = new ComentarioDAO();
		dao.salvaComentario(comentario);
		
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(String.valueOf(comentario.getId())); 
	}

	private Postagem consultaPostagem(Long idPostagem) {
		PostagemDAO dao = new PostagemDAO();
		return dao.findById(idPostagem);
	}

}
