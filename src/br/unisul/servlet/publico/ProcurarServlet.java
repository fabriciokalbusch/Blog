package br.unisul.servlet.publico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.AutorDAO;
import br.unisul.dao.PostagemDAO;
import br.unisul.dao.TagDAO;
import br.unisul.model.Autor;
import br.unisul.model.Postagem;
import br.unisul.servlet.ServletBase;

@WebServlet("/procurar")
public class ProcurarServlet extends ServletBase {

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
		String procurar = request.getParameter("procurar");
		List<Postagem> postagens = new ArrayList<Postagem>(); 
		PostagemDAO daoPostagem = new PostagemDAO();
		postagens.addAll(daoPostagem.findBySearch(procurar));
		TagDAO daoTag = new TagDAO();
		postagens.addAll(daoTag.findBySearch(procurar));
		if (postagens.isEmpty()) {
			request.setAttribute("mensagem", "Nenhum resultado encontrado para a sua pesquisa.");
		}
		request.setAttribute("postagens", removeDuplicados(postagens));
	}

	private List<Postagem> removeDuplicados(List<Postagem> postagens) {
		Map<Long, Postagem> postagensPorCodigo = new HashMap<Long, Postagem>();
		for (Postagem postagem : postagens) {
			postagensPorCodigo.put(postagem.getId(), postagem);
		}
		return new ArrayList<Postagem>(postagensPorCodigo.values());
	}
	
}