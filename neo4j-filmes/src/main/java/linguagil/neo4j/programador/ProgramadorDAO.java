package linguagil.neo4j.programador;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
import org.springframework.stereotype.Repository;

@Repository
public class ProgramadorDAO {

	private GraphDatabaseService graphDb;
	private Label programadorLabel;
	
	private String queryIdade;
	
	private String queryLinguagem;
	
	private String queryIdadeLinguagem;
	
	public void adicionarProgramadores() {
		try ( Transaction tx = graphDb.beginTx() )
		{
		Node otavio = criarNode("Otavio", Linguagem.JAVA, 25);
		Node bruno = criarNode("Bruno", Linguagem.JAVA, 42);
		Node edson = criarNode("Edson", Linguagem.JAVA, 30);
		
		Node luiz = criarNode("Luiz", Linguagem.PYTHON, 30);
		Node mauricio = criarNode("Mauricio", Linguagem.PYTHON, 26);
		
		Node marcio = criarNode("Marcio", Linguagem.PHP, 32);
		Node marlon = criarNode("Marlon", Linguagem.RUBY, 30);
		
		otavio.createRelationshipTo(luiz, AmizadeRelacao.CONHECE);
		otavio.createRelationshipTo(marcio, AmizadeRelacao.CONHECE);
		
		luiz.createRelationshipTo(bruno, AmizadeRelacao.CONHECE);
		luiz.createRelationshipTo(mauricio, AmizadeRelacao.CONHECE);
		
		marcio.createRelationshipTo(edson, AmizadeRelacao.CONHECE);
		marcio.createRelationshipTo(marlon, AmizadeRelacao.CONHECE);
		
		tx.success();
		}
		
	}
	
	public List<Programador> recomendarAmigosIdadeLinguagem(Integer idade,Linguagem linguagem) {
		List<Programador> programadores = new LinkedList<>();
		Map<String, Object> param = new HashMap<>();
		param.put("idade", idade);
		param.put("linguagem", linguagem.toString());
		try ( Transaction tx = graphDb.beginTx() )
		{
			ExecutionEngine engine = new ExecutionEngine(graphDb);
			ExecutionResult result = engine.execute( queryIdadeLinguagem, param);
			criarListaProgramacao(programadores, result);
			tx.success();
			}
		return programadores;
	}
	public List<Programador> recomendarAmigosIdade(Integer idade) {
		
		List<Programador> programadores = new LinkedList<>();
		try ( Transaction tx = graphDb.beginTx() )
		{
		ExecutionEngine engine = new ExecutionEngine(graphDb);
		Map<String, Object> param = new HashMap<>();
		param.put("idade", idade);
		ExecutionResult result = engine.execute( queryIdade, param);
		
		criarListaProgramacao(programadores, result);
		tx.success();
		}
		return programadores;
	}

	private void criarListaProgramacao(List<Programador> programadores,
			ExecutionResult result) {
		Iterator<Node> nodes = result.columnAs("amigoAmigo");

		for (Node node : IteratorUtil.asIterable(nodes)) {
			Programador programador = new Programador();
			
			String nome = String.class.cast(node.getProperty("nome"));
			Integer idadeValue = Integer.class.cast(node.getProperty("idade"));
			Linguagem linguagem = Linguagem.valueOf(node.getProperty("linguagem").toString());
			
			programador.setNome(nome);
			programador.setIdade(idadeValue);
			programador.setLinguagem(linguagem);
			programadores.add(programador);
		}
	}
	
	public List<Programador> recomendarAmigosLinguagem(Linguagem linguagem) {
		List<Programador> programadores = new LinkedList<>();
		Map<String, Object> param = new HashMap<>();
		param.put("linguagem", linguagem.toString());
		try ( Transaction tx = graphDb.beginTx() )
		{
		ExecutionEngine engine = new ExecutionEngine(graphDb);
		ExecutionResult result = engine.execute( queryLinguagem,param);
		
		criarListaProgramacao(programadores, result);
		tx.success();
		}
		return programadores;
	}

	private Node criarNode(String nome, Linguagem linguagem, Integer idade){
		Node node = graphDb.createNode();
		node.addLabel(programadorLabel);
		node.setProperty("nome", nome);
		node.setProperty("linguagem", linguagem.toString());
		node.setProperty("idade", idade);
		return node;
	}
	
	public void setQueryIdade(String queryIdade) {
		this.queryIdade = queryIdade;
	}

	public void setQueryLinguagem(String queryLinguagem) {
		this.queryLinguagem = queryLinguagem;
	}

	public void setQueryIdadeLinguagem(String queryIdadeLinguagem) {
		this.queryIdadeLinguagem = queryIdadeLinguagem;
	}

	{
		graphDb = ConnectionUtil.INSTANCE.getGraphDb("Programador");
		programadorLabel = DynamicLabel.label("Programador");
;
	}
}
