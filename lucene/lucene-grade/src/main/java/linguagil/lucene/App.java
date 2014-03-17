package linguagil.lucene;

import java.io.IOException;

import linguagil.lucene.grade.LuceneUtil;
import linguagil.lucene.grade.lucene.GradeSearch;
import linguagil.lucene.grade.model.Grades;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	public static void main(String[] args) throws IOException, ParseException {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
		Grades grades = ctx.getBean(Grades.class);
		GradeSearch search = new GradeSearch();
		
		search.indexarAll(grades.getGrades());	
		LuceneUtil.INSTANCE.finalizar();
		System.out.println(search.procurarTudo("Java"));
		System.out.println(search.procurarMiniBio("Java"));
		
		System.out.println(search.procurarConteudo("NOSQL"));
		System.out.println(search.procurarNomeExato("Otávio Santana"));
	}
	
}