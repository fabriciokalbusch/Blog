package br.unisul.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.model.Usuario;
import br.unisul.servlet.login.LoginServlet;

public class ServletBase extends HttpServlet {
	
	public static final String CHAVE_MSG_SUCESSO = "MSG_SUCESSO";
	public static final String CHAVE_MSG_INFORMACAO = "MSG_INFORMACAO";
	
	private static final long serialVersionUID = 1L;

	protected void mostraMensagemSucesso(HttpServletRequest request, String mensagem) {
		request.setAttribute(CHAVE_MSG_SUCESSO, mensagem);
	}
	
	protected void mostraMensagemSucesso(HttpServletRequest request) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<strong>Sucesso!</strong>");
		mensagem.append("<br>");
		mensagem.append("Os dados foram cadastrados com sucesso.");
		mostraMensagemSucesso(request, mensagem.toString());
	}
	
	protected void mostraMensagemSucessoUpdate(HttpServletRequest request) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<strong>Sucesso!</strong>");
		mensagem.append("<br>");
		mensagem.append("Os dados foram atualizados com sucesso.");
		mostraMensagemSucesso(request, mensagem.toString());
	}
	
	protected void mostraMensagemSucessoDelete(HttpServletRequest request) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("<strong>Sucesso!</strong>");
		mensagem.append("<br>");
		mensagem.append("O registro foi excluido com sucesso.");
		mostraMensagemSucesso(request, mensagem.toString());
	}
	
	protected void mostraMensagemInformacao(HttpServletRequest request, String mensagem) {
		request.setAttribute(CHAVE_MSG_INFORMACAO, mensagem);
	}

	protected void redirecionaParaPagina(HttpServletRequest request, HttpServletResponse response, String pagina) throws ServletException, IOException {
		ServletContext servletContext = getServletConfig().getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pagina);
		requestDispatcher.forward(request, response);
	}
	
	protected Usuario getUsuarioLogado(HttpServletRequest request) {
		return (Usuario) request.getSession().getAttribute(LoginServlet.CHAVE_USUARIO_LOGADO);
	}
	
}
