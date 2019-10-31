package gauss;

import java.io.BufferedReader;
import java.io.FileReader;

public class Leitor {
	
	private BufferedReader leitor;
	
	public Leitor (String arquivo) throws Exception {
		this.leitor = new BufferedReader (new FileReader (arquivo));
	}
	
	public Matriz lerSistema () throws Exception {
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
