package mongo.app;


import java.io.FileInputStream;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Set;

import org.junit.BeforeClass;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Java + MongoDB Hello world Example
 * 
 */
public class App {
	
	static DBCollection collection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// connect to mongoDB, ip and port number
		Mongo mongo = new Mongo("localhost", 27017);

		// get database from MongoDB,
		// if database doesn't exists, mongoDB will create it automatically
		DB db = mongo.getDB("yourdb");

		// Get collection from MongoDB, database named "yourDB"
		// if collection doesn't exists, mongoDB will create it automatically
		collection = db.getCollection("yourCollection");
	}


	

	public void insert() throws UnknownHostException {

		try {



			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("id", 1001);
			document.put("msg", "hello world mongoDB in Java");

			// save it into collection named "yourCollection"
			collection.insert(document);


			System.out.println("Done");

		} catch (MongoException e) {
			e.printStackTrace();
		}

	}

	public void search(String Keyword) throws UnknownHostException {
		try {

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", 1001);

			// query it
			DBCursor cursor = collection.find(searchQuery);

			// loop over the cursor and display the retrieved result
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}