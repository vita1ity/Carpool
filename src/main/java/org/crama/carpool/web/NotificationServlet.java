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
import org.crama.carpool.model.Notification;
import org.crama.carpool.service.NotificationService;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {

	private static final long serialVersionUID = -1106740385043490240L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account user = (Account)session.getAttribute("user");
		
		NotificationService notificationService = NotificationService.getInstance();
		List<Notification> notifications = notificationService.getUserNotifications(user.getUserId());
		request.setAttribute("notifications", notifications);
		
		notificationService.markAsSeen(notifications);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/notifications.jsp");
	      rd.forward(request, response);
	}
	
}
