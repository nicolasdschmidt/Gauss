package gauss;

import java.util.ArrayList;

public class Linha {
	private ArrayList<Double> elementos = new ArrayList<Double>();
	
	public Linha() {
		
	}
	
	public Linha(String linha) {
		String[] temp = linha.split(" ");
		for (String obj : temp) {
			adicionar(Double.parseDouble(obj));
		}
	}
	
	public void adicionar (double elem) {
		elementos.add(elem);
	}
	
	public int getTamanho () {
		return elementos.size();
	}
	
	public void setElem (int index, double valor) {
		elementos.set(index, valor);
	}
	
	public Linha dividir (Linha outraLinha) throws Exception {
		if (this.elementos.size() != outraLinha.elementos.size())
			throw new Exception ("Linhas têm tamanhos diferentes");
		
		Linha divisao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			divisao.adicionar(this.elementos.get(i) / outraLinha.elementos.get(i));
		}
		
		return divisao;
	}
	
	public Linha dividir (double num) {
		Linha divisao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			divisao.adicionar(this.elementos.get(i) / num);
		}
		
		return divisao;
	}
	
	public Linha multiplicar (double num) {
		Linha multiplicacao = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			multiplicacao.adicionar(this.elementos.get(i) * num);
		}
		
		return multiplicacao;
	}
	
	public Linha somar (Linha outraLinha) throws Exception {
		if (this.elementos.size() != outraLinha.elementos.size())
			throw new Exception ("Linhas têm tamanhos diferentes");
		
		Linha soma = new Linha();
		
		for (int i = 0; i < this.elementos.size(); i++) {
			soma.adicionar(this.elementos.get(i) + outraLinha.elementos.get(i));
		}
		
		return soma;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Double> getElem () {
		return (ArrayList<Double>) elementos.clone();
	}
	
	
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
