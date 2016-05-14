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

@WebServlet("/deleteAutor")
public class DeleteAutorServlet extends ServletBase {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.valueOf(request.getParameter("id"));
		AutorDAO dao = new AutorDAO();
		dao.deleteAutor(id);
		List<Autor> autores = dao.findAll();
		request.setAttribute("autores", autores);
		mostraMensagemSucessoDelete(request);
		redirecionaParaPagina(request, response, "/listAutores.jsp");
	}

}