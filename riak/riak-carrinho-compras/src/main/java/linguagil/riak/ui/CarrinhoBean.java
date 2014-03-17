package linguagil.riak.ui;

import javax.swing.JOptionPane;

import linguagil.riak.modelo.Endereco;
import linguagil.riak.modelo.Produto;
import linguagil.riak.modelo.Usuario;
import linguagil.riak.service.CarrinhoService;
import linguagil.riak.util.ProdutoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CarrinhoBean {
	
	@Autowired
	private CarrinhoService service;
	
	private CarrinhoForm form = new CarrinhoForm();
	
	
	public void usuarioExiste () {
		String nickName = JOptionPane.showInputDialog("Qual é o seu nome?");
		if (service.recuperar(nickName) == null) {
			String logradouro = JOptionPane.showInputDialog("Informe o seu endereco");
			String cep = JOptionPane.showInputDialog("Informe o seu endereco");
			Endereco endereco = new Endereco();
			endereco.setCep(cep);
			endereco.setEndereco(logradouro);
			Usuario usuario = new Usuario();
			usuario.setEndereco(endereco);
			usuario.setNickName(nickName);
			service.armazenar(usuario);
			
		}
		form.setUsuario(nickName);
	}
	
	public void selecionarProduto() {
		Produto produtoSelecionado = (Produto) JOptionPane.showInputDialog(
				null, "Selecione um Produto", "Realize sua compra",
				JOptionPane.PLAIN_MESSAGE, null, ProdutoUtil.INSTANCE.produtos(),
				ProdutoUtil.INSTANCE.produtos()[0]);
		
		Integer quantidade = (Integer) JOptionPane.showInputDialog(
				null, "Selecione um Produto", "Realize sua compra",
				JOptionPane.PLAIN_MESSAGE, null, ProdutoUtil.INSTANCE.quantidade(),
				ProdutoUtil.INSTANCE.quantidade()[0]);
		
		service.adicionarProduto(form.getUsuario(), quantidade, produtoSelecionado);
	}
	
	public void zerarUsuario() {
		form = new CarrinhoForm();
	}

	public boolean getContinuarComprar() {
		int valor = JOptionPane.showConfirmDialog(null, "Coninuar Comprando");
		return  valor == 0;
	}
	
}
