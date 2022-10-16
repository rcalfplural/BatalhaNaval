package batalhanaval;

import batalhanaval.dados.ArquivoDadosManager;
import util.ArrayUtils;

public class Jogo {
	private Tabuleiro tabuleiro;
	private int level;
	private int totalNavios;
	private int totalDisparos;
	private Navio[] navios;
	private String[] disparosCoordenadas;
	
	public Jogo(int level) throws Exception {
		this.level = level;
		this.tabuleiro = new Tabuleiro(15);
		this.disparosCoordenadas = new String[tabuleiro.getTamanho()*tabuleiro.getTamanho()];
		this.loadNavios();
	}
	
	// TODO adicionar metodos de start - restart e outros
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public int[][] getTabuleiroMatriz(){
		return tabuleiro.getMatriz();
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.disparosCoordenadas = new String[tabuleiro.getTamanho()*tabuleiro.getTamanho()];
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	public boolean atirar(String entrada) {
		if(ArrayUtils.includes(disparosCoordenadas, entrada)) return true;
		
		int navioId = tabuleiro.atirar(entrada);
		
		if(navioId < 0) return false;
		
		disparosCoordenadas[totalDisparos] = entrada;
		totalDisparos++;
		
		navios[navioId].incrementPartesDestruidas();
		
		return true;
	}

	private void loadNavios() throws Exception{
		String levelData = ArquivoDadosManager.lerArquivo(level+".txt");
		String[] boats = levelData.split("\n");
		int totalNavios = ArquivoDadosManager.getNumeroLinhas(level+".txt");
		navios = new Navio[totalNavios];
		
		int navioId = 1;
		for(String s:boats) {
			Navio navio;
			String[] boatProperties = s.split(",");
			
			char[] colunas = new char[boatProperties.length];
			int[] linhas = new int[boatProperties.length];
			
			
			for (int i = 0; i < boatProperties.length; i++) {
				colunas[i] = boatProperties[i].charAt(0);
				linhas[i] = Integer.parseInt(boatProperties[i].substring(1));
			}
			

			navio = new Navio(navioId, boatProperties.length, linhas, colunas);
			tabuleiro.inserirNavio(navio);
			navios[navioId-1] = navio; 
			navioId ++;
		}
		
	}

	public int getTotalNavios() {
		return totalNavios;
	}

	public void setTotalNavios(int totalNavios) {
		this.totalNavios = totalNavios;
	}

	public Navio[] getNavios() {
		return navios;
	}

	public void setNavios(Navio[] navios) {
		this.navios = navios;
	}

	public int getTotalDisparos() {
		return totalDisparos;
	}

	public void setTotalDisparos(int totalDisparos) {
		this.totalDisparos = totalDisparos;
	}

	public String[] getDisparosCoordenadas() {
		return disparosCoordenadas;
	}

	public void setDisparosCoordenadas(String[] disparosCoordenadas) {
		this.disparosCoordenadas = disparosCoordenadas;
	}
	
	
	
	
}