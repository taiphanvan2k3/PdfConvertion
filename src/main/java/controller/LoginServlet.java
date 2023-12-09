package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BEAN.User;
import model.BO.LoginBO;
import utils.Utils;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		switch (action) {
		case "check-login": {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			this.checkLogin(request, response, username, password);
			break;
		}
		case "sign-up":
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			this.signUp(request, response, username, password);
			break;
		case "invalidate-session":
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("login-status");
			break;
		case "logout":
			this.logout(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		User user = (new LoginBO()).checkLogin(username, password);
		if (user != null) {
			request.getSession().setAttribute("login-status", true);
		} else {
			request.getSession().setAttribute("login-status", false);
		}
		request.getSession().setAttribute("username", username);
		Utils.redirectToPage(request, response, "/index.jsp");
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		boolean status = (new LoginBO()).createAccount(username, password);
		if (status) {
			request.getSession().setAttribute("username", username);
		}
		request.getSession().setAttribute("signup-status", status);
		Utils.redirectToPage(request, response, "/index.jsp");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("login-status");
		request.getSession().removeAttribute("signup-status");
		Utils.redirectToPage(request, response, "/index.jsp");
	}
}
