package linguagil.neo4j.programador;

import linguagil.neo4j.util.SpringUtil;

public class AppProgramador {

	
	public static void main(String[] args) {
		ProgramadorDAO programadorDAO = SpringUtil.INSTANCE.getInstance(ProgramadorDAO.class);

//		programadorDAO.adicionarProgramadores();
		System.out.println(programadorDAO.recomendarAmigosLinguagem(Linguagem.JAVA));
		System.out.println(programadorDAO.recomendarAmigosIdade(26));
		System.out.println(programadorDAO.recomendarAmigosIdadeLinguagem(26,Linguagem.JAVA));
	}
}
