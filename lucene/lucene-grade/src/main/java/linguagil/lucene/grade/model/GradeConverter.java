package linguagil.lucene.grade.model;

import org.springframework.core.convert.converter.Converter;

public class GradeConverter implements Converter<String, Grade>{

	private static final int INDICE_TITULO = 0;
	private static final int INDICE_DESCRICAO = 1;
	private static final int INDICE_PALESTRANTE = 2;
	private static final int INDICE_MINI_BIO = 3;

	@Override
	public Grade convert(String arg0) {
		String[] valores = arg0.split("\\|");
		Grade grade = new Grade();
		grade.setTitulo(valores[INDICE_TITULO]);
		grade.setDescricao(valores[INDICE_DESCRICAO]);
		grade.setPalestrante(valores[INDICE_PALESTRANTE]);
		grade.setMiniBio(valores[INDICE_MINI_BIO]);
		return grade;
	}

}
