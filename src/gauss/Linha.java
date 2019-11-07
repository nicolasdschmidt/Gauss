package gauss;

import java.util.ArrayList;

/**
 * <h1>Linha</h1>
 * Classe que representa e armazena uma equação do sistema
 * de equações lineares. Possui métodos necessários para
 * trabalhar com a equação, incluindo interação com outras
 * Linhas.
 */
public class Linha {
	/**
	 * A lista de elementos da Linha.
	 */
	private ArrayList<Double> elementos = new ArrayList<Double>(); // Armazena os elementos de cada equação linear
	
	/**
	 * Construtor de classe vazio.
	 */
	public Linha() {}
	
	/**
	 * Construtor de classe por String.
	 * @param linha String contendo os coeficientes
	 * de uma equação, com ordem padronizada.
	 */
	public Linha(String linha) {
		String[] temp = linha.split(" "); // armazena os dados da linha recebida no vetor de String temp
		for (String obj : temp) {
			adicionar(Double.parseDouble(obj)); // adiciona todos os dados do vetor temp para dentro do array list elementos
		}
	}
	
	/**
	 * Adiciona um elemento ao fim da lista de elementos.
	 * @param elem o elemento a ser adicionado
	 */
	public void adicionar (double elem) {
		elementos.add(elem); // adiciona o dado elem ao array list elementos
	}
	
	/**
	 * Retorna o tamanho da lista de elementos.
	 * @return o tamanho da lista
	 */
	public int getTamanho () {
		return elementos.size();
	}
	
	/**
	 * Define o valor de uma posição da lista de elementos.
	 * @param index a posição a ser definida
	 * @param valor o valor para essa posição
	 */
	public void setElem (int index, double valor) {
		elementos.set(index, valor); // altera o dado na posição index no array list elementos para o dado valor
	}
	
	/**
	 * Divide cada elemento da linha <code>L<sub>1</sub></code> por
	 * cada elemento de outra linha <code>L<sub>2</sub></code>.
	 * @param outra a linha <code>L<sub>2</sub></code> de divisores
	 * @return uma nova linha <code>L</code>, com o elemento <code>L(n)</code>
	 * sendo o quociente da divisão entre <code>L<sub>1</sub>(n)</code>
	 * e <code>L<sub>2</sub>(n)</code>
	 * @throws Exception caso as linhas não tenham o mesmo
	 * número de elementos
	 */
	public Linha dividir (Linha outra) throws Exception {
		if (this.elementos.size() != outra.elementos.size())
			throw new Exception ("Linhas têm tamanhos diferentes"); // lança exceção se as linhas outra e this tiverem tamanhos diferentes
		
		Linha divisao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			divisao.adicionar(this.elementos.get(i) / outra.elementos.get(i)); // adiciona à Linha divisao o dado do array list elementos de this dividido pelo dado do array list elementos da outra linha
		}
		
		return divisao;
	}
	
	/**
	 * Divide cada elemento da linha <code>L<sub>1</sub></code> por
	 * um mesmo número <code>num</code>.
	 * @param num o divisor
	 * @return uma nova linha <code>L</code>, com o elemento <code>L(n)</code>
	 * sendo o quociente da divisão entre <code>L<sub>1</sub>(n)</code>
	 * e <code>num</code>
	 */
	public Linha dividir (double num) {
		Linha divisao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			divisao.adicionar(this.elementos.get(i) / num); // adiciona à Linha divisao o dado do array list elementos de this dividido por um número double num
		}
	}
		
		return divisao;
	}
	
	/**
	 * Multiplica cada elemento da linha <code>L<sub>1</sub></code> por
	 * um mesmo número <code>num</code>.
	 * @param num o segundo fator
	 * @return uma nova linha <code>L</code>, com o elemento <code>L(n)</code>
	 * sendo o produto da multiplicação entre <code>L<sub>1</sub>(n)</code>
	 * e <code>num</code>
	 */
	public Linha multiplicar (double num) {
		Linha multiplicacao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			multiplicacao.adicionar(this.elementos.get(i) * num); // adiciona à Linha multiplicacao o dado do array list elementos de this multiplicado por um número double num
		}
		
		return multiplicacao;
	}
	
	/**
	 * Soma cada elemento da linha <code>L<sub>1</sub></code> a
	 * cada elemento de outra linha <code>L<sub>2</sub></code>.
	 * @param outra a linha <code>L<sub>2</sub></code> de parcelas
	 * @return uma nova linha <code>L</code>, com o elemento <code>L(n)</code>
	 * sendo a soma entre <code>L<sub>1</sub>(n)</code>
	 * e <code>L<sub>2</sub>(n)</code>
	 * @throws Exception caso as linhas não tenham o mesmo
	 * número de elementos
	 */
	public Linha somar (Linha outra) throws Exception {
		if (this.elementos.size() != outra.elementos.size()) // lança exceção se as linhas outra e this tiverem tamanhos diferentes
			throw new Exception ("Linhas têm tamanhos diferentes");
		
		Linha soma = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			soma.adicionar(this.elementos.get(i) + outra.elementos.get(i)); // adiciona à Linha soma o dado do array list elementos de this somado ao dado do array list elementos da outra linha
		}
		
		return soma;
	}
	
	/**
	 * Verifica se todos os elementos da Linha
	 * são iguais.
	 * @return se todos os elementos são iguais
	 */
	public boolean todosElementosIguais () {
		ArrayList<Double> jaLidos = new ArrayList<Double>();
		jaLidos.add(elementos.get(0));
		for (int i = 1; i < elementos.size(); i++) {
			if (!jaLidos.contains(elementos.get(i)))
				return false;
			jaLidos.add(elementos.get(i));
		}
		return true;
	}
	
	/**
	 * Retorna a lista de elementos contida na Linha
	 * @return a lista de elementos
	 */
	public ArrayList<Double> getElem () {
		return (ArrayList<Double>) elementos.clone();
	}
	
	/**
	 * Construtor de cópia da classe.
	 * @param modelo Linha para copiar
	 * @throws Exception caso o modelo esteja ausente
	 */
	public Linha (Linha modelo) throws Exception {
		if (modelo == null)
			throw new Exception ("Modelo ausente");
		
		for (double obj : modelo.elementos) { // para cada objeto double dentro dos elementos do modelo
			this.adicionar(obj); // adiciona o obj ao array list elementos de this
		}
	}
	
	public Linha clone () {
		Linha clone = null;
		
		try {
			clone = new Linha(this);
		}
		catch (Exception erro) {}
		
		return clone;
	}
	
	public boolean equals (Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (this.getClass() != obj.getClass())
            return false;
		
		Linha l = (Linha) obj;
        if (!this.elementos.equals(l.getElem()))
            return false;

        return true;
	}
	
	public int hashCode () {
		int ret = 666;
		ret = ret * 13 + this.elementos.hashCode();
		
		return ret;
	}
	
	public String toString () {
		return elementos.toString();
	}
}
