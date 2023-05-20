package views;

import models.Resizor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.*;

public class OptionPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String START_GAME = "Nuevo Juego";
	private static final String LOAD_GAME = "Cargar Partida";
	private static final String EXIT = "Salir";
	private static final String MUSIC = "Presiona 'M' para pausar o reproducir la musica";
	private int x, y, counterSteps;
	private String color_start = "#FFC400";
	private String color_load = "#fffff";
	private String color_exit = "#fffff";
	private String PATH_ACTUAL_CKN = Constants.PATH_CKN_L_1;
	private boolean music;
	private boolean load;
	private String LAST_GAME;
	private String AUTOSAVE = "* Este juego cuenta con autoguardado";
	private String AUTOR = "Desarrollado por: Santiago Rojas  :)";

	private Resizor resizor = new Resizor();

	public OptionPanel() {
		setBackground(Color.BLACK);
		//El menos 1, es un desface que se tiene al momento de hacer el cálculo para realizar la nueva posición
		x = resizor.updDateX(1400) - 1;
		y = resizor.updDateY(520);
		music = true;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Image img = new ImageIcon(getClass().getResource(Constants.PATH_WALL_BCOME_CKN)).getImage();
		g2.drawImage(img, resizor.updDateX(100), resizor.updDateY(0),resizor.updDateX(1200), resizor.updDateY(700), null);
		
		if (music) {
			Image imgMusic = new ImageIcon(getClass().getResource(Constants.PATH_ICON_MUSIC)).getImage();
			g2.drawImage(imgMusic, resizor.updDateX(1300), resizor.updDateY(20), resizor.updDateX(50), resizor.updDateY(50), null);
		}
		
		if (load) {
			g2.drawString(LAST_GAME, resizor.updDateX(400), resizor.updDateY(680));
		}
		
		Image chicken = new ImageIcon(getClass().getResource(PATH_ACTUAL_CKN)).getImage();
		g2.drawImage(chicken, x, y, resizor.updDateX(80), resizor.updDateY(80), null);
	
		g2.drawString(MUSIC, resizor.updDateX(30), resizor.updDateY(40));
		g2.drawString(Constants.TUTORIAL_PAUSE, resizor.updDateX(30), resizor.updDateY(80));
		g2.drawString(AUTOSAVE, resizor.updDateX(30), resizor.updDateY(120));
		g2.drawString(AUTOR , resizor.updDateX(30), resizor.updDateY(750));
		
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
		
		repaint();
	}

	public void optionInitGame() {
		load = false;
		color_start = "#FFC400";
		color_load = "#ffffff";
		color_exit = "#ffffff";
		if (x > resizor.updDateX(500)) {
			counterSteps += 1;
			x -= 2;
			if (counterSteps == 5) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_1;
			}
			if (counterSteps == 10) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_2;
			}
			if (counterSteps == 15) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_3;
				counterSteps = 0;
			}
		} else if (x == resizor.updDateX(500)) {
			PATH_ACTUAL_CKN = Constants.PATH_CKN_FRONT;
			counterSteps = 0;
		}

		repaint();
	}

	public void optionLoadGame(String string) {
		load = true;
		color_start = "#ffffff";
		color_load = "#FFC400";
		color_exit = "#ffffff";
		LAST_GAME = string;
		if (x < resizor.updDateX(750)) {
			counterSteps += 1;
			x += 2;
			if (counterSteps == 5) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_R_1;
			}
			if (counterSteps == 10) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_R_2;
			}
			if (counterSteps == 15) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_R_3;
				counterSteps = 0;
			}
		} else if (x == (int) resizor.updDateX(750)) {
			PATH_ACTUAL_CKN = Constants.PATH_CKN_FRONT;
			counterSteps = 0;
		} else  if (x > resizor.updDateX(320)) {
			counterSteps += 1;
			x -= 2;
			if (counterSteps == 5) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_1;
			}
			if (counterSteps == 10) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_2;
			}
			if (counterSteps == 15) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_3;
				counterSteps = 0;
			}
		}
		repaint();
	}

	public void optionExitGame() {
		load = false;
		color_start = "#ffffff";
		color_load = "#ffffff";
		color_exit = "#FFC400";
		if (x < (int) resizor.updDateX(750)) {
			counterSteps += 1;
			x += 2;
			if (counterSteps == 5) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_R_1;
			}
			if (counterSteps == 10) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_R_2;
			}
			if (counterSteps == 15) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_R_3;
				counterSteps = 0;
			}
		} else if (x == (int) resizor.updDateX(750)) {
			PATH_ACTUAL_CKN = Constants.PATH_CKN_FRONT;
			counterSteps = 0;
		} else  if (x > resizor.updDateX(320)) {
			counterSteps += 1;
			x -= 2;
			if (counterSteps == 5) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_1;
			}
			if (counterSteps == 10) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_2;
			}
			if (counterSteps == 15) {
				PATH_ACTUAL_CKN = Constants.PATH_CKN_L_3;
				counterSteps = 0;
			}
		}
		repaint();
	}

	public void setIconMusic(boolean music2) {
		music = music2;	
	}

}
