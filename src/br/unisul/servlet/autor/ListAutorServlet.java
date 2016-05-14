package br.unisul.servlet.autor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisul.dao.AutorDAO;
import br.unisul.model.Autor;
import br.unisul.servlet.ServletBase;

@WebServlet("/listAutor")
public class ListAutorServlet extends ServletBase {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AutorDAO dao = new AutorDAO();
		List<Autor> autores = dao.findAll();
		request.setAttribute("autores", autores);
		redirecionaParaPagina(request, response, "/listAutores.jsp");
	}
	
}
