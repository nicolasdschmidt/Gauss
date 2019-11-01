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
	 * Construtor de classe vazio.
	 */
	public Matriz() {
		
	}
	
	/**
	 * Adiciona uma Linha ao fim da lista de linhas
	 * @param elem a Linha a ser adicionada
	 */
	public void adicionar (Linha elem) {
		linhas.add (elem);
	}
	
	public void mudarOrdem() throws Exception{
		if(!daPraTirarZeros())
			throw new Exception("Impossivel de resolver");
	}

	public boolean daPraTirarZeros () {
		ArrayList<Linha> copia = (ArrayList<Linha>) linhas.clone();
		while(zerosNaDiagonalPrincipal())
		{
			for(int i = 0; i < linhas.size(); i++)
			{
				if(linhas.get(i).getElem().get(i) == 0)	
					for(int i2 = 0; i2 < linhas.size(); i2++)
						if(linhas.get(i).getElem().get(i) == 0)
						{
							Linha removida = linhas.get(i);
							linhas.set(i, linhas.get(i2));
							linhas.set(i2, removida);
						}
			}
			if(linhas.equals(copia))
				return false;
		}
		return true;
	}
	
	public boolean divisaoValida () {
		for (int i = 0; i < linhas.size(); i++) {
			for (int j = 0; j < linhas.size(); j++) {
				Linha l1 = linhas.get(i);
				Linha l2 = linhas.get(j);
				
				if (i != j) {
					Linha divisao = null;
					
					try {
						divisao = l1.dividir(l2);
					} catch (Exception e) {}
					
					ArrayList<Double> jaLidos = new ArrayList<Double>();
					
					for (int elem = 0; elem < divisao.getElem().size() - 1; elem++) {
						if (jaLidos.contains(divisao.getElem().get(elem)))
							return false;
						jaLidos.add(divisao.getElem().get(elem));
					}
				}
			}
		}
		return true;
	}
	
	public boolean zerosNaDiagonalPrincipal () {
		for (int i = 0; i < linhas.size(); i++) {
			Linha l = linhas.get(i);
			if (l.getElem().get(i) == 0)
				return true;
		}
		return false;
	}
	
	public void resolver () {
		for (int i = 0; i < linhas.size(); i++) {
			tornar1(i);
			tornar0(i);
		}
	}
	
	private void tornar1 (int linha) {
		Linha l = linhas.get(linha);
		double elem = l.getElem().get(linha);
		try {
			l = l.dividir(elem);
		} catch (Exception e) {}
		linhas.set(linha, l);
	}
	
	private void tornar0 (int coluna) {
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
	
	public String resultado () {
		String ret = "";
		for (int i = 0; i < linhas.size(); i++) {
			ret += (char)(i+'a');
			ret += " = ";
			ret += new DecimalFormat("##.###").format(linhas.get(i).getElem().get(linhas.get(i).getElem().size() - 1));
			ret += "\n";
		}

		return ret;
	}
	
	public int getTamanho () {
		return linhas.size();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Linha> getElem () {
		return (ArrayList<Linha>) linhas.clone();
	}
	
	
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
					ret += (char)(j+'a');
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
