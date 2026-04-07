package br.com.campoMinado.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.campoMinado.exeption.ExplosaoExeption;
import br.com.campoMinado.exeption.SairExeption;
import br.com.campoMinado.model.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	private Scanner sc = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		execultarJogo();
	}
	private void execultarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloJogo();
				System.out.println("Outra partida? (S/n)");
				String resposta = sc.nextLine();
				if(resposta.equalsIgnoreCase("n")) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
				
			}
			
		} catch (SairExeption e) {
			System.out.println("saiu!!!");
		}finally {
			sc.close();
		}
	}
	private void cicloJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				String digitado = capturarValorDigitado("Digite (X, Y)");
				Iterator<Integer> xy= Arrays.stream(digitado.split(","))
					.map(e->Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("Digite 1-Abrir|2-Desmarcar");
				if(digitado.equalsIgnoreCase("1")) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if(digitado.equalsIgnoreCase("2")) {
					tabuleiro.marcar(xy.next(), xy.next());
				}
			}
			System.out.println("Voce perdeu!!");
		} catch (ExplosaoExeption e) {
			System.out.println("Voce perdeu!!");
		}
	}
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = sc.nextLine();
		if(digitado.equalsIgnoreCase("sair")) {
			throw new SairExeption();
		}
		return digitado;
	}
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
