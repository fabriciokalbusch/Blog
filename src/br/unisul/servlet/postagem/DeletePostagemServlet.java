package br.unisul.servlet.postagem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.PostagemDAO;
import br.unisul.dao.TagDAO;
import br.unisul.model.Postagem;
import br.unisul.servlet.ServletBase;

@WebServlet("/deletePostagem")
public class DeletePostagemServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		TagDAO daoTag = new TagDAO();
		daoTag.excluiTagsDestaPostagem(id);
		PostagemDAO daoPostagem = new PostagemDAO();
		daoPostagem.deletePostagem(id);
		List<Postagem> postagens = daoPostagem.findPostagemPorUsuario(getUsuarioLogado(request));
		request.setAttribute("postagens", postagens);
		mostraMensagemSucessoDelete(request);
		redirecionaParaPagina(request, response, "/listPostagens.jsp");
	}

}
