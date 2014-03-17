package linguagil.riak.modelo;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CarroCompras {

	private List<Produto> produtos;
	
	private Double total = 0D;

	public void addProduto (Produto produto) {
		if (produtos == null) {
			produtos = new LinkedList<>();
		}
		total = total + produto.getQuantidade() * produto.getValorUnitario();
		produtos.add(produto);
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
	
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
