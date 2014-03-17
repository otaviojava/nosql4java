package linguagil.riak.modelo;

import org.apache.commons.lang3.ObjectUtils;

public class Produto implements Cloneable {

    private Integer codigo;

    private String nome;

    private Integer quantidade;

    private Double valorUnitario;
    
    private Double total;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    

    public Double getTotal() {
    	total = valorUnitario * quantidade;
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
    public String toString() {

        return nome.concat(" R$ ").concat(
                valorUnitario == null ? "" : ObjectUtils.defaultIfNull(
                        valorUnitario, 0.d).toString());
    }

    public Produto clone() throws CloneNotSupportedException {
        return (Produto) super.clone();
    }

}
