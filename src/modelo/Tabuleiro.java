package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import af.oliveira.cm.ExplosaoException;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();
	
	public Tabuleiro( int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	// Metodo para abrir e procurar a linha e coluna que foi clicada
	
	public void abrir(int linha, int coluna) {
		try {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.abrir());
	} catch (ExplosaoException e) {
		
		// Abrir todos os campos
		campos.forEach(c -> c.setAberto(true));
		throw e;
	}
		}
	// Metodo marcar para alternar a linha e a coluna selecionada
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
		.filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());
	}
	
	
	// Gerando os campos do campo minado
	
	private void gerarCampos() {
			for (int linha = 0; linha < linhas; linha++) {
				for (int coluna = 0; coluna < colunas; coluna++) {
					campos.add(new Campo(linha, coluna));
				}
			}
	}
	
	// Associação das casa vizinhas do tabuleiro do campo minado
	
	private void associarVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}
	// Mineirando a quantidade de minas que ficaram armadas no campo
	
	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		
	do {
	
	// Sempre que entrar no laçado, saberá a quantidade de minas armadas
		int aleatorio = (int) (Math.random() * campos.size());
		campos.get(aleatorio).minar();
		minasArmadas = campos.stream().filter(minado).count();	
	} while(minasArmadas < minas);
  }
	// Função para verificar se o objetivo foi  alcançado(Se ganhou)

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	// Função de reiniciar o jogo
	
	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}
	
	// Função criada para mostrar o tabuleiro
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
			
			sb.append("  ");
		for (int c = 0; c < colunas; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
			 
		}
		 
		sb.append("\n");
		
		int i = 0;
		for (int l = 0; l < linhas; l++) {
			sb.append(l);
			sb.append(" ");			
			for(int c = 0; c < colunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
