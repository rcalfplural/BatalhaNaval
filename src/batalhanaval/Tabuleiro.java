package batalhanaval;

import batalhanaval.exceptions.PosicaoDisparoInvalidoException;
import batalhanaval.exceptions.PosicaoNavioInvalidoException;

public class Tabuleiro {
	public static String alfabeto = "abcdefghijklmnopqrstuvwxyz";
	
	
	private int tamanho;
	private int[][] matriz;
		
	public Tabuleiro(int tamanho) {
		this.tamanho = tamanho;
		this.setMatriz();
	}
	
	public void inserirNavio(Navio navio) throws Exception{
		// TODO fazer verificação para que os navios nao se sobreponham
		char[] colunas = navio.getColunaPos();
		int[] linhas = navio.getLinhaPos();
		for(int i  = 0; i < navio.getTamanho(); i++) {
			int x = alfabeto.indexOf(colunas[i]);
			int y = linhas[i]-1;
			
			if(x < 0 || x > tamanho-1 || y < 0 || y > tamanho-1) throw new PosicaoNavioInvalidoException("Parte do navio "+navio.getId()+" está fora do tabuleiro.");
			
			if(matriz[y][x] != 0) throw new PosicaoNavioInvalidoException("Navio "+navio.getId()+" está sobrepondo navio "+matriz[y][x]);
			matriz[y][x] = navio.getId();
		}
	}
	
	// TODO adicionar metodo de transformar os digitos de entrada em coordenadas
	private static int getVerticalPos(String entrada) {
		return Integer.parseInt(entrada.substring(1))-1;
	}
	
	private static int getHorizontalPos(String entrada) {
		int x = alfabeto.indexOf(entrada.charAt(0));
		if(x < 0) throw new PosicaoDisparoInvalidoException("Posicao invalida");
		return x;
	}
	
	// TODO adicionar metodo de atirar
	public boolean atirar(String entrada) {
		int x = getHorizontalPos(entrada);
		int y = getVerticalPos(entrada);
		
		if(x < 0 || x > tamanho-1 || y < 0 || y > tamanho-1) return false;
		
		return true;
	}
	
	public int[][] getMatriz(){
		return matriz;
	}
	
	private void setMatriz() {
		this.matriz = new int[tamanho][tamanho];
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int largura) {
		this.tamanho = largura;
		this.setMatriz();
	}
	
}
