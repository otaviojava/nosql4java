package linguagil.riak.bean;


import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import linguagil.riak.modelo.CarroCompras;
import linguagil.riak.modelo.Produto;
import linguagil.riak.modelo.Usuario;
import linguagil.riak.service.CarrinhoService;
import linguagil.riak.util.ProdutoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class UsuarioBean implements Serializable{
    
	private static final long serialVersionUID = 7845623725648191303L;

	@Autowired
    private CarrinhoService carrinhoService;

    private UsuarioForm form = new UsuarioForm();
    
    
    public void verificarInfoPessoais() {
        boolean existeUsuario = carrinhoService.existeUsuario(form.getNome());
        form.setExibirEndereco(!existeUsuario);
        form.setExibirCompras(existeUsuario);
    }
    public void inserirEndereco() {
        Usuario usuario = form.toUsuario();
        carrinhoService.armazenar(usuario);
        form.setExibirEndereco(Boolean.FALSE);
        form.setExibirCompras(Boolean.TRUE);
    }
    
    public void adicionarCarro() {
        Produto produto = ProdutoUtil.INSTANCE.getProduto(form.getCodigoProduto());
        carrinhoService.adicionarProduto(form.getNome(), form.getQuantidade(), produto);
        form.setQuantidade(1);
        form.setCodigoProduto(0);
    }
    
    public List<Produto> getCompras() {
        CarroCompras carroCompras = carrinhoService.getCarroCompras(form.getNome());
        return carroCompras.getProdutos();
    }

    public UsuarioForm getForm() {
        return form;
    }

    public void setForm(UsuarioForm form) {
        this.form = form;
    }
    
    
}
