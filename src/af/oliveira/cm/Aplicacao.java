package af.oliveira.cm;

import modelo.Tabuleiro;
import visaotabuleiro.TabuleiroConsole;

public class Aplicacao {
	
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);	
		new TabuleiroConsole(tabuleiro);
	
	}

}
