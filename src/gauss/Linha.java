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
	private ArrayList<Double> elementos = new ArrayList<Double>();
	
	/**
	 * Construtor de classe vazio.
	 */
	public Linha() {
		
	}
	
	/**
	 * Construtor de classe por String.
	 * @param linha String contendo os coeficientes
	 * de uma equação, com ordem padronizada.
	 */
	public Linha(String linha) {
		String[] temp = linha.split(" ");
		for (String obj : temp) {
			adicionar(Double.parseDouble(obj));
		}
	}
	
	/**
	 * Adiciona um elemento ao fim da lista de elementos.
	 * @param elem o elemento a ser adicionado
	 */
	public void adicionar (double elem) {
		elementos.add(elem);
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
		elementos.set(index, valor);
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
			throw new Exception ("Linhas têm tamanhos diferentes");
		
		Linha divisao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			divisao.adicionar(this.elementos.get(i) / outra.elementos.get(i));
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
			divisao.adicionar(this.elementos.get(i) / num);
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
			multiplicacao.adicionar(this.elementos.get(i) * num);
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
		if (this.elementos.size() != outra.elementos.size())
			throw new Exception ("Linhas têm tamanhos diferentes");
		
		Linha soma = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			soma.adicionar(this.elementos.get(i) + outra.elementos.get(i));
		}
		
		return soma;
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
		
		for (double obj : modelo.elementos) {
			this.adicionar(obj);
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
        if (this.elementos != l.getElem())
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
