﻿package gauss;

import java.util.ArrayList;

public class Matriz {
	private ArrayList<Linha> linhas = new ArrayList<Linha>();
	
	public Matriz() {
		
	}
	
	public void adicionar (Linha elem) {
		linhas.add (elem);
	}
	
	public void mudarOrdem() throws Exception{
		/*Linha removida = linhas.get(0);
		linhas.remove(0);
		linhas.add(removida);*/
		if(!daPraTirarZeros())
			throw new Exception("Impossivel de resolver");
	}
	
	public boolean daPraTirarZeros () {
		ArrayList<Linha> copia = (ArrayList<Linha>) linhas.clone();
		while(temZero())
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
	
	public boolean temZero()
	{
		for(int i = 0; i < linhas.size(); i++)
		{
			for(int j = 0; j < linhas.size(); j++)
			{
				if(linhas.get(i).getElem().get(j) == 0)
				{
					return true;
				}
			}
		}
		return false;
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
	
	public void tornar1 (int linha) {
		Linha l = linhas.get(linha);
		double elem = l.getElem().get(linha);
		try {
			l = l.dividir(elem);
		} catch (Exception e) {}
		linhas.set(linha, l);
	}
	
	public void tornar0 (int coluna) {
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
		ArrayList<Double> res = new ArrayList<Double>();
		for (int i = 0; i < linhas.size(); i++) {
			res.add(linhas.get(i).getElem().get(linhas.get(i).getElem().size() - 1));
		}
		
		return res.toString();
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
		return linhas.toString();
	}
}
