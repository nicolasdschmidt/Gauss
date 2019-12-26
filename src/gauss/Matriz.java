package gauss;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * <h1>Matriz</h1>
 * Classe que representa e armazena uma lista de Linhas,
 * representando um sistema de equações lineares. Possui
 * métodos necessários para trabalhar com as Linhas e
 * resolver o sistema.
 */
public class Matriz {
	/**
	 * A lista de Linhas da Matriz.
	 */
	private ArrayList<Linha> linhas = new ArrayList<Linha>();
	
	/**
	 * Se a matriz já foi resolvida.
	 */
	private boolean resolvida = false;
	
	/**
	 * Construtor de classe vazio.
	 */
	public Matriz() {
		
	}
	
	/**
	 * Adiciona uma Linha ao fim da lista de linhas.
	 * @param elem a Linha a ser adicionada
	 */
	public void adicionar (Linha elem) {
		linhas.add (elem);
	}
	
	/**
	 * Reorganiza a matriz de modo que não haja zeros na
	 * diagonal principal.
	 * @throws Exception caso não consiga reorganizar a matriz
	 */
	public void tirarZeros () throws Exception {
		Matriz copia = this.clone();
		int onde = primeiroZeroNaDiagonalPrincipal();
		int iteracoes = 0;
		while(onde >= 0 && iteracoes <= linhas.size())
		{
			if(linhas.get(onde).getElem().get(onde) == 0)	
				for(int i = onde; i < linhas.size(); i++)
					if(linhas.get(i).getElem().get(onde) != 0)
					{
						Linha removida = new Linha(linhas.get(onde));
						linhas.set(onde, linhas.get(i));
						linhas.set(i, removida);
					}
			if(this.equals(copia))
				throw new Exception("Impossível retirar todos os zeros da diagonal principal!");
			onde = primeiroZeroNaDiagonalPrincipal();
			iteracoes++;
		}
		onde = primeiroZeroNaDiagonalPrincipal();
		if (onde >= 0)
			throw new Exception("Impossível retirar todos os zeros da diagonal principal!");
	}
	
	
	/**
	 * Verifica se não existe um dupla de linhas em que
	 * os quocientes da divisão dos valores são todos iguais.
	 * @return se o sistema é válido
	 */
	public boolean divisaoValida () {
		for (int i = 0; i < linhas.size(); i++) {
			for (int j = i; j < linhas.size(); j++) {
				Linha l1 = linhas.get(i);
				Linha l2 = linhas.get(j);
				
				if (i != j) {
					
					if (l1.equals(l2))
						return false;
					
					Linha divisao = null;
					
					try {
						divisao = l1.dividir(l2);
					} catch (Exception e) {}
					
					if (divisao.todosElementosIguais())
						return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Retorna a posição do primeiro zero encontrado
	 * na diagonal principal do sistema de equações.
	 * @return a posição do primeiro zero na diagonal principal
	 */
	public int primeiroZeroNaDiagonalPrincipal () {
		for (int i = 0; i < linhas.size(); i++) {
			Linha l = linhas.get(i);
			if (l.getElem().get(i) == 0)
				return i; // retorna a posição do zero
		}
		return -1; // se não encontrar, retorna -1
	}
	
	/**
	 * Executa os métodos necessários para
	 * resolver o sistema de equações.
	 */
	public void resolver () {
		for (int i = 0; i < linhas.size(); i++) {
			tornarUm(i);
			tornarZero(i);
		}
		this.resolvida = true;
	}
	
	/**
	 * Passo 6 do método de Gauss-Seidel.
	 * <p>
	 * Torna 1 os elementos da diagonal principal
	 * na linha especificada, dividindo toda a
	 * linha pelo elemento a ser tornado 1.
	 * @param linha a linha na qual executar esse procedimento
	 */
	private void tornarUm (int linha) {
		Linha l = linhas.get(linha);
		double elem = l.getElem().get(linha);
		try {
			l = l.dividir(elem);
		} catch (Exception e) {}
		linhas.set(linha, l);
	}
	
	/**
	 * Passo 7 do método de Gauss-Seidel.
	 * <p>
	 * Torna 0 os elementos da coluna especificada,
	 * com exceção do elemento que está na diagonal
	 * principal, que resulta em 1.
	 * @param coluna a coluna na qual executar esse procedimento
	 */
	private void tornarZero (int coluna) {
		Linha lImplantada = linhas.get(coluna);
		for (int i = 0; i < linhas.size(); i++) {
			if (i != coluna) {
				Linha lAtual = linhas.get(i);
				if (lAtual.getElem().get(coluna) != 0) {
					try {
						linhas.set(i, lAtual.somar((lImplantada.multiplicar(lAtual.getElem().get(coluna) * -1))));
					} catch (Exception e) {}
				}
			}
		}
	}
	
	/**
	 * Se chamado depois do método {@code resolver()},
	 * retorna as soluções do sistema de equações.
	 * @return a última coluna da matriz
	 * @throws Exception caso o sistema ainda não tenha sido resolvido
	 */
	public String resultado () throws Exception {
		if (!resolvida)
			throw new Exception ("O sistema ainda não foi resolvido");
		String ret = "";
		for (int i = 0; i < linhas.size(); i++) {
			if (i < 26)
				ret += (char)(i + 'a');
			else
				ret += "(x" + (i) + ")";
			ret += " = ";
			ret += new DecimalFormat("##.###").format(linhas.get(i).getElem().get(linhas.get(i).getElem().size() - 1));
			ret += "\n";
		}

		return ret;
	}
	
	/**
	 * Retorna o tamanho do sistema.
	 * @return o número de Linhas na matriz
	 */
	public int getTamanho () {
		return linhas.size();
	}
	
	/**
	 * Retorna a lista de Linhas contida na Matriz.
	 * @return a lista de Linhas
	 */
	public ArrayList<Linha> getElem () {
		return (ArrayList<Linha>) linhas.clone();
	}
	
	/**
	 * Construtor de cópia da classe.
	 * @param modelo Matriz para copiar
	 * @throws Exception caso o modelo esteja ausente
	 */
	public Matriz (Matriz modelo) throws Exception {
		if (modelo == null)
			throw new Exception ("Modelo ausente");
		
		for (Linha obj : modelo.linhas) {
			this.adicionar(obj);
		}
	}
	
	public Matriz clone () {
		Matriz clone = null;
		
		try {
			clone = new Matriz(this);
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
		
		Matriz m = (Matriz) obj;
        if (!this.linhas.equals(m.getElem()))
            return false;

        return true;
	}
	
	public int hashCode () {
		int ret = 666;
		ret = ret * 13 + this.linhas.hashCode();
		
		return ret;
	}
	
	public String toString () {
		String ret = "";
		for (int i = 0; i < linhas.size(); i++) {
			int numValores = 0;
			for (int j = 0; j < linhas.size(); j++) {
				double valor = linhas.get(i).getElem().get(j);
				if (valor != 0) {
					if (numValores != 0) {
						if (valor > 0)
							ret += " + ";
						else
							ret += " - ";
					}
					ret += new DecimalFormat("##.###").format(Math.abs(valor));
					ret += (char)(j + 'a');
					numValores++;
				}
			}
			ret += " = ";
			ret += new DecimalFormat("##.###").format(linhas.get(i).getElem().get(linhas.get(i).getElem().size() - 1));
			if (i < linhas.size() - 1)
				ret += "\n";
		}
		return ret;
	}
}
