package org.crama.carpool.service;

import org.crama.carpool.dao.AccountDAO;
import org.crama.carpool.model.Account;

public class AccountService {
	private AccountDAO accountDAO;
	private static AccountService instance = null;
	private AccountService() {
		
		accountDAO = AccountDAO.getInstance();
	}
	public static synchronized AccountService getInstance() {
		if (instance == null) {
			instance = new AccountService();			
		}
		return instance;
	}
	public Account getAccount(String username) {
		
		return accountDAO.getAccount(username);
	}
	public void saveAccount(Account newUser) {
		accountDAO.saveAccount(newUser);
		
	}
	
}
