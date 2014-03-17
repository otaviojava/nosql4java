package linguagil.neo4j.amigo;

public class AppAmigo {

	
	public static void main(String[] args) {
		AmigoDAO amigoDAO = new AmigoDAO();
		amigoDAO.adicionarAmigos();
		System.out.println(amigoDAO.executarAmigo("Jonh"));
		System.out.println(amigoDAO.executardoAmigos("Jonh"));
	}
}
