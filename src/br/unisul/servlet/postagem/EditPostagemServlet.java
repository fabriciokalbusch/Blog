package br.unisul.servlet.postagem;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/editPostagem")
public class EditPostagemServlet extends ServletBase {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		PostagemDAO dao = new PostagemDAO();
		Postagem postagem = dao.findById(id);
		request.setAttribute("postagem", postagem);
		request.setAttribute("action", "editPostagem");
		redirecionaParaPagina(request, response, "/cadPostagem.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Postagem postagem = recuperaParametrosEPopulaEntidade(request);
		deletaTags(postagem.getId());
		atualizaPostagem(postagem);
		salvaTags(postagem);
		request.setAttribute("postagem", postagem);
		request.setAttribute("action", "editPostagem");
		mostraMensagemSucessoUpdate(request);
		redirecionaParaPagina(request, response, "/cadPostagem.jsp");
	}

	private void deletaTags(Long id) {
		TagDAO dao = new TagDAO();
		dao.excluiTagsDestaPostagem(id);
	}

	private void atualizaPostagem(Postagem postagem) {
		PostagemDAO dao = new PostagemDAO();
		postagem = preparaPostagemParaAtualizacao(dao, postagem);
		dao.atualizaPostagem(postagem);
	}
	
	private Postagem preparaPostagemParaAtualizacao(PostagemDAO dao, Postagem postagem) {
		Postagem postagemDB = dao.findById(postagem.getId());
		postagemDB.setTitulo(postagem.getTitulo());
		postagemDB.setConteudo(postagem.getConteudo());
		postagemDB.setTags(postagem.getTags());
		return postagemDB;
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

	private Postagem recuperaParametrosEPopulaEntidade(HttpServletRequest request) {
		Postagem postagem = new Postagem();
		postagem.setId(Long.valueOf(request.getParameter("id")));
		postagem.setTitulo(request.getParameter("titulo"));
		postagem.setConteudo(request.getParameter("conteudo"));
		postagem.setTags(recuperaTags(request));
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
	
}
