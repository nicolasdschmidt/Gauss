package gauss;

public class Gauss {
	public static void main(String[] args) {
		try {
			
			Leitor l = new Leitor("Z:\\2o semestre\\TI201 - TP\\Eclipse Workspace\\Gauss\\input.txt");
			Matriz m = l.lerSistema();
		
			if (!m.divisaoValida()) {
				throw new Exception("O sistema é indefinido ou impossível");
			}
			
			if (m.zerosNaDiagonalPrincipal())
				m.mudarOrdem();
			
			for (int i = 0; i < m.getElem().size(); i++) {
				m.tornar1(i);
				m.tornar0(i);
			}
			
			System.out.println(m.resultado());
			
		} catch (Exception erro) {
			System.err.println("Erro: " + erro.getMessage());
		}
	}
}
