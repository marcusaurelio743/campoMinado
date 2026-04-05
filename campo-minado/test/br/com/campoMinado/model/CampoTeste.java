package br.com.campoMinado.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
