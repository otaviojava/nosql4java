package linguagil.riak.dao;

import java.util.logging.Logger;

import linguagil.riak.modelo.Usuario;
import linguagil.riak.util.RiakUtil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.cap.UnresolvedConflictException;
import com.basho.riak.client.convert.ConversionException;

@Repository
public class CarrinhoRepository implements InitializingBean{

	
	private Bucket bucket;
	
	private String bucketName;
	
	public void armazenar(Usuario usuario) {
		try {
			  bucket.store(usuario.getNickName(), usuario).execute();
		} catch (RiakRetryFailedException | UnresolvedConflictException | ConversionException e) {
			Logger.getLogger(CarrinhoRepository.class.getName()).severe("Erro ao inserir um livro " +e.getMessage());
		} 
	}
	public void remover(String nickName) {
		
			try {
				bucket.delete(nickName).execute();
			} catch (RiakException e) {
				Logger.getLogger(CarrinhoRepository.class.getName()).severe("Erro ao remover um livro " +e.getMessage());
			}
		
	}
	
	public Usuario recuperar(String nickName) {
		try {
			return bucket.fetch(nickName, Usuario.class).execute();
		} catch (UnresolvedConflictException | RiakRetryFailedException
				| ConversionException e) {
			Logger.getLogger(CarrinhoRepository.class.getName()).severe("Erro ao recuperar um livro " +e.getMessage());
		} 
		return null;
	}

	public String getBucketName() {
		return bucketName;
	}



	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		bucket = RiakUtil.INSTANCE.getBucket(bucketName);
		
	}
	
	
	
}
