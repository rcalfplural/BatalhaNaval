package gui;

import java.awt.Color;

import javax.swing.JButton;

import gui.listeners.CelulaListener;

public class CelulaComponent extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int navioId;
	private boolean clicado;
	private String coordString;
	public CelulaComponent(String text, int navioId) {
		super(text);
		this.navioId = navioId;
		this.clicado = false;
		this.coordString = text;
		setSize(60, 60);
		addMouseListener(new CelulaListener());
	}

	public int getNavioId() {
		return navioId;
	}

	public void setNavioId(int navioId) {
		this.navioId = navioId;
	}
	
	public boolean foiClicado() {
		return clicado;
	}
	
	public void setClicado(boolean c) {
		this.clicado = c;
	}
	
	public void setNaufragio() {
		this.setText("-");
		this.setBackground(Color.GREEN);
	}
	
	public void setAcerto() {
		this.setText("X");
		this.setBackground(Color.YELLOW);
	}
	
	public void setErro() {
		this.setText("0");
		this.setBackground(Color.RED);
	}

	public String getCoordString() {
		return coordString;
	}

	public void setCoordString(String coordString) {
		this.coordString = coordString;
	}
	
	
	
}
