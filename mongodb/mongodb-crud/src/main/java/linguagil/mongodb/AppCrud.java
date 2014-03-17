package linguagil.mongodb;

import java.util.Date;
import java.util.UUID;

public class AppCrud {

	public static void main(String[] args) {
		PessoaDAO dao = new PessoaDAO();
		UUID uuid = UUID.randomUUID();
		Pessoa pessoa = new Pessoa();
//		pessoa.setCpf("123456");
		pessoa.setCpf(uuid.toString());
		pessoa.setNascimento(new Date());
		pessoa.setNome("Otávio");
		
		dao.inserir(pessoa);
		
		System.out.println(dao.buscar(pessoa.getCpf()));
		
		pessoa.setNome("Otávio Atualizado");
		
		dao.atualizando(pessoa);
		
		System.out.println(dao.buscar(pessoa.getCpf()));
		
		dao.delete(pessoa.getCpf());
		
		System.out.println(dao.buscar(pessoa.getCpf()));
	}
}
