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
			Leitor l = new Leitor("input.txt");
			Matriz m = l.lerSistema();
			
			System.out.println("Sistema lido:");
			System.out.println(m);
			System.out.println();
		
			if (!m.divisaoValida()) {
				throw new Exception("O sistema é indeterminado ou impossível");
			}
			
			if (m.zerosNaDiagonalPrincipal())
				m.mudarOrdem();
			
			m.resolver();
				
			System.out.println("Resultado:");
			System.out.println(m.resultado());
			
		} catch (Exception erro) {
			System.err.println("Erro: " + erro.getMessage());
		}
	}
}
