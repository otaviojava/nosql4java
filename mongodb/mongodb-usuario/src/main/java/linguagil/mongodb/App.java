package linguagil.mongodb;

import java.util.Calendar;
import java.util.List;

import linguagil.mongodb.model.Usuario;
import linguagil.mongodb.repository.UsuarioRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class App {

	public static void main(String[] args) {

		//  XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		UsuarioRepository repository = ctx.getBean(UsuarioRepository.class);
		Usuario usuario = getUsuario();

		repository.salvar(usuario);

		System.out.println("1. usuario : " + usuario);

		
		Usuario usuarioEncontrado = repository.buscar(usuario.getNickname());
		System.out.println("2. usuario encontrado : " + usuarioEncontrado);

		repository.atualizar(usuario.getNickname(), "123456789");

		Usuario usuarioAtualizado = repository.buscar(usuario.getNickname());

		System.out.println("3. atualizado : " + usuarioAtualizado);

		repository.delete(usuario.getNickname());

		List<Usuario> list = repository.findAll();
		System.out.println("4. numero de usuario = " + list.size());

	}

	private static Usuario getUsuario() {
		Calendar calendar = Calendar.getInstance();
		Usuario usuario = new Usuario();
		usuario.setNickname("otaviojava");
		usuario.setNome("Otávio Gonçalves de Santana");
		usuario.setInicio(calendar.getTime());
		calendar.add(Calendar.MONTH, +10);
		usuario.setFim(calendar.getTime());
		usuario.setPassword("123456");
		return usuario;
	}

}