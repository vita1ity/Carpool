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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7302566429048671209L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		AccountService accountService = AccountService.getInstance();
		String errorMessage = AccountValidator.validateRegistration(accountService, username, password, confirmPassword, email);
		
		request.setAttribute("errorMessageReg", errorMessage);
		if (errorMessage != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/loginSignup.jsp");
			rd.forward(request, response);
		}
		else {
			Account newUser = new Account(username, email, password, phone, "user");
			accountService.saveAccount(newUser);
			HttpSession session = request.getSession(true); 
			session.setAttribute("user", newUser);
			
			response.sendRedirect("/carpool/user");
			
		}
	}
}
