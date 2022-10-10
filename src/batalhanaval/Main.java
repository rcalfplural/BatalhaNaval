package batalhanaval;

import java.util.Random;
import batalhanaval.menus.Menu;

public class Main {

	private static final Random random = new Random();
	
	private static final Menu menuPrincipal = new Menu("Menu Principal", new String[]{"1. Novo Jogo", "2. Jogar",  "3. Sair"});
	private static final Menu menuJogo = new Menu("Insira a posição de disparo. Ex: c4", new String[1]);
	 
	public static void main(String[] args) {
		Jogo jogo;
		
		
		
		// TODO Auto-generated method stub
		try {					
			jogo = new Jogo(random.nextInt(1, 10));
			menu();
			printTabuleiro(jogo);
			// TODO Criar e mostrar o menu principal ao usuario
			// TODO Criar e mostrar o menu de gameplay ao usuario
			
		}catch(Exception e) {
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
	}
	
	private static void menu() {
		int escolha;
		do {
			menuPrincipal.mostrarOpcoesMenu();
			escolha = menuPrincipal.menuNextInt();
		}while(escolha != 3);
	}
	
	
	private static void printTabuleiro(Jogo jogo) {
		int[][] tabuleiro = jogo.getTabuleiroMatriz();
		int tamanho = jogo.getTabuleiro().getTamanho();
		
		for(int i = 0; i < tamanho; i++) {
			if(i == 0) {
				for(int j = 0; j < tamanho; j++) {
					System.out.print(Tabuleiro.alfabeto.charAt(j)+" ");
				}
				System.out.print("\n");
				for(int j = 0; j < tamanho; j++) {
					System.out.print("==");
				}
				System.out.print("\n");
			}
			for(int j = 0; j < tamanho; j++) {
				if(tabuleiro[i][j] != 0) {					
					System.out.print("- ");
				}else {					
					System.out.print("  ");
				}
			}
			System.out.print("|"+(i+1));
			System.out.print("\n");
		}
	}

}