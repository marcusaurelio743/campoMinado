package br.com.campoMinado.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.campoMinado.exeption.ExplosaoExeption;

public class CampoTeste {
	
	private Campo campo; 
	
	@BeforeEach
	void iniciaCampo() {
		campo =  new Campo(3, 3);
	}
	
	@Test
	public void testeVizinhoDistancia1Esquerda() {
		 Campo vizinho = new Campo(3, 2);
		boolean resultado =  campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	public void testeVizinhoDistancia1Direita() {
		 Campo vizinho = new Campo(3, 4);
		boolean resultado =  campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	public void testeVizinhoDistancia1EmCima() {
		 Campo vizinho = new Campo(2, 3);
		boolean resultado =  campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	public void testeVizinhoDistancia1EmBaixo() {
		 Campo vizinho = new Campo(3, 4);
		boolean resultado =  campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	public void testeVizinhoDistancia2() {
		 Campo vizinho = new Campo(2, 2);
		boolean resultado =  campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	@Test
	public void testeNaoVizinho() {
		 Campo vizinho = new Campo(1, 1);
		boolean resultado =  campo.adicionarVizinhos(vizinho);
		assertFalse(resultado);
	}
	@Test
	public void testeValorPadraoAtributoMarcado() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		
	}
	@Test
	public void testeMarcado() {	
		assertFalse(campo.isMarcado());
		
	}
	@Test
	public void testeValorDuasMarcacoes() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
		
	}
	@Test
	public void testeNaoMinadoNaoMarcado() {
		assertTrue(campo.abir());
		
		
	}
	@Test
	public void testeNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abir());
		
		
	}
	@Test
	public void testeMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abir());
		
		
	}
	@Test
	public void testeMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoExeption.class, ()->{
			campo.abir();	
		});
		
		
		
	}
	@Test
	public void testeAbirVizinho1() {
		Campo vizinho1 = new Campo(1, 1);
		Campo vizinho2 = new Campo(2, 2);
		
		vizinho2.adicionarVizinhos(vizinho1);
		campo.adicionarVizinhos(vizinho2);
		campo.abir();
		
		assertTrue(vizinho2.isAberto() && vizinho1.isAberto());
		
		
		
	}
	
	@Test
	public void testeAbirVizinho2() {
		Campo vizinho1 = new Campo(1, 1);
		Campo vizinho2 = new Campo(2, 2);
		Campo vizinho3 = new Campo(1, 1);
		vizinho3.minar();
		
		vizinho2.adicionarVizinhos(vizinho1);
		vizinho2.adicionarVizinhos(vizinho3);
		
		campo.adicionarVizinhos(vizinho2);
		campo.abir();
		
		assertTrue(vizinho2.isAberto() && !vizinho1.isAberto());
		
		
		
	}
	

}
