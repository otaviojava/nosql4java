package linguagil.mongodb;

import java.net.UnknownHostException;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public enum MongoUtil {
INSTANCE;

private MongoClient mongo;
private DB db;
{
	
	try  {
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB("pessoa");
	} catch (UnknownHostException e) {
		Logger.getLogger(MongoUtil.class.getName()).severe("Erro ao iniciar a conexão" + e.getMessage());
	}
	}

	public DBCollection getCollection(String collection) {
		return db.getCollection("pessoa");
	}

}
