package gui;


import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.UIManager;
import batalhanaval.Jogo;
import batalhanaval.Navio;
import util.ArrayUtils;

public class Janela extends JFrame {

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
		
		statsString = " Navios Naufragados: "+jogo.getNaviosNalfragados()+"/"+jogo.getTotalNavios()+" - Munição disponível: "+jogo.getTotalDisparos()+"/"+jogo.getMunicaoTotal();
		setTitle("Batalha Naval UI - "+statsString);
		setSize(900, 900);
		setResizable(false);
		setAlwaysOnTop(true);

		this.tabuleiro = new TabuleiroComponent(jogo);
		add(tabuleiro);
		update();
		setVisible(true);
	}
	
	public void update() {
		statsString = " Navios Naufragados: "+jogo.getNaviosNalfragados()+"/"+jogo.getTotalNavios()+" - Munição disponível: "+jogo.getTotalDisparos()+"/"+jogo.getMunicaoTotal();
		setTitle("Batalha Naval UI - "+statsString);
		
		for(int i = 0; i < tabuleiro.getComponentCount(); i++) {
			Component c = tabuleiro.getComponent(i);
			if(!(c instanceof CelulaComponent)) continue;
			
			CelulaComponent celula = (CelulaComponent)c;
			
			if(ArrayUtils.includes(jogo.getDisparosCoordenadas(), celula.getCoordString())) {
				celula.setClicado(true);
				if(celula.getNavioId() > 0) {
					Navio navio = jogo.getNavios()[celula.getNavioId()-1];
					System.out.println("Navio "+navio.getId()+" Tem "+navio.getPartesDestruidas()+" partes destruidas e seu tamanho "+navio.getTamanho());
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
	}
	
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
}
