package af.oliveira;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import af.oliveira.cm.ExplosaoException;
import modelo.Campo;


public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoRealEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoRealDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	@Test
	void testeVizinhoEmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoEmbaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);		
		assertTrue(resultado);
		
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() {		
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {	
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	

	@Test
	void testeAlternarMarcacaoDuasChamadas() {	
	campo.alternarMarcacao();
	campo.alternarMarcacao();
	assertFalse(campo.isMarcado());
	
	}
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {	
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {	
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadoMarcado() {	
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	void testeAbrirMinadNaooMarcado() {	
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
		
	}
		
		@Test
		void testeAbrirComVizinhos1() {	
			
			Campo campo11 = new Campo(1, 1);
			Campo campo22 = new Campo(2, 2);
			campo22.adicionarVizinho(campo11);
			
			campo.adicionarVizinho(campo22);
			campo.abrir();			
			assertTrue(campo22.isAberto() && campo11.isAberto());
		}
		
		@Test
		void testeAbrirComVizinhos2() {	
			Campo campo11 = new Campo(1, 1);
			Campo campo12 = new Campo(1, 1);
			campo12.minar();
			
			Campo campo22 = new Campo(2, 2);
			campo.adicionarVizinho(campo11);
			campo.adicionarVizinho(campo12);
			
			campo.adicionarVizinho(campo22);	
			campo.abrir();
			assertTrue(campo22.isAberto() && campo11.isFechado());
		}
	}




























