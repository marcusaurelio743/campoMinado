package br.com.campoMinado;

import br.com.campoMinado.model.Tabuleiro;
import br.com.campoMinado.view.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
	}

}
