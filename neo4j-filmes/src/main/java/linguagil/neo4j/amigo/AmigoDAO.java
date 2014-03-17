package linguagil.neo4j.amigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import linguagil.neo4j.util.ConnectionUtil;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.helpers.collection.IteratorUtil;

public class AmigoDAO {

	private GraphDatabaseService graphDb;
	private Label amigoLabel;
	
	public void adicionarAmigos() {
		try ( Transaction tx = graphDb.beginTx() )
		{
		Node jonh = criarNode("Jonh");
		Node sara = criarNode("Sara");
		Node joe = criarNode("Joe");
		Node maria = criarNode("Maria");
		Node steve = criarNode("Steve");
		
		jonh.createRelationshipTo(sara, AmizadeRelacao.CONHECE);
		jonh.createRelationshipTo(joe, AmizadeRelacao.CONHECE);
		
		sara.createRelationshipTo(maria, AmizadeRelacao.CONHECE);
		
		joe.createRelationshipTo(steve, AmizadeRelacao.CONHECE);
		
		tx.success();
		}
		
	}
	public List<Amigo> executarAmigo(String nome) {
		List<Amigo> amigos = new ArrayList<>();
		try ( Transaction tx = graphDb.beginTx() )
		{
		String query = "MATCH (me:Amigo{nome:{nome}})-->(amigo) return amigo";
		Map<String, Object> param = new HashMap<>();
		param.put("nome", nome);
		ExecutionEngine engine = new ExecutionEngine(graphDb);
		ExecutionResult result = engine.execute( query, param);
		Iterator<Node> nodes = result.columnAs("amigo");
		
		for ( Node node : IteratorUtil.asIterable( nodes ) ) {
			
			amigos.add(new Amigo(node.getProperty("nome").toString()));
		}
		tx.success();
		}
		return amigos;
	}
	
	public List<Amigo> executardoAmigos(String nome) {
		String query = "MATCH (me:Amigo{nome:{nome}})-->(amigo)-->(amigoAmigo) return amigoAmigo";
		Map<String, Object> param = new HashMap<>();
		param.put("nome", nome);
		List<Amigo> amigos = new ArrayList<>();
		try ( Transaction tx = graphDb.beginTx() )
		{
		ExecutionEngine engine = new ExecutionEngine(graphDb);
		ExecutionResult result = engine.execute( query, param);
		Iterator<Node> nodes = result.columnAs("amigoAmigo");
		
		for ( Node node : IteratorUtil.asIterable( nodes ) ) {
			amigos.add(new Amigo(node.getProperty("nome").toString()));
		}
		tx.success();
		}
		return amigos;
	}
	
	private Node criarNode(String nome){
		Node node = graphDb.createNode();
		node.addLabel(amigoLabel);
		node.setProperty("nome", nome);
		return node;
	}
	
	{
		graphDb = ConnectionUtil.INSTANCE.getGraphDb("Amizade");
		amigoLabel = DynamicLabel.label("Amigo");
;
	}
}
