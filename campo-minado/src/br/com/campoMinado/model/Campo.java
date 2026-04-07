package br.com.campoMinado.model;

import java.util.ArrayList;
import java.util.List;

import br.com.campoMinado.exeption.ExplosaoExeption;

public class Campo {
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	private List<Campo> vizinhos = new ArrayList<>();
	
	public Campo(int linha,int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	boolean adicionarVizinhos(Campo vizinho) {
		boolean linhaAtual = this.linha != vizinho.linha;
		boolean colunaAtual = this.coluna != vizinho.coluna;
		
		boolean diagonal = linhaAtual && colunaAtual;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	
	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abir() {
		if(!aberto && !marcado) {
			aberto = Boolean.TRUE;
			if(minado) {
				throw new ExplosaoExeption();
			}
			if(visinhancaSegura()) {
				vizinhos.forEach(v -> v.abir());
			}
			return true;
		}else {
			return false;
		}
	}
	
	boolean visinhancaSegura() {
		return vizinhos.stream().noneMatch(v->v.minado);
	}
	public boolean isMarcado() {
		return marcado;
	}
	
	 void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	public boolean isMinado() {
		return minado;
	}
	 void minar() {
		if(!minado) {
			minado = true;
		}
	}
	 
	 public boolean isAberto() {
		 return aberto;
	 }
	 public boolean objetivoAlcancado() {
		 boolean desvendado = ! minado && aberto;
		 boolean protegido = minado && marcado;
		 return desvendado ||protegido;
	 }
	 public void reiniciar() {
		 marcado = false;
		 minado = false;
		 aberto = false;
	 }
	 long minasNaVizinhanca() {
		 return vizinhos.stream().filter(v->v.minado).count();
	 }
	 public String toString() {
		 if(marcado) {
			 return "X";
		 }else if(aberto && minado) {
			 return "*";
		 }else if(aberto && minasNaVizinhanca() > 0) {
			 return Long.toString(minasNaVizinhanca());
		 }else if(aberto) {
			 return " ";
		 }else {
			 return "?";
		 }
	 }
	public int getLinha() {
		return linha;
	}
	public int getColuna() {
		return coluna;
	}
	 
	 
	 
}
