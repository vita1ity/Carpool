package org.crama.carpool.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crama.carpool.admin.service.RouteServiceAdmin;
import org.crama.carpool.model.Route;

@WebServlet("/admin/passengers")
public class UnverifiedPassengerServlet extends HttpServlet {

	private static final long serialVersionUID = 8064185000079025111L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RouteServiceAdmin routeService = RouteServiceAdmin.getInstance();
		List<Route> unverifiedPassengers = routeService.getUnverifiedPassengers();
		request.setAttribute("routes", unverifiedPassengers);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/passengers.jsp");
	    rd.forward(request, response);
	}
	
}
