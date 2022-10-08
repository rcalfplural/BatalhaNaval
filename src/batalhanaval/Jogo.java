package batalhanaval;

import batalhanaval.dados.ArquivoDadosManager;

public class Jogo {
	private Tabuleiro tabuleiro;
	private int level;
	
	public Jogo(int level) throws Exception {
		this.level = level;
		this.tabuleiro = new Tabuleiro(15);
		this.loadNavios();
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public int[][] getTabuleiroMatriz(){
		return tabuleiro.getMatriz();
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	private void loadNavios() throws Exception{
		String levelData = ArquivoDadosManager.lerArquivo(level+".txt");
		String[] boats = levelData.split("\n");
		
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
			navioId ++;
		}
	}
	
}
