package br.unisul.servlet.autor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.AutorDAO;
import br.unisul.model.Autor;
import br.unisul.model.Usuario;
import br.unisul.servlet.ServletBase;

@WebServlet("/cadAutor")
public class CadAutorServlet extends ServletBase  {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("action", "cadAutor");
		redirecionaParaPagina(request, response, "/cadAutor.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Autor autor = recuperaParametrosEPopulaEntidade(request);
		salvaAutor(autor);
		request.setAttribute("autor", autor);
		request.setAttribute("action", "editAutor");
		mostraMensagemSucesso(request);
		redirecionaParaPagina(request, response, "/cadAutor.jsp");
	}

	private Autor recuperaParametrosEPopulaEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario.setNome(request.getParameter("autor"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTpUsuarioAutor();
		Autor autor = new Autor();
		autor.setPerfil(request.getParameter("perfil"));
		autor.setUsuario(usuario);
		return autor;
	}

	private void salvaAutor(Autor autor) {
		AutorDAO dao = new AutorDAO();
		dao.saveAutor(autor);
	}

}
