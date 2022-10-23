package batalhanaval;

import java.util.Random;

import batalhanaval.exceptions.PosicaoNavioInvalidoException;
import batalhanaval.menus.Menu;
import gui.Janela;
import util.ArrayUtils;

public class Main {

	private static final Random random = new Random();
	private static final Menu menuPrincipal = new Menu("Menu Principal", new String[]{"1. Novo Jogo - Iniciar um novo jogo em outro tabuleiro", "2. Jogar - jogar",  "3. Sair - encerrar o programa"});
	private static final Menu menuJogo = new Menu("Insira a posição de disparo. Ex: c4", new String[1]);
	
	private static BatalhaNavalTelas telaAtual;
	private static Jogo jogo;
	private static int nivelAtual;
	private static boolean mostrar = false;
	private static boolean venceu = false;
	
	public static void main(String[] args) {
		telaAtual = BatalhaNavalTelas.MENU_PRINCIPAL;
		nivelAtual = random.nextInt(1, 10);

		try {			
			jogo = new Jogo(nivelAtual);
		}catch(Exception exception) {
			System.out.println("Ocorreu um erro: "+exception.getMessage());
			exception.printStackTrace();
		}
		while(telaAtual != BatalhaNavalTelas.FIM_JOGO) {			
			try {					
				
				switch(telaAtual) {
					case MENU_PRINCIPAL:
						menuPrincipal();
						break;
					case JOGO:
						jogo();
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
				break;
			}
		}
		
		
		if(venceu) {
			System.out.println("Parabéns! Você derrotou as frotas inimigas!");
		}else {
			System.out.println("Fim de Jogo. Você não foi capaz de derrotar as frotas inimigas...");
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
				jogo = new Jogo(nivelAtual);
				break;
			case 2:
				telaAtual = BatalhaNavalTelas.JOGO;
				break;
			case 3:
				telaAtual = BatalhaNavalTelas.FIM_JOGO;
				break;
		}
		
	}
	
	private static void jogo() {
		String jogada;
		
		try {
			printTabuleiro();
			// verifica se ganhou o jogo
			if(jogo.getTotalNavios() == jogo.getNaviosNalfragados()) {
				venceu = true;
				telaAtual = BatalhaNavalTelas.FIM_JOGO;
				return;
			}
			
			if(jogo.getTotalDisparos() == jogo.getMunicaoTotal()) {
				venceu = false;
				telaAtual = BatalhaNavalTelas.FIM_JOGO;
				mostrar = true;
				printTabuleiro();
				return;
			}
			
			
			menuJogo.mostrarOpcoesMenu();
			jogada = menuJogo.menuNextString();
			
			
			System.out.println(jogada);
			// Verificar jogadas especiais
			if(jogada.equals("00")) {
				telaAtual = BatalhaNavalTelas.MENU_PRINCIPAL;
				return;
			}else if(jogada.equals("11")) {
				mostrar = !mostrar;
				System.out.println((mostrar)?"CHEAT ATIVADO: MOSTRAR NAVIOS.":"CHEAT DESATIVADO: MOSTRAR NAVIOS.");
				return;
			}else if(jogada.equals("77")) {
				System.out.println("MODO SECRETO ATIVADO!!!!!");
				new Janela(jogo);
			}
			
			if(jogo.atirar(jogada)) {
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
	
	
	private static void printTabuleiro() {
		int[][] tabuleiro = jogo.getTabuleiroMatriz();
		int tamanho = jogo.getTabuleiro().getTamanho();
		
		System.out.println("Navios acertados: "+jogo.getNaviosNalfragados()+"/"+jogo.getTotalNavios());
		System.out.println("Munição: "+jogo.getTotalDisparos()+"/"+jogo.getMunicaoTotal());
		
		
		// percorrer o tabuleiro verticalmente
		for(int i = 0; i < tamanho; i++) {
			// desenhar o cabeçalho do tabuleiro
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
			
			
			// percorrer o tabuleiro horizontalmente
			for(int j = 0; j < tamanho; j++) {
				
				int navioId = tabuleiro[i][j];
				Navio[] navios = jogo.getNavios();
				String coordStr = Tabuleiro.getPosStringFromCoordenates(j, i);
				
				// Verifica se celula ja foi disparada
				if(ArrayUtils.includes(jogo.getDisparosCoordenadas(), coordStr)) {
					if(navioId != 0) {
						Navio navio = navios[navioId-1];
						if(navio.getPartesDestruidas() == navio.getTamanho()) {						
							System.out.print("- ");
						}else {
							System.out.print("x ");
						}
					}else {
						System.out.print("0 ");
					}
				}else if(mostrar) {
					if(navioId != 0) {						
						System.out.print("- ");
					}else {
						System.out.print("0 ");	
					}
				}
				else{
					System.out.print("  ");
				}
			}
			
			// desenha a coluna de numeros ao lado do tabuleiro
			System.out.print("|"+(i+1));
			System.out.print("\n");
		}
	}
}