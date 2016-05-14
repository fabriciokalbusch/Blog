package br.unisul.servlet.postagem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.PostagemDAO;
import br.unisul.model.Postagem;
import br.unisul.servlet.ServletBase;

@WebServlet("/listPostagem")
public class ListPostagemServlet extends ServletBase{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostagemDAO dao = new PostagemDAO();
		List<Postagem> postagens = dao.findPostagemPorUsuario(getUsuarioLogado(request));
		request.setAttribute("postagens", postagens);
		redirecionaParaPagina(request, response, "/listPostagens.jsp");
	}
	
}
