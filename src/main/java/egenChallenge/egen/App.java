package egenChallenge.egen;

import User.UserService;
import controllers.UserController;

/**
 * Main Application for the user services
 * 
 * @author Varun
 *
 */
public class App {

	public App(String dbName){
		new UserController(new UserService(dbName));
	}
	public static void main(String[] args) {
		new App("egen");
	}

}
