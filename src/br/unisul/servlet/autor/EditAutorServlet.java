package br.unisul.servlet.autor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.AutorDAO;
import br.unisul.dao.UsuarioDAO;
import br.unisul.model.Autor;
import br.unisul.model.Usuario;
import br.unisul.servlet.ServletBase;

@WebServlet("/editAutor")
public class EditAutorServlet extends ServletBase {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		AutorDAO dao = new AutorDAO();
		Autor autor = dao.findById(id);
		request.setAttribute("autor", autor);
		request.setAttribute("action", "editAutor");
		redirecionaParaPagina(request, response, "/cadAutor.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Autor autor = recuperaParametrosEPopulaEntidade(request);
		atualizaAutor(autor);
		request.setAttribute("autor", autor);
		request.setAttribute("action", "editAutor");
		mostraMensagemSucessoUpdate(request);
		redirecionaParaPagina(request, response, "/cadAutor.jsp");
	}
	
	private void atualizaAutor(Autor autor) {
		AutorDAO dao = new AutorDAO();
		dao.updateAutor(autor);
		UsuarioDAO daoUser = new UsuarioDAO();
		daoUser.updateUsuario(autor.getUsuario());
	}

	private Autor recuperaParametrosEPopulaEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario.setId(Long.valueOf(request.getParameter("idUsuario")));
		usuario.setNome(request.getParameter("autor"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTpUsuarioAutor();
		Autor autor = new Autor();
		autor.setId(Long.valueOf(request.getParameter("id")));
		autor.setPerfil(request.getParameter("perfil"));
		autor.setUsuario(usuario);
		return autor;
	}

}
