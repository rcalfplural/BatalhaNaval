package gui.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import batalhanaval.Jogo;
import gui.CelulaComponent;
import gui.Janela;

public class CelulaListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getButton() != MouseEvent.BUTTON1) return;
		
		
		Object button = e.getComponent();
		
		if(!(button instanceof CelulaComponent)) return;
		
		CelulaComponent buttonComponent = (CelulaComponent)button;
		
		if(buttonComponent.foiClicado()) return;
		
		
		JFrame janelaComponent = (JFrame)SwingUtilities.getRoot(buttonComponent);
		Janela janela = (Janela)janelaComponent;
		Jogo jogo = janela.getJogo();

		
		if(jogo.atirar(buttonComponent.getText())) {
			buttonComponent.setAcerto();
		}else {
			buttonComponent.setErro();
		}
		
		janela.update();
		buttonComponent.setClicado(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
