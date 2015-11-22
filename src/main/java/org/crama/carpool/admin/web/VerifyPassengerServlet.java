package org.crama.carpool.admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.admin.service.RouteServiceAdmin;
import org.crama.carpool.model.Passenger;
import org.crama.carpool.model.Route;
import org.crama.carpool.util.Util;

@WebServlet("/admin/passengers/verify")
public class VerifyPassengerServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8528046683646623214L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int passId = Integer.parseInt(request.getParameter("passId"));
		String pickTime = request.getParameter("pickTime");
		
		
		RouteServiceAdmin routeAdminService = RouteServiceAdmin.getInstance();
		
		Passenger pass = routeAdminService.getPassenger(passId);
		pass.setIsApproved(true);
		pass.setPickTime(Util.createTime(pickTime));
		
		routeAdminService.editPassenger(pass);
		response.sendRedirect(request.getContextPath() + "/admin/passengers");
		
		
	}
}
