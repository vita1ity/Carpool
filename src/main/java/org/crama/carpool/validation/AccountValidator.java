package org.crama.carpool.validation;

import org.crama.carpool.model.Account;
import org.crama.carpool.service.AccountService;

public class AccountValidator {
	
	public static final String USERNAME_EMPTY = "Username is empty";
	public static final String PASSWORD_EMPTY = "Password is empty";
	public static final String NO_SUCH_USER = "There is no user with such username registered in the system";
	public static final String INVALID_PASSWORD = "Invalid password";
	public static final String EMAIL_EMPTY = "Email is empty";
	public static final String USERNAME_ALREADY_IN_USE = "The user with this username is already registered";
	public static final String PASSWORD_DONT_MATCH = "Password don't match";
	public static final String WRONG_EMAIL_FORMAT = "Wrong email format";
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static String validateLogin(AccountService accountService,
			String username, String password) {
		if (username == null || username.equals("")) {
			return USERNAME_EMPTY;
		}
		if (password == null || password.equals("")) {
			return PASSWORD_EMPTY;
		}
		Account account = accountService.getAccount(username);
		if (account == null) {
			return NO_SUCH_USER;
		}
		
		if (!account.getPassword().equals(password)) {
			return INVALID_PASSWORD;
		}
		return null;
	}

	public static String validateRegistration(AccountService accountService, String username, String password,
			String confirmPassword, String email) {
		if (username == null || username.equals("")) {
			return USERNAME_EMPTY;
		}
		if (password == null || password.equals("")) {
			return PASSWORD_EMPTY;
		}
		if (email == null || email.equals("")) {
			return EMAIL_EMPTY;
		}
		if (accountService.getAccount(username) != null) {
			return USERNAME_ALREADY_IN_USE;
		}
		if (!password.equals(confirmPassword)) {
			return PASSWORD_DONT_MATCH;
		}
		if (!email.matches(EMAIL_PATTERN)) {
			return WRONG_EMAIL_FORMAT;
		}
		return null;
	}
	//TODO
	public static String validateAccount(String email) {
		if (!email.matches(EMAIL_PATTERN)) {
			return WRONG_EMAIL_FORMAT;
		}
		return null;
	}
}
