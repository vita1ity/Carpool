package org.crama.carpool.admin.service;

import java.util.List;

import org.crama.carpool.admin.dao.AccountDAOAdmin;
import org.crama.carpool.model.Account;
import org.crama.carpool.model.Vehicle;

public class AccountServiceAdmin {
	private AccountDAOAdmin accountDAO;
	private static AccountServiceAdmin instance = null;
	private AccountServiceAdmin() {
		
		accountDAO = AccountDAOAdmin.getInstance();
	}
	public static synchronized AccountServiceAdmin getInstance() {
		if (instance == null) {
			instance = new AccountServiceAdmin();			
		}
		return instance;
	}
	public List<Account> getUnverifiedAccounts() {
		
		return accountDAO.getUnverifiedAccounts();
	}
	public List<Vehicle> getUnverifiedVehicles() {
		
		return accountDAO.getUnverifiedVehicles();
	}
	
	public void editVehicle(Vehicle vehicle) {
		accountDAO.updateVehicle(vehicle);
		
	}
}
