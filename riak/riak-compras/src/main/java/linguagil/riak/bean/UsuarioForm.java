package linguagil.riak.bean;

import java.io.Serializable;

import linguagil.riak.modelo.Endereco;
import linguagil.riak.modelo.Usuario;


public class UsuarioForm implements Serializable {

	private static final long serialVersionUID = -4742076982165553639L;

	private String nome;
    
    private boolean exibirEndereco = false;
    
    private boolean exibirCompras = false;
    
    private String cep;
    
    private String logradouro;
    
    private Integer quantidade;
    
    private Integer codigoProduto;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public boolean isExibirEndereco() {
        return exibirEndereco;
    }

    public void setExibirEndereco(boolean exibirEndereco) {
        this.exibirEndereco = exibirEndereco;
    }

    public boolean isExibirCompras() {
		return exibirCompras;
	}

	public void setExibirCompras(boolean exibirCompras) {
		this.exibirCompras = exibirCompras;
	}

	public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNickName(nome);
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setEndereco(logradouro);
        usuario.setEndereco(endereco);
        cep = null;
        logradouro = null;
        return usuario;

    }
    
    
}
