package controllers;

import static spark.Spark.*;

import User.UserService;
import utils.ResponseError;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import utils.JsonTransformer;

/**
 * Controller which maps the path to the methods.
 * @author Varun
 *
 */
public class UserController {
	public UserController(final UserService userService){
		get("/createUser/:user", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String user = request.params(":user");
				return new ResponseError(userService.createUser(user));
			}
			
			
		}, new JsonTransformer());
		
		get("/getAllUsers", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				return userService.getAllUsers();
			}
			
		}, new JsonTransformer());
		
		get("/updateUser/:user", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String user = request.params(":user");
				String updStatus = userService.updateUser(user);
				
				if(updStatus != null){
					return new ResponseError(updStatus);
				}
				response.status(404);
				return new ResponseError("Error 404: User not found"); 
			}
			
		}, new JsonTransformer());

		get("/hello", new Route() {

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {
				return "Hello World";
			}
			
		}, new JsonTransformer());
		after( new Filter(){

			@Override
			public void handle(Request req, Response res) throws Exception {
				res.type("application/json");
				
			}
			
		});
	}
}
