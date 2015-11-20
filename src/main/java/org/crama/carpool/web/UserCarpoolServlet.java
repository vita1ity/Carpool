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
import org.crama.carpool.model.Passenger;
import org.crama.carpool.model.Route;
import org.crama.carpool.service.RouteService;

@WebServlet("/route/manage-carpool")
public class UserCarpoolServlet extends HttpServlet{

	private static final long serialVersionUID = -3519986186310398962L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Manage Carpool");
		
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		
		RouteService routeService = RouteService.getInstance();
		List<Route> userRouteList = routeService.getUserRoutes(user.getUserId());
		request.setAttribute("userRoutes", userRouteList);
		
		List<Route> joinedRouteList = routeService.getJoinedRoutes(user.getUserId());
		System.out.println(joinedRouteList);
		for (Route route: joinedRouteList) {
			List<Passenger> pass = route.getPassengers();
			for (Passenger p: pass) {
				System.out.println(p.getRouteId() + ", " + p.getUser().getUsername() + ", " + p.getUser().getFullName());
			}
		}
		
		request.setAttribute("joinedRoutes", joinedRouteList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/manageRoutes.jsp");
	      rd.forward(request, response);
	}
	
}
