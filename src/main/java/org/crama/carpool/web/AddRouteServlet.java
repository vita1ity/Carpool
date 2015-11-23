package org.crama.carpool.web;

import java.io.IOException;
import java.time.LocalTime;

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
import org.crama.carpool.validation.RouteValidator;

@WebServlet("/route/add")
public class AddRouteServlet extends HttpServlet {

	private static final long serialVersionUID = 5662173169164864104L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/addRoute.jsp");
	      rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		
		int userId = user.getUserId();
		String routeName = request.getParameter("routeName");
		String sourceAddress = request.getParameter("sourceAddress");
		String destinationAddress = request.getParameter("destinationAddress");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String rideComment = request.getParameter("comment");
		String numOfPassengers = request.getParameter("numOfPassengers");
		
		String errorMessage = RouteValidator.validateRoute(sourceAddress, destinationAddress, startTime, endTime, numOfPassengers);
		
		request.setAttribute("errorMessage", errorMessage);
		if (errorMessage != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/addRoute.jsp");
			rd.forward(request, response);
		}
		else {
			
			String[] startNum = startTime.split(":");
			String[] endNum = endTime.split(":");
			
			int startHours = Integer.parseInt(startNum[0]);
			int endHours = Integer.parseInt(endNum[0]);
			int startMinutes = Integer.parseInt(startNum[1]);
			int endMinutes = Integer.parseInt(endNum[1]);
			
			LocalTime startTimeLocal = LocalTime.of(startHours, startMinutes);
			LocalTime endTimeLocal = LocalTime.of(endHours, endMinutes);
			System.out.println(startTimeLocal.toString());
			System.out.println(endTimeLocal.toString());
			
			int numPass = Integer.parseInt(numOfPassengers);
			
			Route route = new Route(routeName, sourceAddress, destinationAddress, startTimeLocal, endTimeLocal, 
					rideComment, numPass, numPass, null, null, user, false);
			RouteService routeService = RouteService.getInstance();
			routeService.addRoute(route);
			
			response.sendRedirect(request.getContextPath() + "/route/manage-carpool");
		}
	}
}
