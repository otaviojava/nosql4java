package linguagil.mongodb.repository;

import java.util.List;

import linguagil.mongodb.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
	@Autowired
	private MongoOperations mongoOperation;
	
	
	public void salvar(Usuario usuario) {
		mongoOperation.save(usuario);
	}
	public Usuario buscar(String nickName){
		Query query = new Query(Criteria.where("nickname").is(nickName));
		return  mongoOperation.findOne(query, Usuario.class);
	}
	
	public void delete(String nickName) {
		Query query = new Query(Criteria.where("nickname").is(nickName));
		mongoOperation.remove(query, Usuario.class);
	}
	public void atualizar(String nickName, String password){
		Query query = new Query(Criteria.where("nickname").is(nickName));
		mongoOperation.updateFirst(query, Update.update("password", "123456"),
				Usuario.class);
	}
	public List<Usuario> findAll() {
		return mongoOperation.findAll(Usuario.class);
	}
}
