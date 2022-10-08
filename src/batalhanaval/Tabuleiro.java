package batalhanaval;

public class Tabuleiro {
	public static String alfabeto = "abcdefghijklmnopqrstuvwxyz";
	
	
	private int tamanho;
	private int[][] matriz;
	
	public Tabuleiro(int tamanho) {
		this.tamanho = tamanho;
		
		this.setMatriz();
	}
	
	public void inserirNavio(Navio navio) {
		// TODO fazer verificação para que os navios nao se sobreponham
		char[] colunas = navio.getColunaPos();
		int[] linhas = navio.getLinhaPos();
		for(int i  = 0; i < navio.getTamanho(); i++) {
			int x = alfabeto.indexOf(colunas[i]);
			int y = linhas[i]-1;
			
			matriz[y][x] = navio.getId();
		}
	}
	
	// TODO adicionar metodo de atirar
	
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
