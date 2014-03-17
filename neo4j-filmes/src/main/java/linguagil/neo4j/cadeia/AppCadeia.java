package linguagil.neo4j.cadeia;

import linguagil.neo4j.util.SpringUtil;

public class AppCadeia {

	
	public static void main(String[] args) {
		AnimalDAO animalDAO = SpringUtil.INSTANCE.getInstance(AnimalDAO.class);
		animalDAO.adicionarAnimais();
		Animal cadeiaAlimentar = animalDAO.getCadeiaAlimentar();
		Animal animalComedor = animalDAO.getComeAlguem();
		
		System.out.println("Cadeia Alimentar: " +cadeiaAlimentar.getCadeia());
		System.out.println("Animal comedor: " +animalComedor.getCadeia());
		
	}
}
