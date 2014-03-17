package linguagil.riak.modelo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Usuario implements Serializable {

	private static final long serialVersionUID = -4366861620796379236L;

	private String nickName;
	
	private CarroCompras carroCompras;
	
	private Endereco endereco;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public CarroCompras getCarroCompras() {
		if (carroCompras == null) {
			carroCompras = new CarroCompras();
		}
		return carroCompras;
	}

	public void setCarroCompras(CarroCompras carroCompras) {
		this.carroCompras = carroCompras;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
	
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
