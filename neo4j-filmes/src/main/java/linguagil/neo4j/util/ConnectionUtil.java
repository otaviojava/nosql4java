package linguagil.neo4j.util;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public enum ConnectionUtil {

	INSTANCE;
	
	public GraphDatabaseService getGraphDb(String base) {
		return  new GraphDatabaseFactory()
	    .newEmbeddedDatabaseBuilder( DB_PATH.concat(base) )
	    .setConfig( GraphDatabaseSettings.nodestore_mapped_memory_size, "10M" )
	    .setConfig( GraphDatabaseSettings.string_block_size, "60" )
	    .setConfig( GraphDatabaseSettings.array_block_size, "300" )
	    .newGraphDatabase();
	}

	
	private static final String DB_PATH = System.getProperty("user.home").concat("/Ambiente/db/neo4j/");
}
