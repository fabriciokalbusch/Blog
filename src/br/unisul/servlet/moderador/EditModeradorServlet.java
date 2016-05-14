package br.unisul.servlet.moderador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.UsuarioDAO;
import br.unisul.model.Usuario;
import br.unisul.servlet.ServletBase;

@WebServlet("/editModerador")
public class EditModeradorServlet extends ServletBase {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		UsuarioDAO dao = new UsuarioDAO();
		Usuario moderador = dao.findById(id);
		request.setAttribute("usuario", moderador);
		request.setAttribute("action", "editModerador");
		redirecionaParaPagina(request, response, "/cadModerador.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario  moderador = recuperaParametrosEPopulaEntidade(request);
		atualizaModerador(moderador);
		request.setAttribute("usuario", moderador);
		request.setAttribute("action", "editModerador");
		mostraMensagemSucessoUpdate(request);
		redirecionaParaPagina(request, response, "/cadModerador.jsp");
	}
	
	private void atualizaModerador(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.updateUsuario(usuario);
	}

	private Usuario recuperaParametrosEPopulaEntidade(HttpServletRequest request) {
		Usuario usuario = new Usuario();
		usuario.setId(Long.valueOf(request.getParameter("id")));
		usuario.setNome(request.getParameter("moderador"));
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTpUsuarioModerador();
		return usuario;
	}
	
}
