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

@WebServlet("/admin/routes/verify")
public class VerifyRouteServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4330401124590362408L;

	/*@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] pathInfo = request.getPathInfo().split("/");
		int routeId = 0;
		
		if (pathInfo.length == 2) {
			routeId = Integer.parseInt(pathInfo[1]);
			
			RouteServiceAdmin routeAdminService = RouteServiceAdmin.getInstance();
			
			Route route = routeAdminService.getRoute(routeId);
			
			route.setIsApproved(true);
			routeAdminService.editRoute(route);
			response.sendRedirect(request.getContextPath() + "/admin/routes");
			
		}
	}*/
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		String mapUrl = request.getParameter("mapUrl");
		
		
		RouteServiceAdmin routeAdminService = RouteServiceAdmin.getInstance();
		
		Route route = routeAdminService.getRoute(routeId);
		
		route.setIsApproved(true);
		route.setMapUrl(mapUrl);
		routeAdminService.editRoute(route);
		
		response.sendRedirect(request.getContextPath() + "/admin/routes");
		
		
	}
}
