package batalhanaval;


public class Main {

	public static void main(String[] args) {
		Jogo jogo;
		// TODO Auto-generated method stub
		try {					
			jogo = new Jogo(1);
			printTabuleiro(jogo);
			// TODO Criar e mostrar o menu principal ao usuario
			// TODO Criar e mostrar o menu de gameplay ao usuario
			
		}catch(Exception e) {
			System.out.println("Ocorreu um erro");
			e.printStackTrace();
		}
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