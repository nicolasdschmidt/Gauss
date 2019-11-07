package gauss;

/**
* <h1>Gauss</h1>
* O programa Gauss implementa uma aplicação que resolve
* sistemas de equações lineares usando o método de
* Gauss-Seidel.
* <p>
* Os dados da equação são extraídos de um arquivo de
* texto {@code input.txt}, que contém os coeficientes das equações que
* compõem o sistema.
* <p>
*/
public class Gauss {
	public static void main(String[] args) {
		try {
			double inicio = System.currentTimeMillis();
			Matriz m = Leitor.lerSistema("input.txt");
			
			System.out.println("Sistema lido:");
			System.out.println(m);
			System.out.println();
			
			if (!m.divisaoValida()) {
				throw new Exception("O sistema é indeterminado ou impossível");
			}
			
			if (m.primeiroZeroNaDiagonalPrincipal() >= 0)
				m.tirarZeros();
			
			m.resolver();
			
			System.out.println("Resultado:");
			System.out.println(m.resultado());
			double fim = System.currentTimeMillis();
			
			System.out.println("Tempo total: " + (fim - inicio) / 1000 + "s");
			
		} catch (Exception erro) {
			System.err.println("Erro: " + erro.getMessage());
		}
	}
}
