package br.com.campoMinado.model;

import java.util.ArrayList;
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
	public void abrir(int linha,int coluna) {
		campos.parallelStream()
			.filter(c-> c.getColuna() == coluna)
			.filter(l-> l.getLinha() == linha)
			.findFirst()
			.ifPresent(c-> c.abir());
	}
	public void marcar(int linha,int coluna) {
		campos.parallelStream()
			.filter(c-> c.getColuna() == coluna)
			.filter(l-> l.getLinha() == linha)
			.findFirst()
			.ifPresent(c-> c.alternarMarcacao());
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
		Predicate<Campo>minado = c->c.isMinado();
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
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 
		 int i = 0;
		 
		 for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {
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
