package linguagil.neo4j.cadeia;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Animal {

	public static final Animal VAZIO = new AnimalVazio(StringUtils.EMPTY);

	private String nome;
	
	private Animal animal = VAZIO;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getCadeia() {
		
		StringBuilder descricao = new StringBuilder();
		descricao.append(nome).append(" --> ").append(animal.getCadeia());
		return descricao.toString();
	}

	@Override
	public String toString() {
	
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public Animal() {
	}
	
	public Animal(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Animal) {
			Animal other = Animal.class.cast(obj);
			return new EqualsBuilder().append(nome,other.nome).isEquals();
		}
		return false;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).toHashCode();
	}
	
	static class AnimalVazio extends Animal {
		AnimalVazio (String nome) {
			super(nome);
		}
		@Override
		public String getCadeia() {
			return StringUtils.EMPTY;
		}
	}
}
