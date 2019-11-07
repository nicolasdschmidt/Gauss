package gauss;

import java.io.BufferedReader;
import java.io.FileReader;

public class Leitor {
	
	/**
	 * Lê o arquivo texto que tem os dados do sistema
	 * de equações que será resolvido
	 * @param arquivo o endereço do arquivo que será lido
	 * @return retorna um objeto da classe Matriz
	 * @throws FileNotFoundException caso o arquivo não exista
	 * @throws IOException caso não seja possível ler alguma linha do arquivo
	 */
	public static Matriz lerSistema (String arquivo) throws Exception {
		
		BufferedReader leitor = new BufferedReader (new FileReader (arquivo));
		 
		int qtdEquacoes = Integer.parseInt(leitor.readLine());
		// lê o número de equações no arquivo, de modo a ler o número correto de linhas
		
		Matriz m = new Matriz();
		
		for (int i = 0; i < qtdEquacoes; i++) {	// enquanto o arquivo não deve acabar
			
			String lida = leitor.readLine();	// lê a próxima linha
			Linha atual = new Linha(lida);		// instancia uma Linha usando a String lida
			m.adicionar(atual);					// armazena a Linha atual na Matriz m
			
		}
		
		leitor.close();
		
		return m; 
	}
	
}
