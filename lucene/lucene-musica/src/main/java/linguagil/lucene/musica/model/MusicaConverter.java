package linguagil.lucene.musica.model;

import org.springframework.core.convert.converter.Converter;

public class MusicaConverter implements Converter<String, Musica>{

	private static final int INDICE_TEXTO = 2;
	private static final int INDICE_AUTOR = 1;
	private static final int INDICE_NOME = 0;

	@Override
	public Musica convert(String arg0) {
		String[] valores = arg0.split("\\|");
		Musica musica = new Musica();
		musica.setNome(valores[INDICE_NOME]);
		musica.setAutor(valores[INDICE_AUTOR]);
		musica.setTexto(valores[INDICE_TEXTO]);
		return musica;
	}

}
