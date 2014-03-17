package linguagil.riak;

import linguagil.riak.bo.LivroPersistence;
import linguagil.riak.modelo.Livro;

public class App {

	public static void main(String[] args) {
		LivroPersistence persistence = new LivroPersistence();
		
		Livro livro = makeLivro();
		
		persistence.armazenar(livro);
		
		Livro recuperado = persistence.recuperar(livro.getISBN());
		System.out.println("Livro recuperado: " +recuperado.toString());
		
		recuperado.setConteudo(recuperado.getConteudo().concat(" atualizado!!"));
		persistence.armazenar(recuperado);
		
		
		System.out.println("Livro recuperado e atualizado: " +persistence.recuperar(livro.getISBN()));
		
		persistence.remover(livro.getISBN());
		
		System.out.println("Livro removido: " +persistence.recuperar(livro.getISBN()));

	}
	
	private static Livro makeLivro() {
		Livro livro = new Livro();
		
		livro.setAutor("Otávio Gonçalves de Santana");
		livro.setConteudo("Conteudo do livro");
		livro.setISBN("123-123-456-678");
		livro.setPaginas(90);
		livro.setTitulo("Imergindo na JVM");
		return livro;
	}

}
