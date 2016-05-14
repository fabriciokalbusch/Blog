package br.unisul.servlet.publico;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.AutorDAO;
import br.unisul.dao.PostagemDAO;
import br.unisul.model.Autor;
import br.unisul.model.Postagem;
import br.unisul.servlet.ServletBase;

@WebServlet("/postagem")
public class PostagemServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));

		Postagem postagem = configuraPostagem(request, id);
		configuraAutor(request, postagem);
		redirecionaParaPagina(request, response, "/postagem.jsp");
	}

	private Postagem configuraPostagem(HttpServletRequest request, Long id) {
		PostagemDAO postagemDAO = new PostagemDAO();
		Postagem postagem = postagemDAO.findById(id);
		request.setAttribute("postagem", postagem);
		return postagem;
	}
	
	private void configuraAutor(HttpServletRequest request, Postagem postagem) {
		AutorDAO dao = new AutorDAO();
		Autor autor = dao.findByIdUsuario(postagem.getUsuario().getId());
		request.setAttribute("autor", autor);
	}
	
}
