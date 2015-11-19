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

@WebServlet("/user/account")
public class UserAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 3630114796211249585L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("user");
		AccountService accountService = AccountService.getInstance();
		Account account = accountService.getFullAccount(loginUser.getUserId());
		
		request.setAttribute("userInfo", account);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/userAccount.jsp");
	    rd.forward(request, response);
	}

	
}
