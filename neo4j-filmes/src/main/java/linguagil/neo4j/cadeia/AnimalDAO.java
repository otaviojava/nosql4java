package linguagil.neo4j.cadeia;

import java.util.Iterator;

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
public class AnimalDAO {

	private GraphDatabaseService graphDb;
	private Label animalLabel;

	private String comeAlguemQuery;
	
	private String cadeiaAlimentarQuery;

	public void adicionarAnimais() {
		try ( Transaction tx = graphDb.beginTx() )
		{
		Node homem = criarNode("Homem");
		Node leao = criarNode("Leão");
		Node zebra = criarNode("raposa");
		Node planta = criarNode("rato");
		
		homem.createRelationshipTo(leao, CadeiaRelacao.COME);
		
		leao.createRelationshipTo(zebra, CadeiaRelacao.COME);
		zebra.createRelationshipTo(planta, CadeiaRelacao.COME);
		
		tx.success();
		}
		
	}
	
	public Animal getComeAlguem() {
		Animal top = Animal.VAZIO;
		try ( Transaction tx = graphDb.beginTx() )
		{
			top = criarCadeia(top, comeAlguemQuery);
			tx.success();
		}
		
		return top;
	}
	
	public Animal getCadeiaAlimentar() {
		Animal top = Animal.VAZIO;
		try ( Transaction tx = graphDb.beginTx() )
		{
			top = criarCadeia(top, cadeiaAlimentarQuery);
			tx.success();
		}
		
		return top;
	}


	private Animal criarCadeia(Animal top, String query) {
		ExecutionEngine engine = new ExecutionEngine( graphDb );
		ExecutionResult result = engine.execute( query);
		Iterator<Node> nodes = result.columnAs( "animal" );
		
		Animal animal = Animal.VAZIO;
		
		for ( Node node : IteratorUtil.asIterable( nodes ) )
		{
		 if(animal.equals(Animal.VAZIO)){
			 animal = new Animal();
			 animal.setNome(node.getProperty("nome").toString());
			 top = animal;
		 } else {
			 Animal subAnimal = new Animal(node.getProperty("nome").toString());
			 animal.setAnimal(subAnimal);
			 animal = subAnimal;
		 }
		 
		 
		}
		return top;
	}
	
	private Node criarNode(String nome){
		Node node = graphDb.createNode();
		node.addLabel(animalLabel);
		node.setProperty("nome", nome);
		return node;
	}
	
	public void setComeAlguemQuery(String comeAlguemQuery) {
		this.comeAlguemQuery = comeAlguemQuery;
	}

	public void setCadeiaAlimentarQuery(String cadeiaAlimentarQuery) {
		this.cadeiaAlimentarQuery = cadeiaAlimentarQuery;
	}

	{
		graphDb = ConnectionUtil.INSTANCE.getGraphDb("CadeiaAlimentar");
		animalLabel = DynamicLabel.label("Animal");
;
	}
}
