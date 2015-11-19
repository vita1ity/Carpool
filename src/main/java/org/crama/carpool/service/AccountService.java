package org.crama.carpool.service;

import org.crama.carpool.dao.AccountDAO;
import org.crama.carpool.model.Account;
import org.crama.carpool.model.Address;
import org.crama.carpool.model.Vehicle;

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
	public Account getFullAccountInfo(int userId) {
		return accountDAO.getFullAccount(userId);
	}
	public void saveAccount(Account newUser) {
		accountDAO.saveAccount(newUser);
		
	}
	
	public Account getFullAccount(int userId) {
		return accountDAO.getFullAccount(userId);
	}
	public void editAccount(Account account) {
		//TODO send account change notification to admin
		//adminService.sendAccountChangeNotification();
		
		//address is not exist in db yet
		Address address = account.getAddress();
		if (address.getAddressId() == -1) {
			accountDAO.saveAddress(address);
			int id = accountDAO.getMaxAddressId();
			address.setAddressId(id);
			account.setAddress(address);
		}
		else {
			accountDAO.editAddress(address);
		}
		
		accountDAO.editAccount(account);
		
	}
	public void editVehicle(int userId, Vehicle vehicle) {
		//TODO send vehicle change notification to admin
		//adminService.sendVehicleChangeNotification();
		if (vehicle.getVehicleId() == -1) {
			accountDAO.saveVehicle(vehicle);
			int vehicleId = accountDAO.getMaxVehicleId();
			accountDAO.setVehicleId(userId, vehicleId);
		}
		else {
			accountDAO.editVehicle(vehicle);
		}
	}
	
}
