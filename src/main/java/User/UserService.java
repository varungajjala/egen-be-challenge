package User;

import java.util.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Projections;
import org.bson.*;

/**
 * Class to create User Services
 * 
 * @author Varun
 * 
 */
public class UserService {
	
	private MongoCollection<Document> db;
	
	/**
	 * Constructor for UserService class
	 * @param dbName {@code String} database name to get connected to and retrieve the collection
	 */
	public UserService(String dbName){
		this.db = this.getCollection(dbName);
	}
	
	/**
	 * Constructor for UserServiceTest class - for testing
	 * @param dbName {@code String} database name to get connected to and retrieve the collection
	 */
	public UserService(MongoCollection<Document> db){
		this.db = db;
	}
	
	/**
	 * createUser Service - creates user if the id is not present
	 * 
	 * @param json {@code String} the user details to enter into the database
	 * @return void
	 */
	public void createUser(String json) throws Exception{
		Document doc = null;
		if (json != "" && json != null){
			doc = Document.parse(json);
			if(doc.get("id") != null){
				this.db.insertOne(doc);
			}else{
				throw new Exception("Invalid Input");
			}
		}else{
			throw new Exception("Empty Input");
		}
		
	}
	
	/**
	 * getAllUsers Service - retrieves all the users in the collection.
	 * 
	 * @return List {@code ArrayList<Document>}of user-details in the database
	 */
	public ArrayList<Document> getAllUsers(){
		
		MongoCursor<Document> mongoCursor = this.db.find().projection(Projections.excludeId()).iterator();
		ArrayList<Document> allUsers = new ArrayList<Document>();
		
		while(mongoCursor.hasNext()){
			allUsers.add(mongoCursor.next());
		}
		
		return allUsers;
		
	}
	
	/**
	 * updateUser Service - updates the user details if the user is already present
	 * 
	 * @param json {@code String} the user details to update the user details already in the database
	 * @return void
	 *
	 */
	public void updateUser(String json) throws Exception{
		Document doc = null;
		
		if( json != "" && json != null){
			doc = Document.parse(json);
		}else{
			throw new Exception("Input cannot be empty");
		}
		
		String id = doc.get("id").toString();
		Document userDoc = null;
		
		try{
			int int_id = Integer.parseInt(id);
			userDoc = this.db.find(Filters.eq("id", int_id)).first();
		}catch(Exception e){
			userDoc = this.db.find(Filters.eq("id", id)).first();
		}
		
		if(userDoc != null){
			this.db.updateOne(userDoc, new Document("$set",doc));
		}else{
			throw new Exception("User not Found");
		}
	}
	
	/**
	 * Method to get the collection from the mongodb database
	 * 
	 * @param dbName {@code String} database name to get connected to
	 * @return the collection {@code MongoCollection<Document>}of users in the user table in the given database
	 */
	public static MongoCollection<Document> getCollection(String dbName){
		
		MongoClient mongo = new MongoClient("localhost",27017);
		MongoDatabase db = mongo.getDatabase(dbName);
		
		MongoCollection<Document> collection = db.getCollection("user");
		collection.createIndex(new Document("id", 1), new IndexOptions().unique(true));
		
		return collection;
		
	}

}
