package gauss;

import java.io.BufferedReader;
import java.io.FileReader;

public class Leitor {
	
	/**
	 * Lê o arquivo texto que tem os dados do sistema
	 * de equações que será resolvido
	 * @param arquivo o endereço do arquivo que será lido
	 */
	public static Matriz lerSistema (String arquivo) throws Exception {//Método para ler o arquivo com o sistema de equações lineares
		
		BufferedReader leitor = new BufferedReader (new FileReader (arquivo));//instanciação de um buffered reader para ler o arquivo
		 
		int qtdEquacoes = Integer.parseInt(leitor.readLine());//número de equações lineares no arquivo texto
		
		Matriz m = new Matriz();//instanciação da matriz que armazenará os dados
		
		for (int i = 0; i < qtdEquacoes; i++) {//enquanto a quantidade de linhas lidas for menor que o número de equações do arquivo
			
			String lida = leitor.readLine();//a linha atual do arquivo é armazenada na String lida
			
			Linha atual = new Linha(lida);//armazenamento dos dados da linha lida em um objeto da classe Linha
			
			m.adicionar(atual);//armazena a Linha atual na matriz
			
		}
		
		return m;//retorna a Matriz m
	}
	
}
