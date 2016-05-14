package br.unisul.filtros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.unisul.dao.AutenticacaoDAO;
import br.unisul.model.Tela;
import br.unisul.model.Usuario;
import br.unisul.servlet.ServletBase;
import br.unisul.servlet.login.LoginServlet;

@WebFilter("/*")
public class AutorizacaoFilter implements Filter {

	public void destroy() {}
	public void init(FilterConfig arg0) throws ServletException {}
	
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getSession().getAttribute(LoginServlet.CHAVE_USUARIO_LOGADO) == null) {
			chain.doFilter(request, response);
			return;
		}
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute(LoginServlet.CHAVE_USUARIO_LOGADO);
		Tela tela = getTelas(request).get(getUrl(request));
		
		if (possuiAutorizacao(usuarioLogado, tela)) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute(ServletBase.CHAVE_MSG_INFORMACAO, "<strong>Informação</strong><br>Você não possui autorização para acessar a página de \"" + tela.getNome() + "\".");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/home");
			dispatcher.forward(request, response);
			return;
		}
		
	}
	private boolean possuiAutorizacao(Usuario usuarioLogado, Tela tela) {
		return tela == null || tela.getTipoUsuario().equals("T") || tela.getTipoUsuario().equals(usuarioLogado.getTpUsuario());
	}

	private String getUrl(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length()).replace("/", "");
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Tela> getTelas(HttpServletRequest request) {
		if (request.getSession().getAttribute("AUTORIZACOES") == null) {
			AutenticacaoDAO dao = new AutenticacaoDAO();
			Map<String, Tela> telas = montaMapPorUrl(dao.findAll());
			request.getSession().setAttribute("AUTORIZACOES", telas);
			return telas;
		}
		return (Map<String, Tela>) request.getSession().getAttribute("AUTORIZACOES");
	}
	
	private Map<String, Tela> montaMapPorUrl(List<Tela> telas) {
		Map<String, Tela> mapTela = new HashMap<String, Tela>();
		for (Tela tela : telas) {
			mapTela.put(tela.getUrl(), tela);
		}
		return mapTela;
	}

}
