package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import batalhanaval.Jogo;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	
	public Janela(Jogo jogo){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		setTitle("Batalha Naval UI");
		setSize(900, 900);
		setResizable(false);
		setAlwaysOnTop(true);

		add(new TabuleiroComponent(jogo));
		
		setVisible(true);
	}
	
}
