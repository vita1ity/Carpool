package org.crama.carpool.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.admin.service.AccountServiceAdmin;
import org.crama.carpool.model.Account;

@WebServlet("/admin/accounts")
public class UnverifiedAccountsServlet extends HttpServlet {

	private static final long serialVersionUID = -8782042325448227868L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountServiceAdmin accountService = AccountServiceAdmin.getInstance();
		List<Account> unverifiedAccounts = accountService.getUnverifiedAccounts();
		request.setAttribute("accounts", unverifiedAccounts);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/accounts.jsp");
	    rd.forward(request, response);
	}
	
}
