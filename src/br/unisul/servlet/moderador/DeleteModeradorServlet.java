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

@WebServlet("/deleteModerador")
public class DeleteModeradorServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		UsuarioDAO dao = new UsuarioDAO();
		dao.deleteAutor(id);
		List<Usuario> usuarios = dao.findAll();
		request.setAttribute("moderadores", usuarios);
		mostraMensagemSucessoDelete(request);
		redirecionaParaPagina(request, response, "/listModeradores.jsp");
	}

}
