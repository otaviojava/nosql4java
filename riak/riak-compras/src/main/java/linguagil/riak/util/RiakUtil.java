package linguagil.riak.util;

import java.util.logging.Logger;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;

public enum RiakUtil {
	INSTANCE;
	
	IRiakClient client;
	{
		try {
			client = RiakFactory.pbcClient();
		} catch (RiakException e) {
			Logger.getLogger(RiakUtil.class.getName()).severe("Erro ao gerar a fábrica do riak " +e.getMessage());
		}

	}
	
	public Bucket getBucket(String bucket) {
		try {
			return client.fetchBucket(bucket).execute();
		} catch (RiakRetryFailedException exception) {
			Logger.getLogger(RiakUtil.class.getName()).severe(
					"Erro ao gerar o bucket " + exception.getMessage());
			return null;
		}
	}
}

