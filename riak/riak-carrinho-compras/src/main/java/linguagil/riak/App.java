package linguagil.riak;

import linguagil.riak.ui.CarrinhoBean;
import linguagil.riak.util.SpringUtil;

public class App {

	public static void main(String[] args) {

		CarrinhoBean bean = SpringUtil.INSTANCE
				.getInstance(CarrinhoBean.class);

			bean.usuarioExiste();
			
			
			while (bean.getContinuarComprar()) {
				bean.selecionarProduto();	
				
			}
		
	}
}
