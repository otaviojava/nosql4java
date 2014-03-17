package linguagil.mongodb;


import java.net.UnknownHostException;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	  try {
    		  
    			/**** Criando conexão ****/
    			MongoClient mongo = new MongoClient("localhost", 27017);
    		 
    			/**** banco de dados do mongo ****/
    			DB db = mongo.getDB("tabela");
    		 
    			/**** coleção, mongo tem coleção e não tabela se não tiver ele cria é dinâmico****/
    			
    			DBCollection table = db.getCollection("pessoa");
    		 
    			/**** inserindo ****/
    			inserindo(table);
    		 
    			/**** buscando ****/
    			buscando(table);
    		 
    			/**** atualizando, sendo que se não tiver vai dar pau ****/
    			atualizando(table);
    		 
    		
    			segundaConsulta(table);
    		 
    			System.out.println("Done");
    		 
    		    } catch (UnknownHostException e) {
    			e.printStackTrace();
    		    } catch (MongoException e) {
    			e.printStackTrace();
    		    }
    		 
    }

	private static void segundaConsulta(DBCollection table) {
		BasicDBObject searchQuery2  = new BasicDBObject().append("nome", "Murilo Gabriela");
 		 
		DBCursor cursor2 = table.find(searchQuery2);
 		 
		while (cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
	}

	private static void atualizando(DBCollection table) {
		BasicDBObject query = new BasicDBObject();
		query.put("nome", "Murilo");
 		 
		BasicDBObject atualizandoDB = new BasicDBObject();
		atualizandoDB.put("nome", "Murilo Gabriela");
 		 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", atualizandoDB);
 		 
		table.update(query, updateObj);
	}

	private static void buscando(DBCollection table) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("nome", "Murilo");
 		 
		DBCursor cursor = table.find(searchQuery);
		
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	private static void inserindo(DBCollection table) {
		BasicDBObject document = new BasicDBObject();
		document.put("nome", "Murilo");
		document.put("idade", 28);
		document.put("inicioVersao", new Date());
		table.insert(document);
	}
}