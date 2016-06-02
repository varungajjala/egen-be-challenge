package egenChallenge.egen;

import User.UserService;
import controllers.UserController;

/**
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
