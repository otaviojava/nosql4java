package linguagil.mongodb;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class PrevisaoTempoDAO {
	private static final String ATUALIZACAO = "atualizacao";
	private DBCollection collection;
	
	public void inserir(PrevisaoTempo previsao) {
		BasicDBObject document = new BasicDBObject();
		document.put("cidade", previsao.getCidade());
		document.put("dia", previsao.getDia());
		document.put(ATUALIZACAO, previsao.getAtualizacao());
		document.put("temperatura", previsao.getTemperatura());
		collection.insert(document);
		
	}
	
	public List<PrevisaoTempo> buscar(String cidade) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("cidade", cidade);
		
		DBCursor cursor = collection.find(searchQuery).sort(new BasicDBObject(ATUALIZACAO, -1)).limit(1);
		
		List<PrevisaoTempo> tempos = new LinkedList<>();
		for(DBObject dbObject: cursor.toArray()){
			tempos.add(criarTempo(dbObject));
		}
		return tempos;
	}

	private PrevisaoTempo criarTempo(DBObject dbObject) {
		PrevisaoTempo tempo = new PrevisaoTempo();
		tempo.setAtualizacao(Integer.class.cast(dbObject.get(ATUALIZACAO)));
		tempo.setCidade(String.class.cast(dbObject.get("cidade")));
		tempo.setDia(Date.class.cast(dbObject.get("dia")));
		tempo.setTemperatura(Double.class.cast(dbObject.get("temperatura")));
		return tempo;
	}
	public PrevisaoTempo agregacao() {
		DBObject match = new BasicDBObject("$match", new BasicDBObject("cidade", "Salvador") );
		
		DBObject fields = new BasicDBObject("temperatura", 1);
		fields.put("cidade", 1);
		
		DBObject project = new BasicDBObject("$project", fields );
		
		DBObject groupFields = new BasicDBObject( "_id", "$cidade");
		groupFields.put("average", new BasicDBObject( "$avg", "$temperatura"));
		DBObject group = new BasicDBObject("$group", groupFields);

		AggregationOutput output = collection.aggregate( match, project, group );
		PrevisaoTempo  previsao = new PrevisaoTempo();
		for (DBObject dbObject: output.results()) {
			
			previsao.setCidade(String.class.cast(dbObject.get("_id")));
			previsao.setTemperatura(Double.class.cast(dbObject.get("average")));
		}
		return previsao;
	}
	{
		collection = MongoUtil.INSTANCE.getColecao("previsaoTempo");
	}
}
