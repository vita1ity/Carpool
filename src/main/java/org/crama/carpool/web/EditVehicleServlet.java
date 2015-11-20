package org.crama.carpool.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.model.Vehicle;
import org.crama.carpool.service.AccountService;

@WebServlet("/user/edit-vehicle")
public class EditVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = 1340507093542022498L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String vehicleIdStr = request.getParameter("vehicleId");
		String model = request.getParameter("model");
		String countryEst = request.getParameter("countryEst");
		int year = Integer.parseInt(request.getParameter("year"));
		
		Vehicle vehicle = null;
		if (vehicleIdStr.equals("")) {
			vehicle = new Vehicle(model, countryEst, year);
		}
		else {
			int vehicleId = Integer.parseInt(vehicleIdStr);
			vehicle = new Vehicle(vehicleId, model, countryEst, year);
		}
		vehicle.setIsApproved(false);
		
		AccountService accountService = AccountService.getInstance();
		accountService.editVehicle(userId, vehicle);
		
		response.sendRedirect(request.getContextPath() + "/user/account");
	}
	
}
