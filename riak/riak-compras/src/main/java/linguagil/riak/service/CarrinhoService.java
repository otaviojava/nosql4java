package linguagil.riak.service;

import linguagil.riak.dao.CarrinhoRepository;
import linguagil.riak.modelo.CarroCompras;
import linguagil.riak.modelo.Produto;
import linguagil.riak.modelo.Usuario;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService  {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public void armazenar(Usuario usuario) {
		carrinhoRepository.armazenar(usuario);
	}
	public void remover(String nickName) {
		carrinhoRepository.remover(nickName);
	}
	
	public Usuario recuperar(String nickName) {
		return carrinhoRepository.recuperar(nickName);
	}
        
        public boolean  existeUsuario(String nickName){
            return recuperar(nickName) != null;
        }
	public void adicionarProduto(String nickName, Integer quantidade,
			Produto produtoSelecionado) {

		Usuario usuario = carrinhoRepository.recuperar(nickName);
		Produto produto = ObjectUtils.clone(produtoSelecionado);
		produto.setQuantidade(quantidade);
		usuario.getCarroCompras().addProduto(produto);
		
		carrinhoRepository.armazenar(usuario);
		
	}
        
        public CarroCompras getCarroCompras(String nickName) {
            Usuario usuario = carrinhoRepository.recuperar(nickName);
            return usuario.getCarroCompras();
        }
        
	
}
