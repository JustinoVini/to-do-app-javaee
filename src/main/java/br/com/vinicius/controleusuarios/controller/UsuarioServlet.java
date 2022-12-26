package br.com.vinicius.controleusuarios.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vinicius.controleusuarios.dao.UsuarioDAO;
import br.com.vinicius.controleusuarios.model.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 * 
 * @author Vinicius Justino
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;

	public void init() {
		usuarioDAO = new UsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/novo":
				mostraNovoForms(request, response);
				break;
			case "/insere":
				inserirUsuario(request, response);
				break;
			case "/deleta":
				deletarUsuario(request, response);
				break;
			case "/editar":
				verEditarForms(request, response);
				break;
			case "/alterar":
				alterarUsuario(request, response);
				break;
			default:
				listaUsuarios(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listaUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Usuario> listaUsario = usuarioDAO.selectAllUsers();
		request.setAttribute("listaUsario", listaUsario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-lista.jsp");
		dispatcher.forward(request, response);
	}

	private void mostraNovoForms(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usario-form.jsp");
		dispatcher.forward(request, response);
	}

	private void verEditarForms(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario extingueUsuario = usuarioDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-form.jsp");
		request.setAttribute("usuario", extingueUsuario);
		dispatcher.forward(request, response);

	}

	private void inserirUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		Usuario novoUsuario = new Usuario(nome, email, pais);
		usuarioDAO.insertUser(novoUsuario);
		response.sendRedirect("lista");
	}

	private void alterarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");

		Usuario livro = new Usuario(id, nome, email, pais);
		usuarioDAO.updateUser(livro);
		response.sendRedirect("lista");
	}

	private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		usuarioDAO.deleteUser(id);
		response.sendRedirect("lista");
	}

}
