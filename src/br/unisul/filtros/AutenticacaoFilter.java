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
import br.unisul.servlet.login.LoginServlet;

@WebFilter("/*")
public class AutenticacaoFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}

	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (estaUrlPrecisaDeAutenticacao(request)) {
			if (request.getSession().getAttribute(LoginServlet.CHAVE_USUARIO_LOGADO) == null) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean estaUrlPrecisaDeAutenticacao(HttpServletRequest request) {
		Map<String, Tela> telas = getTelas(request);
		String url = getUrl(request);
		Tela tela = telas.get(url);
		if (tela == null) {
			System.out.println("NAO HA TELA CADASTRADA. -> " + url);
			return false;
		}
		return tela.isPrecisaAutenticacao();
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
