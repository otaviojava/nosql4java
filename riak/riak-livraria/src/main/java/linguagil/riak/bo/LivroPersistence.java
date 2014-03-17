package linguagil.riak.bo;

import java.util.logging.Logger;

import linguagil.riak.modelo.Livro;
import linguagil.riak.util.RiakUtil;

import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.cap.UnresolvedConflictException;
import com.basho.riak.client.convert.ConversionException;

public class LivroPersistence {

	
	private static final String BUCKET_NAME = "livraria";
	private Bucket bucket;
	
	public void armazenar(Livro livro) {
		try {
			  bucket.store(livro.getISBN(), livro).execute();
		} catch (RiakRetryFailedException | UnresolvedConflictException | ConversionException e) {
			Logger.getLogger(LivroPersistence.class.getName()).severe("Erro ao inserir um livro " +e.getMessage());
		} 
	}
	public void remover(String isbn) {
		
			try {
				bucket.delete(isbn).execute();
			} catch (RiakException e) {
				Logger.getLogger(LivroPersistence.class.getName()).severe("Erro ao remover um livro " +e.getMessage());
			}
		
	}
	
	public Livro recuperar(String isbn) {
		try {
			return bucket.fetch(isbn, Livro.class).execute();
		} catch (UnresolvedConflictException | RiakRetryFailedException
				| ConversionException e) {
			Logger.getLogger(LivroPersistence.class.getName()).severe("Erro ao recuperar um livro " +e.getMessage());
		} 
		return null;
	}


	{
		bucket = RiakUtil.INSTANCE.getBucket(BUCKET_NAME);
	}
	
	
	
}
