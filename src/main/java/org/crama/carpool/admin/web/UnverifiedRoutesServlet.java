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

@WebServlet("/admin/routes")
public class UnverifiedRoutesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 663862106654546970L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RouteServiceAdmin routeService = RouteServiceAdmin.getInstance();
		List<Route> unverifiedRoutes = routeService.getUnverifiedRoutes();
		request.setAttribute("routes", unverifiedRoutes);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/routes.jsp");
	    rd.forward(request, response);
	}
}
