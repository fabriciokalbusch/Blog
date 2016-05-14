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

@WebServlet("/index")
public class IndexServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		configuraPostagens(request);
		configuraAutores(request);
		redirecionaParaPagina(request, response, "/index.jsp");
	}

	private void configuraAutores(HttpServletRequest request) {
		AutorDAO dao = new AutorDAO();
		List<Autor> autores = dao.findAll();
		request.setAttribute("autores", autores);
	}

	private void configuraPostagens(HttpServletRequest request) {
		PostagemDAO postagemDAO = new PostagemDAO();
		List<Postagem> postagens = postagemDAO.findAll();
		request.setAttribute("postagens", postagens);
	}

}
