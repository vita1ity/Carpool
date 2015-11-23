package org.crama.carpool.model;

import java.time.LocalDateTime;

public class Notification implements Comparable<Notification>{
	
	private int id;
	private Account from;
	private Account to;
	private LocalDateTime date;
	private String message;
	private boolean seen;
	
	public Notification(int id, Account from, Account to, LocalDateTime date, String message, boolean seen) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.date = date;
		this.message = message;
		this.seen = seen;
	}
	
	
	public Notification(Account from, Account to, LocalDateTime date, String message, boolean seen) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.message = message;
		this.seen = seen;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Account getFrom() {
		return from;
	}
	public void setFrom(Account from) {
		this.from = from;
	}
	public Account getTo() {
		return to;
	}
	public void setTo(Account to) {
		this.to = to;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	@Override
	public String toString() {
		return "Notification [id=" + id + ", from=" + from + ", to=" + to + ", date=" + date + ", message=" + message
				+ ", seen=" + seen + "]";
	}


	@Override
	public int compareTo(Notification o) {
		
		return o.getDate().compareTo(this.getDate());
	}

	
}
