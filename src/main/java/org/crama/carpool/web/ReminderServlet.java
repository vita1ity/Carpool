package org.crama.carpool.web;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crama.carpool.model.Account;
import org.crama.carpool.model.Notification;
import org.crama.carpool.service.AccountService;
import org.crama.carpool.service.NotificationService;

@WebServlet("/route/reminder")
public class ReminderServlet extends HttpServlet {

	private static final long serialVersionUID = 1205787790809123352L;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account from = (Account)session.getAttribute("user");
		
		int userToId = Integer.parseInt(request.getParameter("to"));
		String message = request.getParameter("message");
		
		AccountService accountService = AccountService.getInstance();
		Account to = accountService.getAccountById(userToId);
		
		Notification notif = new Notification(from, to, LocalDateTime.now(), message, false);
		NotificationService notificationService = NotificationService.getInstance();
		notificationService.addNotification(notif);
		
		response.sendRedirect(request.getContextPath() + "/route/manage-carpool");
	}
	
}
