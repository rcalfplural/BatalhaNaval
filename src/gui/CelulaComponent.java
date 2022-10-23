package gui;

import javax.swing.JButton;

import gui.listeners.CelulaListener;

public class CelulaComponent extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int navioId;
	
	public CelulaComponent(String text, int navioId) {
		super(text);
		this.navioId = navioId;
		
		setSize(60, 60);
		addMouseListener(new CelulaListener());
	}

	public int getNavioId() {
		return navioId;
	}

	public void setNavioId(int navioId) {
		this.navioId = navioId;
	}
	
	
}
