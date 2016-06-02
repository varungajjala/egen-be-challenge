package utils;

/**
 * ResponseError - format the error and return the error
 * @author Varun
 *
 */
public class ResponseError {
	private String message;
	 
	/**
	 * ResponseError - constructor
	 * 
	 * @param message {@code String} message to be displayed
	 * @param args 
	 */
	public ResponseError(String message, String... args) {
	    this.message = String.format(message, args);
	}
	 
	/**
	 * Set message to the exception message
	 * @param e exception{@code Exception} 
	 */
	public ResponseError(Exception e) {
	    this.message = e.getMessage();
	}
	 
	/**
	 * Return the message
	 * @return error message {@code String}
	 */
	public String getMessage() {
	    return this.message;
	}
}
