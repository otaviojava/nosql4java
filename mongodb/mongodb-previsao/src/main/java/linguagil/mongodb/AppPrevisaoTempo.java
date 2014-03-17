package linguagil.mongodb;

import java.util.Date;

public class AppPrevisaoTempo {

	public static void main(String[] args) {
		PrevisaoTempoDAO previsaoTempoDAO = new PrevisaoTempoDAO();
		Double temperaturaInicial = 30d;
		for (int i = 1; i< 10;i++){
		PrevisaoTempo tempo = new PrevisaoTempo();
		tempo.setAtualizacao(i);
		tempo.setCidade("Salvador");
		tempo.setDia(new Date());
		tempo.setTemperatura(temperaturaInicial++);
		previsaoTempoDAO.inserir(tempo);
		}
		System.out.println(previsaoTempoDAO.agregacao());
		System.out.println(previsaoTempoDAO.buscar("Salvador"));
		
	}
}
