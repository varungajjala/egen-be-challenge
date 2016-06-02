package egenChallenge.egen;

import User.UserService;
import controllers.UserController;

/**
 * @author Varun
 *
 */
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UserController(new UserService("testuser"));
	}

}
