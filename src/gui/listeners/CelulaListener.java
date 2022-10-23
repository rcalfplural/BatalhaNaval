package gui.listeners;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import gui.CelulaComponent;

public class CelulaListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getButton() != MouseEvent.BUTTON1) return;
		
		
		Object button = e.getComponent();
		
		if(!(button instanceof CelulaComponent)) return;
		
		CelulaComponent buttonComponent = (CelulaComponent)button;
		
		if(buttonComponent.getNavioId() != 0) {
			buttonComponent.setText("X");
			buttonComponent.setBackground(Color.GREEN);
		}else {
			buttonComponent.setText("0");
			buttonComponent.setBackground(Color.RED);
		}
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
