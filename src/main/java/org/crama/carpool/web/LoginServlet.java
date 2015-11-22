package org.crama.carpool.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crama.carpool.model.Account;
import org.crama.carpool.service.AccountService;
import org.crama.carpool.validation.AccountValidator;

@WebServlet("/login-signup")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = -4669636443414229868L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/loginSignup.jsp");
	      rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AccountService accountService = AccountService.getInstance();
		String errorMessage = AccountValidator.validateLogin(accountService, username, password);
		
		request.setAttribute("errorMessageLogin", errorMessage);
		if (errorMessage != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else {
			
			Account loginUser = accountService.getAccount(username);
			//admin 
			if (loginUser.getRole().equals("admin")) {
				HttpSession session = request.getSession(true); 
				session.setAttribute("admin", loginUser);
				
				response.sendRedirect(request.getContextPath() + "/admin/accounts");
			}
			//user
			else {
				HttpSession session = request.getSession(true); 
				session.setAttribute("user", loginUser);
				
				response.sendRedirect(request.getContextPath() + "/route/manage-carpool");
			}
			
			
		}
	}
	
}
