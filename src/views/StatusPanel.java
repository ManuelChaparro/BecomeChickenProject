package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import models.Game;

public class StatusPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private String LV = "1";
	private static final String TITLE_LEVEL = "NIVEL: ";
	private int power;
	private Game game;
	private static final String TITLE_POINTS = "PUNTOS: ";
	
	
	public StatusPanel() {
		game = new Game(false);
		setPreferredSize(new Dimension(1200, 125));
		setBorder(new EmptyBorder(100, 100, 100, 100));
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		g2.setColor(Color.WHITE);
		g2.drawRect(20, 20, 1320, 80);
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_DEFAULT);
		
		createStage(g2);
		createPoints(g2);
		createPowers(g2);
		createInmunity(g2);
		repaint();
	}

	private void createInmunity(Graphics2D g2) {
		int counter =650;
		Image img = new ImageIcon(getClass().getResource(Constants.PATH_INMUNITY)).getImage(); 
		for (int i = 0; i < game.getQuantityInmunity(); i++) {
			g2.drawImage(img, counter, 35, 50, 50, null);
			counter+=70;
		}
		
	}

	private void createPowers(Graphics2D g2) {
		int counter = 1000;
		Image img = new ImageIcon(getClass().getResource(Constants.PATH_FAST)).getImage(); 
		if (game.getChicken().getPower() == 0) {
			power = 5;
		}else {
			power = game.getChicken().getPower();
		}
 		for (int i = 0; i < power; i++) {
			g2.drawImage(img, counter, 35, 30, 50, null);
			counter+=60;
		}
	}

	private void createPoints(Graphics2D g2) {
		g2.drawString(TITLE_POINTS , 350, 70);
		g2.drawString(String.valueOf(game.getPoints()), 500, 70);
		g2.drawLine(600, 20, 600, 80);
	}

	private void createStage(Graphics2D g2) {
		g2.drawString(TITLE_LEVEL, 50, 70);
		g2.drawString(LV, 180, 70);
		g2.drawLine(300, 20, 300, 80);
	}
	
	public void setLevel(int level) {
		LV = ""+ String.valueOf(level)+"/10";	
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}