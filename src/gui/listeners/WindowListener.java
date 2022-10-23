package gui.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import batalhanaval.Jogo;

public class WindowListener extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		Jogo.isSpecialMode = false;	
	}
}
