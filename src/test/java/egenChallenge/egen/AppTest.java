/**
 * 
 */
package egenChallenge.egen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import User.UserService;
import junit.framework.Assert;
import junit.framework.TestCase;
import testdata.testData;

/**
 * @author Varun
 *
 */
public class AppTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		new App("testuser");
		Thread.sleep(5000);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		UserService.getCollection("testuser").drop();
	}

	/**
	 * Test method for {@link egenChallenge.egen.App#main(java.lang.String[])}.
	 */
	public void testMain() {

        String response = this.execUrl("/createUser", testData.JSON_LONG, "PUT");
        Assert.assertEquals("User created", "{\"message\":\"User created successfully\"}", response);

		String response1 = this.execUrl("/getAllUsers", null, "GET");
        Assert.assertEquals("1 user should match", "["+testData.JSON_LONG+"]", response1.replace("\"", "'"));

        
        String response2 = this.execUrl("/updateUser", testData.JSON_LONG_UPDATED, "PUT");
        Assert.assertEquals("1 user should be updated", "{\"message\":\"Updated Successfully\"}", response2);

	}
	
	/**
	 * Test negative method for {@link egenChallenge.egen.App#main(java.lang.String[])}.
	 */
	public void testMainNegative() {
        
        String response = this.execUrl("/createUser", null, "PUT");
        Assert.assertEquals("User created", "400", response);

		String response1 = this.execUrl("/getAllUsers", null, "GET");
        Assert.assertEquals("1 user should match", "[]", response1);
        
        this.execUrl("/createUser", testData.JSON_LONG, "PUT");

        String response3 = this.execUrl("/updateUser", testData.JSON_SHORT, "PUT");
        Assert.assertEquals("User Should not be updated", "404", response3);

	}
	
	/**
     * Rest client connection
     *
     */
    private String execUrl(final String testUrl, final String json, final String methodType) {
    	StringBuffer sb = new StringBuffer();

    	try {

    		URL url = new URL("http://localhost:4567"+testUrl);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setDoOutput(true);
    		conn.setRequestMethod(methodType);
    		conn.setRequestProperty("Content-Type", "application/json");

    		if(json != null){
	    		OutputStream os = conn.getOutputStream();
	    		os.write(json.getBytes());
	    		os.flush();
    		}

    		if (conn.getResponseCode() != 200) {
    			return String.valueOf(conn.getResponseCode());
    		}

    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				(conn.getInputStream())));

    		String output;
    		
    		while ((output = br.readLine()) != null) {
    			sb.append(output);
    		}

    		conn.disconnect();

    	  } catch (MalformedURLException e) {

    		e.printStackTrace();

    	  } catch (IOException e) {

    		e.printStackTrace();

    	 }
    	
    	return sb.toString();
    }
    

}
