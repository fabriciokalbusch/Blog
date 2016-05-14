package br.unisul.servlet.moderador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.UsuarioDAO;
import br.unisul.model.Usuario;
import br.unisul.servlet.ServletBase;

@WebServlet("/listModerador")
public class ListModeradorServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarios = dao.findAll();
		request.setAttribute("moderadores", usuarios);
		redirecionaParaPagina(request, response, "/listModeradores.jsp");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarios = dao.findByNomeELogin(nome, login);
		request.setAttribute("moderadores", usuarios);
		redirecionaParaPagina(request, response, "/listModeradores.jsp");
	}

}
