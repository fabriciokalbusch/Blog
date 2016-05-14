package br.unisul.servlet.postagem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.PostagemDAO;
import br.unisul.dao.TagDAO;
import br.unisul.model.Postagem;
import br.unisul.model.Tag;
import br.unisul.servlet.ServletBase;

@WebServlet("/cadPostagem")
public class CadPostagemServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("action", "cadPostagem");
		redirecionaParaPagina(request, response, "/cadPostagem.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Postagem postagem = recuperaParametrosEPopulaEntidade(request);
		salvaPostagem(postagem);
		salvaTags(postagem);
		request.setAttribute("postagem", postagem);
		request.setAttribute("action", "editPostagem");
		mostraMensagemSucesso(request);
		redirecionaParaPagina(request, response, "/cadPostagem.jsp");
	}

	private Postagem recuperaParametrosEPopulaEntidade(HttpServletRequest request) {
		Postagem postagem = new Postagem();
		postagem.setTitulo(request.getParameter("titulo"));
		postagem.setConteudo(request.getParameter("conteudo"));
		postagem.setUsuario(getUsuarioLogado(request));
		postagem.setTags(recuperaTags(request));
		postagem.setDataPostagem(new Date());
		return postagem;
	}

	private List<Tag> recuperaTags(HttpServletRequest request) {
		String campoTag = request.getParameter("tag");
		String[] arrayTag = campoTag.split(";");
		List<Tag> tags = new ArrayList<Tag>();
		for (String descricao : arrayTag) {
			if (descricao.trim().isEmpty()) {
				continue;
			}
			Tag tag = new Tag();
			tag.setDescricao(descricao.trim());
			tags.add(tag);
		}
		return tags;
	}

	private void salvaPostagem(Postagem postagem) {
		PostagemDAO dao = new PostagemDAO();
		dao.savePostagem(postagem);
	}

	private void salvaTags(Postagem postagem) {
		populaTagsComAPostagem(postagem);
		TagDAO dao = new TagDAO();
		dao.salvaTodasAsTags(postagem.getTags());
	}

	private void populaTagsComAPostagem(Postagem postagem) {
		for (Tag tag : postagem.getTags()) {
			tag.setPostagem(postagem);
		}
	}
	
}
