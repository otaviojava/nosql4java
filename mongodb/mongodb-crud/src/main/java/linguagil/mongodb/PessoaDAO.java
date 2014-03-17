package linguagil.mongodb;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class PessoaDAO {

	private DBCollection collection;
	
	public void inserir(Pessoa pessoa) {
		BasicDBObject document = new BasicDBObject();
		document.put("nome", pessoa.getNome());
		document.put("nascimento", pessoa.getNascimento());
		document.put("_id", pessoa.getCpf());
		collection.insert(document);
	}
	public Pessoa buscar(String cpf) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", cpf);
 		 
		DBObject cursor = collection.findOne(searchQuery);
		if (cursor == null) {
			return new Pessoa();
		}
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(String.class.cast(cursor.get("_id")));
		pessoa.setNascimento(Date.class.cast(cursor.get("nascimento")));
		pessoa.setNome(String.class.cast(cursor.get("nome")));
		return pessoa;
	}
	
	public void delete(String cpf) {
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("_id", cpf);
		collection.remove(deleteQuery);
	}
	public void atualizando(Pessoa pessoa) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", pessoa.getCpf());
 		 
		BasicDBObject atualizandoDB = new BasicDBObject();
		
		atualizandoDB.put("nome", pessoa.getNome());
		atualizandoDB.put("nascimento", pessoa.getNascimento());
 		 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", atualizandoDB);
 		 
		collection.update(query, updateObj);
	}
	{
		collection = MongoUtil.INSTANCE.getCollection("pessoa");
	}
}
