package gui;


import java.awt.Component;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import batalhanaval.Jogo;
import batalhanaval.Navio;
import gui.listeners.WindowListener;
import util.ArrayUtils;

public class Janela extends JFrame {
	private static final Random random = new Random();
	private static final long serialVersionUID = 1L;
	private static String statsString;
	private TabuleiroComponent tabuleiro;
	private Jogo jogo;
	
	public Janela(Jogo jogo){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		this.jogo = jogo;
		
		statsString = "Nivel "+jogo.getLevel()+" -  Navios Naufragados: "+jogo.getNaviosNalfragados()+"/"+jogo.getTotalNavios()+" - Munição disponível: "+jogo.getTotalDisparos()+"/"+jogo.getMunicaoTotal();
		setTitle("Batalha Naval UI - "+statsString);
		setSize(900, 900);
		setResizable(false);
		setAlwaysOnTop(true);

		this.tabuleiro = new TabuleiroComponent(jogo);
		add(tabuleiro);
		update();
		addWindowListener(new WindowListener());
		setVisible(true);
	}
	
	public void update() {
		statsString = "Nivel "+jogo.getLevel()+" Navios Naufragados: "+jogo.getNaviosNalfragados()+"/"+jogo.getTotalNavios()+" - Munição disponível: "+jogo.getTotalDisparos()+"/"+jogo.getMunicaoTotal();
		setTitle("Batalha Naval UI - "+statsString);
		
		for(int i = 0; i < tabuleiro.getComponentCount(); i++) {
			Component c = tabuleiro.getComponent(i);
			if(!(c instanceof CelulaComponent)) continue;
			
			CelulaComponent celula = (CelulaComponent)c;
			
			if(ArrayUtils.includes(jogo.getDisparosCoordenadas(), celula.getCoordString())) {
				celula.setClicado(true);
				if(celula.getNavioId() > 0) {
					Navio navio = jogo.getNavios()[celula.getNavioId()-1];
					if(navio.getTamanho() == navio.getPartesDestruidas()) {
						celula.setNaufragio();
					}else {						
						celula.setAcerto();
					}
				}else {
					celula.setErro();
				}
			}
		}
		
		if(jogo.getMunicaoTotal() == jogo.getTotalDisparos() && jogo.getNaviosNalfragados() < jogo.getTotalNavios()) {
			fimJogo(false);
		}
		else if(jogo.getNaviosNalfragados() == jogo.getTotalNavios()){
			fimJogo(true);
		}
		
	}
	
	private void fimJogo(boolean venceu) {
		jogo.setVenceu(venceu);
		String[] options = {"Novo Jogo", "Sair"};
		String msg = (venceu)?"Você afundou todos os navios da frota inimiga. Você venceu!\nNavios naufragados: ":"Você não foi capaz de derrotar as frotas inimigas.\nVocê perdeu. Navios naufragados: ";
		int type = (venceu)?JOptionPane.PLAIN_MESSAGE:JOptionPane.ERROR_MESSAGE;
		int opt = JOptionPane.showOptionDialog(this, msg+jogo.getNaviosNalfragados()+"/"+jogo.getTotalNavios(),"Fim de Jogo!",  JOptionPane.YES_NO_CANCEL_OPTION, type, null, options, options[1]);
		
		if(opt == 0) {
			try {
				getContentPane().removeAll();
				jogo = new Jogo(random.nextInt(1, 10));
				this.tabuleiro = new TabuleiroComponent(jogo);
				add(tabuleiro);
				validate();
				repaint();
				update();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			Jogo.isSpecialMode = false;
			dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		
	}
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
}
