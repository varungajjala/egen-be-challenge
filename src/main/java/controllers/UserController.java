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

		put("/createUser", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				try{
					userService.createUser(request.body());
					response.status(200);
					return new ResponseError("User created successfully");
				}catch(Exception ex){
					response.status(400);
					return new ResponseError(ex.getMessage());
				}
				
			}
			
			
		}, new JsonTransformer());
		
		get("/getAllUsers", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				response.status(200);
				return userService.getAllUsers();
			}
			
		}, new JsonTransformer());
		
		put("/updateUser", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				try{
					userService.updateUser(request.body());
					response.status(200);
					return new ResponseError("Updated Successfully");
				}catch (Exception ex){
					response.status(404);
					return new ResponseError(ex.getMessage());
				}
				
				 
			}
			
		}, new JsonTransformer());
		
		get("/", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				response.status(404);
				return new ResponseError("Not found");
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
