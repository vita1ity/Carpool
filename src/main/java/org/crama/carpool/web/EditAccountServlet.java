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
import org.crama.carpool.model.Address;
import org.crama.carpool.service.AccountService;
import org.crama.carpool.validation.AccountValidator;

@WebServlet("/user/account/edit")
public class EditAccountServlet extends HttpServlet {

	private static final long serialVersionUID = -189269759144506305L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("user");
		AccountService accountService = AccountService.getInstance();
		Account account = accountService.getFullAccount(loginUser.getUserId());
		
		request.setAttribute("userInfo", account);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/editAccount.jsp");
	    rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String imageUrl = request.getParameter("imageUrl");
		
		//address info
		String addressIdStr = request.getParameter("addressId");
		String country = request.getParameter("country");
		String town = request.getParameter("town");
		String street = request.getParameter("street");
		String houseNumber = request.getParameter("houseNumber");
		
		String errorMessage = AccountValidator.validateAccount(email);
		
		request.setAttribute("errorMessageReg", errorMessage);
		if (errorMessage != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/editAccount.jsp");
			rd.forward(request, response);
		}
		else {	
		
			Address address = null;
			if (addressIdStr.equals("")) {
				address = new Address(country, town, street, houseNumber);
			}
			else {
				int addressId = Integer.parseInt(addressIdStr);
				address = new Address(addressId, country, town, street, houseNumber);
			}
			Account account = new Account(userId, username, fullName, email, password, phone, "user", imageUrl);
			account.setIsApproved(false);
			account.setAddress(address);
			
			AccountService accountService = AccountService.getInstance();
			accountService.editAccount(account);
			
			response.sendRedirect(request.getContextPath() + "/user/account");
		}
		
	}
}
