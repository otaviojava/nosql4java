package linguagil.lucene.grade.model;

import java.io.Serializable;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Grade implements Serializable{
	
	private static final String SEPERADOR = " ";

	private static final long serialVersionUID = -8245568483951712497L;

	private String titulo;
	
	private String descricao;
	
	private String palestrante;
	
	private String miniBio;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(String palestrante) {
		this.palestrante = palestrante;
	}

	public String getMiniBio() {
		return miniBio;
	}

	public void setMiniBio(String miniBio) {
		this.miniBio = miniBio;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getTudo() {
		StringBuilder tudo = new StringBuilder();
		
		tudo.append(ObjectUtils.defaultIfNull(titulo, StringUtils.EMPTY)).append(SEPERADOR)
		.append(ObjectUtils.defaultIfNull(descricao, StringUtils.EMPTY)).append(SEPERADOR)
		.append(ObjectUtils.defaultIfNull(palestrante, StringUtils.EMPTY)).append(SEPERADOR)
		.append(ObjectUtils.defaultIfNull(miniBio, StringUtils.EMPTY)).append(SEPERADOR);
		
		return tudo.toString();
	}

}
