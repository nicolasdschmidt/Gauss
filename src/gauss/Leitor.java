package gauss;

import java.io.BufferedReader;
import java.io.FileReader;

public class Leitor {
	
	/**
	 * L� o arquivo texto que tem os dados do sistema
	 * de equa��es que ser� resolvido
	 * @param arquivo o endere�o do arquivo que ser� lido
	 */
	public static Matriz lerSistema (String arquivo) throws Exception {//M�todo para ler o arquivo com o sistema de equa��es lineares
		
		BufferedReader leitor = new BufferedReader (new FileReader (arquivo));//instancia��o de um buffered reader para ler o arquivo
		 
		int qtdEquacoes = Integer.parseInt(leitor.readLine());//n�mero de equa��es lineares no arquivo texto
		
		Matriz m = new Matriz();//instancia��o da matriz que armazenar� os dados
		
		for (int i = 0; i < qtdEquacoes; i++) {//enquanto a quantidade de linhas lidas for menor que o n�mero de equa��es do arquivo
			
			String lida = leitor.readLine();//a linha atual do arquivo � armazenada na String lida
			
			Linha atual = new Linha(lida);//armazenamento dos dados da linha lida em um objeto da classe Linha
			
			m.adicionar(atual);//armazena a Linha atual na matriz
			
		}
		
		return m;//retorna a Matriz m
	}
	
}
