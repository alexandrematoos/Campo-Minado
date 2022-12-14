package visaotabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import af.oliveira.cm.ExplosaoException;
import excecao.SairException;
import modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();		
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			while(continuar) {
				cicloDoJogo();
	// Fun��o perguntar� ao usu�rio se quer ir uma nova partida
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
			   }				
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}
	}
	
	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado() ) {
				System.out.println(tabuleiro);
			
		// Fun��o para usu�rio digitar o valor(Quadrado a ser clicado)
				String digitado = capturarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
				.map(e -> Integer.parseInt(e.trim())).iterator();
				
		 digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());					
					
				}
		}
			System.out.println(tabuleiro);	
			System.out.println("Voc� ganhou!");
		} catch(ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voc� perdeu!");
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
	
		// Se o valor digitado for 'Sair' gera um exce��o	
	
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}

