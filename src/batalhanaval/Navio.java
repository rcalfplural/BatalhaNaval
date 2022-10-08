package batalhanaval;

public class Navio {
	private int id;
	private int tamanho;
	private int[] linhaPos;
	private char[] colunaPos;
	
	public Navio(int id, int tamanho, int[] linhas, char[] colunas) {
		this.id = id;
		this.colunaPos = colunas;
		this.linhaPos = linhas;
		this.tamanho = tamanho;
	}
	
	@Override
	public String toString() {
		String posString = "";
		
		for (int i = 0; i < tamanho; i++) {
			posString = posString.concat(""+colunaPos[i]+""+linhaPos[i]+" ");
		}
		
		
		return "Navio "+id+" na posicao "+posString;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public int[] getLinhaPos() {
		return linhaPos;
	}
	public void setLinhaPos(int[] linhaPos) {
		this.linhaPos = linhaPos;
	}
	public char[] getColunaPos() {
		return colunaPos;
	}
	public void setColunaPos(char[] colunaPos) {
		this.colunaPos = colunaPos;
	}	
}
