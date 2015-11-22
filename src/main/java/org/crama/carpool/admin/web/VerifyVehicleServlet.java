package org.crama.carpool.admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.admin.service.AccountServiceAdmin;
import org.crama.carpool.model.Vehicle;
import org.crama.carpool.service.AccountService;

@WebServlet("/admin/vehicles/verify/*")
public class VerifyVehicleServlet extends HttpServlet {

	private static final long serialVersionUID = -6385203341116544278L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		int vehicleId = 0;
		
		if (pathInfo.length == 2) {
			vehicleId = Integer.parseInt(pathInfo[1]);
			
			AccountServiceAdmin accountAdminService = AccountServiceAdmin.getInstance();
			AccountService accountService = AccountService.getInstance();
			Vehicle vehicle = accountService.getVehicle(vehicleId);
			
			vehicle.setIsApproved(true);
			accountAdminService.editVehicle(vehicle);
			response.sendRedirect(request.getContextPath() + "/admin/vehicles");
			
		}
	}
	
}
