package views;

import models.Resizor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameOver extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String START_GAME = "Nuevo Juego";
	private static final String EXIT = "Salir";
	private static final String LOAD_GAME = "Cargar Partida";
	private String color_exit = "#fffff";
	private String color_start = "#FFC400";
	private String PATH_ACTUAL_CKN = Constants.PATH_CKN_L_1;
	private String color_load = "#fffff";
	Resizor resizor = new Resizor();

	public GameOver() {
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Image img = new ImageIcon(getClass().getResource(Constants.PATH_WALL_GME_OVER)).getImage();
		g2.drawImage(img, resizor.updDateX(150), resizor.updDateY(30), resizor.updDateX(1150), resizor.updDateY(550), null);
		
		Image imgChicken = new ImageIcon(getClass().getResource(Constants.PATH_CKN_OVER)).getImage();
		g2.drawImage(imgChicken, resizor.updDateX(580), resizor.updDateY(400), resizor.updDateX(200), resizor.updDateY(150), null);
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		
		g2.setColor(Color.decode(color_start));
		g2.drawRect(resizor.updDateX(450), resizor.updDateY(600), resizor.updDateX(180), resizor.updDateY(50));
		g2.drawString(START_GAME, resizor.updDateX(480), resizor.updDateY(630));
		
		g2.setColor(Color.decode(color_load));
		g2.drawRect(resizor.updDateX(700), resizor.updDateY(600), resizor.updDateX(180), resizor.updDateY(50));
		g2.drawString(LOAD_GAME, resizor.updDateX(715), resizor.updDateY(630));
		
		g2.setColor(Color.decode(color_exit));
		g2.drawRect(resizor.updDateX(950), resizor.updDateY(600), resizor.updDateX(150), resizor.updDateY(50));
		g2.drawString(EXIT, resizor.updDateX(1000), resizor.updDateY(630));
	}

	public void optionInitGame() {
		color_start = "#FFC400";
		color_exit = "#ffffff";
		color_load = "#ffffff";
		repaint();	
	}
	
	public void exit() {
		color_start = "#ffffff";
		color_exit = "#FFC400";
		color_load = "#ffffff";
		repaint();		
	}
	
	public void loadGame() {
		color_start = "#ffffff";
		color_exit = "#ffffff";
		color_load = "#FFC400";
		repaint();	
	}

	public String getPATH_ACTUAL_CKN() {
		return PATH_ACTUAL_CKN;
	}

	public void setPATH_ACTUAL_CKN(String pATH_ACTUAL_CKN) {
		PATH_ACTUAL_CKN = pATH_ACTUAL_CKN;
	}

}
