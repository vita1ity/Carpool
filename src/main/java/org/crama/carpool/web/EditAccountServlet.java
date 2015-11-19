package org.crama.carpool.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.model.Account;
import org.crama.carpool.model.Address;
import org.crama.carpool.service.AccountService;

@WebServlet("/user/edit")
public class EditAccountServlet extends HttpServlet {

	private static final long serialVersionUID = -189269759144506305L;
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		//address info
		String addressIdStr = request.getParameter("addressId");
		String country = request.getParameter("country");
		String town = request.getParameter("town");
		String street = request.getParameter("street");
		String houseNumber = request.getParameter("houseNumber");
		
		Address address = null;
		if (addressIdStr.equals("")) {
			address = new Address(country, town, street, houseNumber);
		}
		else {
			int addressId = Integer.parseInt(addressIdStr);
			address = new Address(addressId, country, town, street, houseNumber);
		}
		Account account = new Account(userId, username, fullName, email, password, phone, "user");
		account.setIsApproved(false);
		account.setAddress(address);
		
		AccountService accountService = AccountService.getInstance();
		accountService.editAccount(account);
		
		response.sendRedirect("/carpool/user/account");
		
		
	}
}
