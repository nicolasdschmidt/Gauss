package gauss;

import java.util.ArrayList;

public class VerificadorDeMatriz {
	
	public static boolean divisaoValida (Matriz m) {
		/*for (int i = 0; i < m.getElem().size(); i++) {
			ArrayList<Double> jaLidos = new ArrayList<Double>();
			for (int j = 0; j < m.getElem().size(); j++) {
				double elem1 = m.getElem().get(i).getElem().get(0);
				double elem2 = m.getElem().get(i).getElem().get(j);
				
				if (i != j) {
					double divisao = elem1 / elem2;
					if (jaLidos.contains(divisao))
						return false;
					jaLidos.add(divisao);
				}
			}
		}
		return true;*/
		
		for (int i = 0; i < m.getElem().size(); i++) {
			for (int j = 0; j < m.getElem().size(); j++) {
				Linha l1 = m.getElem().get(i);
				Linha l2 = m.getElem().get(j);
				
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
					
					/*for (double elem : divisao.getElem()) {
						if (jaLidos.contains(elem))
							return false;
						jaLidos.add(elem);
					}*/
				}
			}
		}
		return true;
	}
	
	public static boolean zerosNaDiagonalPrincipal (Matriz m) {
		for (int i = 0; i < m.getElem().size(); i++) {
			Linha l = m.getElem().get(i);
			if (l.getElem().get(i) == 0)
				return true;
		}
		return false;
	}
}
