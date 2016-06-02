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

        try {
			UserServiceTest.userService.createUser(testData.JSON_LONG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			UserServiceTest.userService.createUser(testData.JSON_SHORT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals("Number of documents should be 2 after the records are inserted", 2,
                UserServiceTest.userService.getAllUsers().size());

	}
	
	/**
	 * Negative Test method for {@link User.UserService#createUser(java.lang.String)}.
	 */
	public void testCreateInvalidUser() {

        try {
			UserServiceTest.userService.createUser(testData.JSON_INVALID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals("Number of documents should be 0", 0,
                UserServiceTest.userService.getAllUsers().size());

	}
	
	/**
	 * Test method for {@link User.UserService#createUser(java.lang.String)}.
	 */
	public void testCreateEmptyUser() {

        try {
			UserServiceTest.userService.createUser("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Assert.assertEquals("Number of documents should be 0", 0,
                UserServiceTest.userService.getAllUsers().size());

	}

	/**
	 * Test method for {@link User.UserService#getAllUsers()}.
	 */
	public void testGetAllUsers() {
		Assert.assertEquals("Intially should be equal to 0", 0, UserServiceTest.userService.getAllUsers().size());
		
		try {
			UserServiceTest.userService.createUser(testData.JSON_LONG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals("After 1 insert should be 1", 1, UserServiceTest.userService.getAllUsers().size());		
		
	}

	/**
	 * Test method for {@link User.UserService#updateUser(java.lang.String)}.
	 */
	public void testUpdateUser() {
		try {
			UserServiceTest.userService.createUser(testData.JSON_LONG);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			UserServiceTest.userService.updateUser(testData.JSON_LONG_UPDATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			Document result = UserServiceTest.userService.getAllUsers().get(0);
			
Document input = Document.parse(testData.JSON_LONG_UPDATED);
			
			Assert.assertEquals("both should be same", input.toJson(), result.toJson().toString());
		}
	}
	
	/**
	 * Negative Test method for {@link User.UserService#updateUser(java.lang.String)}.
	 */
	public void testUpdateInvalidUser() {
		try {
			UserServiceTest.userService.createUser(testData.JSON_LONG);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			UserServiceTest.userService.updateUser(testData.JSON_SHORT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			Document result = UserServiceTest.userService.getAllUsers().get(0);
			Document input = Document.parse(testData.JSON_LONG);
			
			Assert.assertEquals("both should be same", input.toJson(), result.toJson().toString());
		}
		
		
	}
	
	/**
	 * Negative Test method for {@link User.UserService#updateUser(java.lang.String)}.
	 */
	public void testUpdateEmptyUser() {
		try {
			UserServiceTest.userService.createUser(testData.JSON_LONG);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			UserServiceTest.userService.updateUser("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
			Document result = UserServiceTest.userService.getAllUsers().get(0);
			Document input = Document.parse(testData.JSON_LONG);
			
			Assert.assertEquals("both should be same", input.toJson(), result.toJson().toString());
		}
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
