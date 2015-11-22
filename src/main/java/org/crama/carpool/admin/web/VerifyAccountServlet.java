package org.crama.carpool.admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.model.Account;
import org.crama.carpool.service.AccountService;

@WebServlet("/admin/accounts/verify/*")
public class VerifyAccountServlet extends HttpServlet {

	private static final long serialVersionUID = -4162511455680327349L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		int userId = 0;
		
		if (pathInfo.length == 2) {
			userId = Integer.parseInt(pathInfo[1]);
			
			AccountService accountService = AccountService.getInstance();
			Account account = accountService.getFullAccount(userId);
			account.setIsApproved(true);
			accountService.editAccount(account);
			response.sendRedirect(request.getContextPath() + "/admin/accounts");
			
		}
	}
	
}
