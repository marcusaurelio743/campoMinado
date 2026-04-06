package br.com.campoMinado.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private int minas;
	private final List<Campo> campos = new ArrayList<Campo>();
	
	public Tabuleiro(int linhas, int colunas, int minas) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		sorteaMinas();
	}


	private void gerarCampos() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				campos.add(new Campo(i, j));
			}
		}
		
	}
	private void associarVizinhos() {
		for(Campo c1: campos) {
			for(Campo c2: campos) {
				c1.adicionarVizinhos(c2);
			}
		}
		
	}
	
	private void sorteaMinas() {
		Long minasArmadas = 0L;
		Predicate<Campo>minado = c->c.isMarcado();
		do {
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			
		}while(minasArmadas < minas);
	}
	
	 public boolean objetivoAlcancado() {
		
		 return campos.stream().allMatch(c->c.objetivoAlcancado());
	 }
	 public void reiniciar() {
		 campos.stream().forEach(c->c.reiniciar());
		 sorteaMinas();
	 }


}
