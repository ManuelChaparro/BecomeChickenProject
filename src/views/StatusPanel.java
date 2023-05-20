package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import models.Game;
import models.Resizor;

public class StatusPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private String LV = "1";
	private static final String TITLE_LEVEL = "NIVEL: ";
	private int power;
	private Game game;
	private static final String TITLE_POINTS = "PUNTOS: ";

	private Resizor resizor = new Resizor();
	
	
	public StatusPanel() {
		game = new Game(false);
		setPreferredSize(new Dimension(resizor.updDateX(1200), resizor.updDateY(125)));
		setBorder(new EmptyBorder(100, 100, 100, 100));
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		g2.setColor(Color.WHITE);
		g2.drawRect(resizor.updDateX(20), resizor.updDateY(20), resizor.updDateX(1320), resizor.updDateY(80));
		
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
			g2.drawImage(img, resizor.updDateX(counter), resizor.updDateY(35), resizor.updDateX(50), resizor.updDateY(50), null);
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
			g2.drawImage(img, resizor.updDateX(counter), resizor.updDateY(35), resizor.updDateX(30), resizor.updDateY(50), null);
			counter+=60;
		}
	}

	private void createPoints(Graphics2D g2) {
		g2.drawString(TITLE_POINTS , resizor.updDateX(350), resizor.updDateY(70));
		g2.drawString(String.valueOf(game.getPoints()), resizor.updDateX(500), resizor.updDateY(70));
		g2.drawLine(resizor.updDateX(600), resizor.updDateY(20), resizor.updDateX(600), resizor.updDateY(80));
	}

	private void createStage(Graphics2D g2) {
		g2.drawString(TITLE_LEVEL, resizor.updDateX(50), resizor.updDateY(70));
		g2.drawString(LV, resizor.updDateX(180), resizor.updDateY(70));
		g2.drawLine(resizor.updDateX(300), resizor.updDateY(20), resizor.updDateX(300), resizor.updDateY(80));
	}
	
	public void setLevel(int level) {
		LV = ""+ String.valueOf(level)+"/10";	
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}