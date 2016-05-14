package br.unisul.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.LoginDAO;
import br.unisul.model.Usuario;
import br.unisul.servlet.ServletBase;

@WebServlet("/login")
public class LoginServlet extends ServletBase {
	
	public static final String CHAVE_USUARIO_LOGADO = "usuarioLogado";
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		realizaLogout(request);
		
		if (request.getSession().getAttribute(CHAVE_USUARIO_LOGADO) != null) {
			redirecionaParaPagina(request, response, "/home.jsp");
		} else {
			redirecionaParaPagina(request, response, "/login.jsp");
		}
	}

	private void realizaLogout(HttpServletRequest request) {
		if ("true".equals(request.getParameter("logout"))) {
			request.getSession().removeAttribute(CHAVE_USUARIO_LOGADO);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
	
		Usuario usuario = consultaUsuarioParaLogar(login, senha);
		
		if (usuario != null) {
			request.getSession().setAttribute(CHAVE_USUARIO_LOGADO, usuario);
			redirecionaParaPagina(request, response, "/home.jsp");
		} else {
			mostraMensagemInformacao(request, "<Strong>Informação</strong><br>Login ou senha inválido.");
			redirecionaParaPagina(request, response, "/login.jsp");
		}
	}

	private Usuario consultaUsuarioParaLogar(String login, String senha) {
		LoginDAO dao = new LoginDAO();
		Usuario usuario = dao.findUsuarioByLoginESenha(login, senha);
		return usuario;
	}
	

}
