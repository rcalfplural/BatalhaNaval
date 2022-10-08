package batalhanaval.menus;

import java.util.Scanner;

public class Menu {
	private Scanner scanner;
	private String menuNome;
	private String[] options;
	
	public Menu(String menuNome, String[] options, Scanner scanner) {
		this.options = options;
		this.menuNome = menuNome;
		this.scanner = scanner;
	}
	
	public String[] getOptions() {
		return this.options;
	}
	public String getNome() {
		return this.menuNome;
	}
	
	public int menuOpcaoEscolha() {
		int escolha;
		do {
			escolha = scanner.nextInt();
		}while(escolha < 1 || escolha > options.length);
		
		return escolha;
	}
}

