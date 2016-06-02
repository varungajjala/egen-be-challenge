/**
 * 
 */
package User;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import junit.framework.Assert;
import junit.framework.TestCase;
import testdata.*;

/**
 * @author Varun
 *
 */
public class UserServiceTest extends TestCase {
	
	private static final String TEST = "testDb";
    private static UserService userService;
    private static MongoCollection<Document> db;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		UserServiceTest.db = UserService.getCollection(UserServiceTest.TEST);
        if (UserServiceTest.db == null) {
            Assert.fail("Cannot get collection from MongoDB");
        }

        UserServiceTest.userService = new UserService(UserServiceTest.db);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		UserServiceTest.db.drop();
	}

	/**
	 * Test method for {@link User.UserService#createUser(java.lang.String)}.
	 */
	public void testCreateUser() {

        String message = UserServiceTest.userService.createUser(testData.JSON_LONG);
        String message1 = UserServiceTest.userService.createUser(testData.JSON_SHORT);
        
        Assert.assertEquals("should display successfull message", "User created successfully", message);
        
        Assert.assertEquals("should display successfull message", "User created successfully", message1);
        
        Assert.assertEquals("Number of documents should be 2 after the records are inserted", 2,
                UserServiceTest.userService.getAllUsers().size());

	}
	
	/**
	 * Test method for {@link User.UserService#createUser(java.lang.String)}.
	 */
	public void testCreateEmptyUser() {

        String message = UserServiceTest.userService.createUser("");
        
        Assert.assertEquals("error should be displayed", "Error: empty input",message);

	}

	/**
	 * Test method for {@link User.UserService#getAllUsers()}.
	 */
	public void testGetAllUsers() {
		Assert.assertEquals("Intially should be equal to 0", 0, UserServiceTest.userService.getAllUsers().size());
		
		UserServiceTest.userService.createUser(testData.JSON_LONG);
		
		Assert.assertEquals("After 1 insert should be 1", 1, UserServiceTest.userService.getAllUsers().size());		
		
	}

	/**
	 * Test method for {@link User.UserService#updateUser(java.lang.String)}.
	 */
	public void testUpdateUser() {
		UserServiceTest.userService.createUser(testData.JSON_LONG);
		
		String message = UserServiceTest.userService.updateUser(testData.JSON_LONG_UPDATED);
		
		Assert.assertEquals("Successful message should be displayed", "User Updated Successfully", message);
	}
	
	/**
	 * Negative Test method for {@link User.UserService#updateUser(java.lang.String)}.
	 */
	public void testUpdateInvalidUser() {
		UserServiceTest.userService.createUser(testData.JSON_LONG);
		
		String message = UserServiceTest.userService.updateUser(testData.JSON_SHORT);
		
		Assert.assertEquals("null should be displayed", null, message);
	}
	
	/**
	 * Negative Test method for {@link User.UserService#updateUser(java.lang.String)}.
	 */
	public void testUpdateEmptyUser() {
		UserServiceTest.userService.createUser(testData.JSON_LONG);
		
		String message = UserServiceTest.userService.updateUser("");
		
		Assert.assertEquals("null should be displayed", null, message);
	}

	/**
	 * Test method for {@link User.UserService#getCollection(java.lang.String)}.
	 */
	public void testGetCollection() {
		UserServiceTest.db = UserService.getCollection(UserServiceTest.TEST);
		if(db == null){
			Assert.fail("db cannot be null");
		}
	}

}
