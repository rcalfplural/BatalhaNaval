package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import batalhanaval.Jogo;
import batalhanaval.Tabuleiro;

public class TabuleiroComponent extends JPanel{
	private static final long serialVersionUID = 1L;
	public static Jogo jogo;
	
	public TabuleiroComponent(Jogo jogo) {
		int[][] matriz = jogo.getTabuleiroMatriz();
		
		setLayout(new GridLayout(15, 15));
		
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[i].length; j++) {
				int navioId = matriz[i][j];
				CelulaComponent celula = new CelulaComponent(Tabuleiro.alfabeto.charAt(j)+""+(i+1), matriz[i][j]);
				add(celula);
			}
		}
	}
}
