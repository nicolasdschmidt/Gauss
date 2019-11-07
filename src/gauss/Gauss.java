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
			
			System.out.println("Lendo sistema...");
			
			if (m.getTamanho() <= 26) {
				System.out.println("Sistema lido:");
				System.out.println(m);
			}
			else {
				System.out.println("Sistema muito grande para ser exibido.");
			}
			System.out.println();
			
			System.out.print("Tempo estimado para resolver: ");
			if (m.getTamanho() <= 250)
				System.out.println("<1s");
			else if (m.getTamanho() >= 1500)
				System.out.println(">2min");
			else {
				long tempo = Math.round((0.0703 * Math.pow(m.getTamanho(), 2) - 30.724 * m.getTamanho() + 3203.2));
				int minutos = (int) (tempo / (60 * 1000));
				int segundos = (int) ((tempo / 1000) % 60);
				if (minutos > 0)
					System.out.print(minutos + "min");
				System.out.println(segundos + "s");
			}
			
			System.out.print("Checando validade... ");
			if (!m.divisaoValida()) {
				throw new Exception("O sistema é indeterminado ou impossível");
			}
			System.out.println("OK");
			
			System.out.print("Removendo zeros...   ");
			if (m.primeiroZeroNaDiagonalPrincipal() >= 0)
				m.tirarZeros();
			System.out.println("OK");
			
			System.out.print("Resolvendo...        ");
			m.resolver();
			System.out.println("OK");
			
			System.out.println();
			System.out.println("Resultado:");
			System.out.println(m.resultado());
			double fim = System.currentTimeMillis();
			
			double tempo = fim - inicio;
			int minutos = (int) (tempo / (60 * 1000));
			int segundos = (int) ((tempo / 1000) % 60);
			System.out.print("Tempo total: ");
			if (minutos > 0)
				System.out.print(minutos + "min");
			System.out.println(segundos + "s");
			
		} catch (Exception erro) {
			System.err.println("Erro: " + erro.getMessage());
		}
	}
}
