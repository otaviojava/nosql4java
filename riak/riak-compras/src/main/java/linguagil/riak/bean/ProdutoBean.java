package linguagil.riak.bean;

import java.util.List;
import javax.inject.Named;
import linguagil.riak.modelo.Produto;
import linguagil.riak.util.ProdutoUtil;
import org.springframework.stereotype.Component;

@Component
@Named
public class ProdutoBean {

    
    public List<Produto> getProdutos() {
        return ProdutoUtil.INSTANCE.produtos();
    }
}
