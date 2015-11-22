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
import org.crama.carpool.model.Vehicle;

@WebServlet("/admin/vehicles")
public class UnverifiedVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = -5520559499717580070L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountServiceAdmin accountService = AccountServiceAdmin.getInstance();
		List<Vehicle> unverifiedVehicles = accountService.getUnverifiedVehicles();
		request.setAttribute("vehicles", unverifiedVehicles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/vehicles.jsp");
	    rd.forward(request, response);
	}
}
