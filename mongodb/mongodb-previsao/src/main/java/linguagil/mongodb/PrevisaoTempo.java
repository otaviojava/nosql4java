package linguagil.mongodb;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PrevisaoTempo {
	private String cidade;
	
	private Double temperatura;

	private Date dia;
	
	private Integer atualizacao;

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Integer getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Integer atualizacao) {
		this.atualizacao = atualizacao;
	}
	
	@Override
	public String toString() {
	
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
