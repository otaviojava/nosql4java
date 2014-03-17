package linguagil.riak.util;

import java.util.ArrayList;
import java.util.List;

import linguagil.riak.modelo.Produto;

public enum ProdutoUtil {
INSTANCE;

public Object[] produtos() {
	
	List<Produto> produtos = new ArrayList<>();
	produtos.add(getProduto("Amor", 300.0));
	produtos.add(getProduto("Prosperidade", 450.0));
	produtos.add(getProduto("Conhecimento", 700.0));
	produtos.add(getProduto("Camisa", 120.0));
	produtos.add(getProduto("Prospteridade", 0.0));
	return produtos.toArray();
	
}

public Object[] quantidade() {
	return new Integer[] {1,2,3,4,5,6,7,8,9};
}
public Produto getProduto (String nome, Double valor){
	Produto produto = new Produto();
	produto.setNome(nome);
	produto.setValorUnitario(valor);
	return produto;
}
}
