package linguagil.neo4j.programador;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Programador {

	private String nome;
	
	private Linguagem linguagem;
	
	private Integer idade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Programador) {
			Programador other = Programador.class.cast(obj);
			return new EqualsBuilder().append(nome, other.nome).isEquals();
		}
		return false;
	}
	@Override
	public int hashCode() {
		
		return new HashCodeBuilder().append(nome).toHashCode();
	}
	@Override
	public String toString() {
	
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	
}
