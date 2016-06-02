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
	 * createUser Service - creates user if the id is not present
	 * 
	 * @param json {@code String} the user details to enter into the database
	 * @return successful {@code String} if the details are inserted
	 * 	       error message {@code String} if there is an error 
	 */
	public String createUser(String json){
		Document doc = Document.parse(json);
		
		try{
			this.db.insertOne(doc);
			return "User created successfully";
		}catch(Exception e){
			return e.getMessage();
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
	 * @return Successful {@code String} if updated.
	 * 		   null {@code null} if the user is not present in the database
	 *
	 */
	public String updateUser(String json){
		Document doc = Document.parse(json);
		String id = doc.get("id").toString();
		Document userDoc = null;
		
		try{
			int int_id = Integer.parseInt(id);
			userDoc = this.db.find(Filters.eq("id", int_id)).first();
		}catch(Exception e){
			userDoc = this.db.find(Filters.eq("id", id)).first();
		}
		
		System.out.println(userDoc);
		
		if(userDoc != null){
			this.db.updateOne(userDoc, new Document("$set",doc));
		}else{
			return null;
		}
		
		return "User Updated Successfully";
	}
	
	/**
	 * Method to get the collection from the mongodb database
	 * 
	 * @param dbName {@code String} database name to get connected to
	 * @return the collection {@code MongoCollection<Document>}of users in the user table in the given database
	 */
	public MongoCollection<Document> getCollection(String dbName){
		
		MongoClient mongo = new MongoClient("localhost",27017);
		MongoDatabase db = mongo.getDatabase(dbName);
		MongoCollection<Document> collection = db.getCollection("user");
		
		collection.createIndex(new Document("id", 1), new IndexOptions().unique(true));
		
		return collection;
		
	}
}
