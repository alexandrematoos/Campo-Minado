package modelo;

import java.util.ArrayList;
import java.util.List;

import af.oliveira.cm.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	// Atributos para o Campo Minado
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	// Construtor do campo minado
	
	public Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
		// Identificando os vizinhos na tabela do Campo Minado
	
		public boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		// Marca as dintancias entre as linhas do campo minado
		// Imprementa��o para chamar os vizinhos do campo que for selecionado
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
			
		}		
	}
		
		// Criando a funcionalidade de marcar na tabela do Campo Minado
		public void alternarMarcacao() {
			if(!aberto) {
				marcado = !marcado;
			}
		}
		// Atributo para dizer se o campo est� aberto ou n�o		
		
		public boolean abrir() {  
			if(!aberto && !marcado) {
				aberto = true;
				
				if(minado) {
					throw new ExplosaoException();
				}	
			// Ir� abrir todos os vizinhos desde que esteja seguro
					
			if(vizinhancaSegura()) {
					vizinhos.forEach(v -> v.abrir());	
				}
				return true;
			} else {
				return false;
			
				}
			}
			
		// Analise se a vizinha est� segura e n�o est� minado
		// Se retornar falso, o campo est�ra minado
		boolean vizinhancaSegura() {
			return vizinhos.stream().noneMatch(v -> v.minado);
		}
		
		public void minar() {
			minado = true;
		}
		public boolean isMinado() {
			return minado;
		}
		
		public boolean isMarcado() {
			return marcado;
		}
		
		void setAberto(boolean aberto) {
			this.aberto = aberto;
		}
		
		public boolean isAberto() {
			return aberto;
		}

		public boolean isFechado() {
			return !isAberto();
		}
		
		public int getLinha() {
			return linha;
		}
		
		public int getColuna() {
			return coluna;
		}
		
	//	boolean objetivoAlcancado() {
	//		return coluna;
	//	}
		
		boolean objetivoAlcancado() {
			boolean desvendado = !minado && aberto;
			boolean protegido = minado && marcado;
			return desvendado || protegido;
		}
		
		long minasNaVizinhanca() {
			
			return vizinhos.stream().filter(v -> v.minado).count();
		}
		// M�todo para reiniciar o jogo
		
		void reiniciar() {
			aberto = false;
			minado = false;
			marcado = false;
			
		}
		// Apresenta��o do 'X' onde for marcado o campo minado
		// Adicionar COR ****
		public String toString() {
			if(marcado) {
				return "x";
			} else if(aberto && minado) {
				return "*";
			} else if(aberto && minasNaVizinhanca() > 0) {
				return Long.toString(minasNaVizinhanca());
			} else if(aberto) {
				return " ";
			} else {
				return "?";
				
			}
		}
		
		
		
}

