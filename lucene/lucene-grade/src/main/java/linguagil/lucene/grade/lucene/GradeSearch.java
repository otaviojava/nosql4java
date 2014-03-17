package linguagil.lucene.grade.lucene;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import linguagil.lucene.grade.LuceneUtil;
import linguagil.lucene.grade.model.Grade;

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


public class GradeSearch {

	

	private static final String COLUNA_TITULO = "titulo";
	private static final String COLUNA_DESCRICAO = "autor";
	private static final String COLUMN_PALESTRANTE = "palestrante";
	private static final String COLUMN_PALESTRANTE_EXATADO = "palestranteexatado";
	private static final String COLUMN_TUDO = "tudo";
	private static final String COLUMN_MINI_BIO = "minibio";

	public List<Grade> procurarTudo(String texto) throws ParseException, IOException {
		
		Query query = new QueryParser(Version.LUCENE_46, COLUMN_TUDO,
				LuceneUtil.INSTANCE.getAnalyzer()).parse(texto);
		
		return returnMusicas(query);
	}
	
	public List<Grade> procurarConteudo(String texto) throws ParseException, IOException {
		Query query = new QueryParser(Version.LUCENE_46, COLUNA_DESCRICAO,
				LuceneUtil.INSTANCE.getAnalyzer()).parse(texto);
		return returnMusicas(query);
	}
	
	public List<Grade> procurarMiniBio(String miniBio) throws ParseException, IOException {
		Query query = new QueryParser(Version.LUCENE_46, COLUMN_MINI_BIO,
				LuceneUtil.INSTANCE.getAnalyzer()).parse(miniBio);
		return returnMusicas(query);
	}
	
	public List<Grade> procurarNomeExato(String palestrante) throws ParseException, IOException {
		Term term = new Term(COLUMN_PALESTRANTE_EXATADO, palestrante);
		Query query = new TermQuery(term);
		return returnMusicas(query);
	}

	private List<Grade> returnMusicas(Query query) throws IOException {
		int hitsPerPage = 10;
		IndexReader reader = DirectoryReader.open(LuceneUtil.INSTANCE.getDirectory());
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(
				hitsPerPage, true);
		searcher.search(query, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		
		   
		   List<Grade> musicas = new LinkedList<>();
		    for(int i=0;i<hits.length;++i) {
		      int docId = hits[i].doc;
		      Document document = searcher.doc(docId);
		      Grade grade = new Grade();
		      grade.setTitulo(document.get(COLUNA_TITULO));
		      grade.setDescricao(document.get(COLUNA_DESCRICAO));
		      grade.setPalestrante(document.get(COLUMN_PALESTRANTE));
		      grade.setMiniBio(document.get(COLUMN_MINI_BIO));
		      musicas.add(grade);
		    }
		return musicas;
	}
	
	public void indexarAll(List<Grade> grades) throws IOException {
		IndexWriter indexWriter = LuceneUtil.INSTANCE.getIndexWriter();
		for (Grade musica : grades) {
			indexWriter.addDocument(indexarMusica(musica));
		}
		indexWriter.close();
	}
	public void indexar(Grade grade) throws IOException {
		IndexWriter indexWriter = LuceneUtil.INSTANCE.getIndexWriter();
		indexWriter.addDocument(indexarMusica(grade));
		indexWriter.close();
	}

	private Document indexarMusica(Grade musica) {
		Document document = new Document();
		document.add(new TextField(COLUNA_TITULO, musica.getTitulo(), Field.Store.YES));
		document.add(new TextField(COLUNA_DESCRICAO, musica.getDescricao(), Field.Store.YES));
		document.add(new TextField(COLUMN_PALESTRANTE, musica.getPalestrante(), Field.Store.YES));
		document.add(new TextField(COLUMN_MINI_BIO, musica.getMiniBio(), Field.Store.YES));
		
		document.add(new TextField(COLUMN_TUDO, musica.getTudo(), Field.Store.NO));
		document.add(new StringField(COLUMN_PALESTRANTE_EXATADO, musica.getPalestrante(), Field.Store.NO));
		return document;
	}
	
}
