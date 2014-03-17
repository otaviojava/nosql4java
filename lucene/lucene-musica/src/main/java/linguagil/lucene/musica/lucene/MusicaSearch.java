package linguagil.lucene.musica.lucene;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import linguagil.lucene.musica.LuceneUtil;
import linguagil.lucene.musica.model.Musica;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.util.Version;


public class MusicaSearch {

	

	private static final String COLUNA_TEXTO = "texto";
	private static final String COLUNA_AUTOR = "Autor";
	private static final String COLUMN_NOME = "nome";

	public List<Musica> getProcurarMusicaTexto(String texto) throws ParseException, IOException {
		
		Query query = new QueryParser(Version.LUCENE_46, COLUNA_TEXTO,
				LuceneUtil.INSTANCE.getAnalyzer()).parse(texto);
		
		return returnMusicas(query);
	}
	
	public List<Musica> getProcurarMusicaAutor(String autor) throws ParseException, IOException {
		Term term = new Term(COLUNA_AUTOR, autor);
		Query query = new TermQuery(term);
		return returnMusicas(query);
	}

	private List<Musica> returnMusicas(Query query) throws IOException {
		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(LuceneUtil.INSTANCE.getDirectory());
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(
				hitsPerPage, true);
		searcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		
		   
		   List<Musica> musicas = new LinkedList<>();
		    for(int i=0;i<hits.length;++i) {
		      int docId = hits[i].doc;
		      Document d = searcher.doc(docId);
		      Musica musica = new Musica();
		      musica.setAutor(d.get(COLUNA_AUTOR));
		      musica.setNome(d.get(COLUMN_NOME));
		      musica.setTexto(d.get(COLUNA_TEXTO));
		      musicas.add(musica);
		    }
		return musicas;
	}
	
	public void indexarAll(List<Musica> musicas) throws IOException {
		IndexWriter indexWriter = LuceneUtil.INSTANCE.getIndexWriter();
		for (Musica musica : musicas) {
			indexWriter.addDocument(indexarMusica(musica));
		}
		indexWriter.close();
	}
	public void indexar(Musica musica) throws IOException {
		IndexWriter indexWriter = LuceneUtil.INSTANCE.getIndexWriter();
		indexWriter.addDocument(indexarMusica(musica));
		indexWriter.close();
	}

	private Document indexarMusica(Musica musica) {
		Document document = new Document();
		document.add(new TextField(COLUMN_NOME, musica.getNome(), Field.Store.YES));
		document.add(new StringField(COLUNA_AUTOR, musica.getAutor(), Field.Store.YES));
		document.add(new TextField(COLUNA_TEXTO, musica.getTexto(), Field.Store.NO));
		return document;
	}
	
}
