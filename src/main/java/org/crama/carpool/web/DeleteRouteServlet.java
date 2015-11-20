package org.crama.carpool.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crama.carpool.model.Account;
import org.crama.carpool.service.RouteService;

@WebServlet("/route/delete/*")
public class DeleteRouteServlet extends HttpServlet {

	private static final long serialVersionUID = 8249810658562388751L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathInfo = request.getPathInfo().split("/");
		int routeId = 0;
		if (pathInfo.length == 2) {
			routeId = Integer.parseInt(pathInfo[1]);
			HttpSession session = request.getSession();
			Account user = (Account)session.getAttribute("user");
			
			//Passenger pass = new Passenger(user, routeId, null, false);
			RouteService routeService = RouteService.getInstance();
			routeService.removeRoute(routeId);
			
			response.sendRedirect(request.getContextPath() + "/route/manage-carpool");
			
		}
	}
	
}
