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
				showNewForm(request, response);
				break;
			case "/insere":
				insertUser(request, response);
				break;
			case "/deleta":
				deleteUser(request, response);
				break;
			case "/editar":
				showEditForm(request, response);
				break;
			case "/alterar":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Usuario> listUser = usuarioDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-lista.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("usario-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario existingUser = usuarioDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("usuario-form.jsp");
		request.setAttribute("usuario", existingUser);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");
		Usuario novoUsuario = new Usuario(nome, email, pais);
		usuarioDAO.insertUser(novoUsuario);
		response.sendRedirect("lista");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String pais = request.getParameter("pais");

		Usuario book = new Usuario(id, nome, email, pais);
		usuarioDAO.updateUser(book);
		response.sendRedirect("lista");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		usuarioDAO.deleteUser(id);
		response.sendRedirect("lista");

	}

}
