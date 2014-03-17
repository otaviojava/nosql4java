package linguagil.riak.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linguagil.riak.modelo.Produto;

public enum ProdutoUtil {

    INSTANCE;

    private final List<Produto> produtos;
    private final Map<Integer, Produto> mapProdutos;

    {

        produtos = new ArrayList<>();
        mapProdutos = new HashMap<>();
        produtos.add(getProduto("Amor", 300.0, 1));
        produtos.add(getProduto("Prosperidade", 450.0, 2));
        produtos.add(getProduto("Conhecimento", 700.0, 3));
        produtos.add(getProduto("Camisa", 120.0, 4));
        produtos.add(getProduto("Prospteridade", 0.0, 5));

        for (Produto produto : produtos) {
            mapProdutos.put(produto.getCodigo(), produto);
        }
    }

    public List<Produto> produtos() {

        return produtos;

    }
    
    public Produto getProduto(Integer codigo) {
        return mapProdutos.get(codigo);
    }

    public Produto getProduto(String nome, Double valor, Integer codigo) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValorUnitario(valor);
        produto.setCodigo(codigo);
        return produto;
    }
}
