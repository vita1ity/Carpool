package org.crama.carpool.service;

import java.util.List;

import org.crama.carpool.dao.NotificationDAO;
import org.crama.carpool.model.Notification;

public class NotificationService {
	private NotificationDAO notificationDAO;
	private static NotificationService instance = null;
	private NotificationService() {
		
		notificationDAO = NotificationDAO.getInstance();
	}
	public static synchronized NotificationService getInstance() {
		if (instance == null) {
			instance = new NotificationService();			
		}
		return instance;
	}
	public void addNotification(Notification notif) {
		notificationDAO.saveNotification(notif);
	}
	public List<Notification> getUserNotifications(int userId) {	
		return notificationDAO.getUserNotifications(userId);
	}
	public void markAsSeen(List<Notification> notifications) {
		for (Notification n: notifications) {
			if (!n.getSeen()) {
				notificationDAO.markAsSeen(n);
			}
		}
	}
}
