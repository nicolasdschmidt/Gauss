package gauss;

public class Gauss {
	public static void main(String[] args) {
		Linha l1 = new Linha();
		l1.adicionar(0);
		l1.adicionar(3);
		l1.adicionar(2);
		l1.adicionar(28);
		//System.out.println(l1);
		
		Linha l2 = new Linha();
		l2.adicionar(4);
		l2.adicionar(0);
		l2.adicionar(2);
		l2.adicionar(24);
		//System.out.println(l2);
		
		Linha l3 = new Linha();
		l3.adicionar(2);
		l3.adicionar(3);
		l3.adicionar(0);
		l3.adicionar(16);
		//System.out.println(l3);
		
		Matriz m = new Matriz();
		m.adicionar(l1);
		m.adicionar(l2);
		m.adicionar(l3);
		//System.out.println(m);
		
		if (!VerificadorDeMatriz.divisaoValida(m)) {
			System.out.println("O sistema é indefinido ou impossível");
			System.exit(0);
		}
		
		if (VerificadorDeMatriz.zerosNaDiagonalPrincipal(m))
			m.mudarOrdem();
		
		for (int i = 0; i < m.getElem().size(); i++) {
			m.tornar1(i);
			m.tornar0(i);
			//System.out.println(m);
		}
		
		System.out.println(m.resultado());
		
	}
}
