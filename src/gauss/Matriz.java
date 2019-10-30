package gauss;

import java.util.ArrayList;

public class Matriz {
	//private int tamanho = 0;
	private ArrayList<Linha> linhas = new ArrayList<Linha>();
	
	public Matriz() {
		
	}
	
	public void adicionar (Linha elem) {
		linhas.add (elem);
	}
	
	public void mudarOrdem() {
		Linha removida = linhas.get(0);
		linhas.remove(0);
		linhas.add(removida);
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
