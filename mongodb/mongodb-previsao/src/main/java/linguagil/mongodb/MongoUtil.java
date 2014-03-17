package linguagil.mongodb;

import java.net.UnknownHostException;
import java.util.logging.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public enum MongoUtil {
INSTANCE;

private MongoClient mongoDB;
private DB db;

public DBCollection getColecao(String colecao) {
	return db.getCollection(colecao);
}


	{
		try {
			mongoDB = new MongoClient("localhost", 27017);
			db = mongoDB.getDB("tabela");
		} catch (UnknownHostException exception) {
			Logger.getLogger(MongoUtil.class.getName()).severe("Erro ao criar conexão com o MongoDB " +exception.getMessage());
		}
	}
}
