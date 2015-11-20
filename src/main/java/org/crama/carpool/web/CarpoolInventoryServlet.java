package org.crama.carpool.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crama.carpool.model.Account;
import org.crama.carpool.model.Route;
import org.crama.carpool.service.RouteService;

@WebServlet("/inventory")
public class CarpoolInventoryServlet extends HttpServlet {

	private static final long serialVersionUID = -2372933369391253587L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		
		RouteService routeService = RouteService.getInstance();
		List<Route> routes = routeService.getApprovedRoutes(user.getUserId());
		request.setAttribute("routes", routes);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/carpoolInventory.jsp");
	      rd.forward(request, response);
	}
	
}
