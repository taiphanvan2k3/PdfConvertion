package model.BO;

import model.BEAN.User;
import model.DAO.LoginDAO;

public class LoginBO {
	public User checkLogin(String username, String password) {
		return (new LoginDAO()).checkLogin(username, password);
	}

	public boolean createAccount(String username, String password) {
		return (new LoginDAO()).createAccount(username, password);
	}
}
