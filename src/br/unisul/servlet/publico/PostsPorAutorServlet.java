package br.unisul.servlet.publico;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.AutorDAO;
import br.unisul.dao.PostagemDAO;
import br.unisul.model.Autor;
import br.unisul.model.Postagem;
import br.unisul.servlet.ServletBase;

@WebServlet("/postsPorAutor")
public class PostsPorAutorServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		Autor autor = configuraAutor(request, id);
		configuraPostagens(request, autor);
		redirecionaParaPagina(request, response, "/postsPorAutor.jsp");
	}
	
	private Autor configuraAutor(HttpServletRequest request, Long id) {
		AutorDAO dao = new AutorDAO();
		Autor autor = dao.findByIdUsuario(id);
		request.setAttribute("autor", autor);
		return autor;
	}

	private void configuraPostagens(HttpServletRequest request, Autor autor) {
		PostagemDAO postagemDAO = new PostagemDAO();
		List<Postagem> postagens = postagemDAO.findPostagemPorUsuario(autor.getUsuario());
		request.setAttribute("postagens", postagens);
	}

}
