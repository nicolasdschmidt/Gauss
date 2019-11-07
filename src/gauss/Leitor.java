package gauss;

import java.io.BufferedReader;
import java.io.FileReader;

public static class Leitor {
	
	public static Matriz lerSistema (String arquivo) throws Exception {
		 BufferedReader leitor = new BufferedReader (new FileReader (arquivo));
		int qtdEquacoes = Integer.parseInt(leitor.readLine());
		
		Matriz m = new Matriz();
		
		for (int i = 0; i < qtdEquacoes; i++) {
			String lida = leitor.readLine();
			Linha atual = new Linha(lida);
			m.adicionar(atual);
		}
		
		return m;
	}
	
}
