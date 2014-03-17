package linguagil.lucene;

import java.io.IOException;

import linguagil.lucene.musica.LuceneUtil;
import linguagil.lucene.musica.lucene.MusicaSearch;
import linguagil.lucene.musica.model.Musica;
import linguagil.lucene.musica.model.Musicas;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) throws IOException, ParseException {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
		Musicas musicas = ctx.getBean(Musicas.class);
		MusicaSearch search = new MusicaSearch();
		for (Musica musica: musicas.getMusicas()) {
			search.indexar(musica);	
		}
		LuceneUtil.INSTANCE.finalizar();
		System.out.println(search.getProcurarMusicaTexto("lepo lepo"));
		System.out.println(search.getProcurarMusicaTexto("aMoR"));
		
		System.out.println(search.getProcurarMusicaAutor("Luan Santana"));
		System.out.println(search.getProcurarMusicaAutor("Santana"));
		System.out.println(search.getProcurarMusicaAutor("Psirico"));
	}
	
}