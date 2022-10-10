package batalhanaval;

import java.util.Random;

import batalhanaval.exceptions.PosicaoNavioInvalidoException;
import batalhanaval.menus.Menu;

public class Main {

	private static final Random random = new Random();
	private static final Menu menuPrincipal = new Menu("Menu Principal", new String[]{"1. Novo Jogo", "2. Jogar",  "3. Sair"});
	private static final Menu menuJogo = new Menu("Insira a posição de disparo. Ex: c4", new String[1]);
	
	private static BatalhaNavalTelas telaAtual;
	private static Jogo jogo;
	private static int nivelAtual;
	
	public static void main(String[] args) {
		telaAtual = BatalhaNavalTelas.MENU_PRINCIPAL;
		nivelAtual = random.nextInt(1, 10);
		
		while(telaAtual != BatalhaNavalTelas.FIM_JOGO) {			
			try {					
				jogo = new Jogo(nivelAtual);
				
				switch(telaAtual) {
					case MENU_PRINCIPAL:
						menuPrincipal();
						break;
					case JOGO:
						jogo(jogo);
						break;
					default:
						break;
				}
			}catch(PosicaoNavioInvalidoException e) {
				System.out.println("Ocorreu um erro ao carregar o nivel "+nivelAtual+". Arquivo possui dados invalidos. "+e.getMessage());
				e.printStackTrace();
				break;
			}
			catch(Exception e) {
				System.out.println("Ocorreu um erro");
				e.printStackTrace();
			}
		}
	}
	
	
	private static void menuPrincipal() throws Exception{
		int escolha;
		menuPrincipal.mostrarOpcoesMenu();
		escolha = menuPrincipal.menuNextInt();
		switch(escolha) {
			case 1:
				nivelAtual = random.nextInt(1,10);
				System.out.println("Carregando nivel: "+nivelAtual);
				jogo = new Jogo(1);
				break;
			case 2:
				telaAtual = BatalhaNavalTelas.JOGO;
				break;
		}
		
	}
	
	private static void jogo(Jogo jogo) {
		String jogada;
		
		try {
			
			printTabuleiro(jogo);
			menuJogo.mostrarOpcoesMenu();
			jogada = menuJogo.menuNextString();
			
			System.out.println(jogada);
			// Verificar jogadas especiais
			if(jogada.equals("00")) {
				telaAtual = BatalhaNavalTelas.MENU_PRINCIPAL;
				return;
			}else if(jogada.equals("CETULHÃO")) {
				telaAtual = BatalhaNavalTelas.FIM_JOGO;
				return;
			}
			
			if(jogo.getTabuleiro().atirar(jogada)) {
				System.out.println("Acertou!");
			}else {
				System.out.println("Errou!");
			}
			
			
		}catch(NumberFormatException e) {
			System.out.println("Esse valor não é válido! Siga esse padrão: <letra><numero> Exemplo: c4");
		}
		catch(Exception e) {
			throw e;
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