package views;

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

	public OptionPanel() {
		setBackground(Color.BLACK);
		x = 1400;
		y = 520;
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
		g2.drawImage(img, 100, 0, 1200, 700, null);
		
		if (music) {
			Image imgMusic = new ImageIcon(getClass().getResource(Constants.PATH_ICON_MUSIC)).getImage();
			g2.drawImage(imgMusic, 1300, 20, 50, 50, null);
		}
		
		if (load) {
			g2.drawString(LAST_GAME, 400, 680);
		}
		
		Image chicken = new ImageIcon(getClass().getResource(PATH_ACTUAL_CKN)).getImage();
		g2.drawImage(chicken, x, y, 80, 80, null);
	
		g2.drawString(MUSIC, 30, 40);
		g2.drawString(Constants.TUTORIAL_PAUSE, 30, 80);
		g2.drawString(AUTOSAVE, 30, 120);
		g2.drawString(AUTOR , 30, 750);
		
		g2.setFont(Constants.FONT_MIN);
		
		g2.setColor(Color.decode(color_start));
		g2.drawRect(450, 600, 180, 50);
		g2.drawString(START_GAME, 480, 630);
		
		g2.setColor(Color.decode(color_load));
		g2.drawRect(700, 600, 180, 50);
		g2.drawString(LOAD_GAME, 715, 630);
		
		g2.setColor(Color.decode(color_exit));
		g2.drawRect(950, 600, 150, 50);
		g2.drawString(EXIT, 1000, 630);
		
		repaint();
	}

	public void optionInitGame() {
		load = false;
		color_start = "#FFC400";
		color_load = "#ffffff";
		color_exit = "#ffffff";
		if (x > 500) {
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
		} else if (x == 500) {
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
		if (x < 750) {
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
		} else if (x == 750) {
			PATH_ACTUAL_CKN = Constants.PATH_CKN_FRONT;
			counterSteps = 0;
		} else  if (x > 320) {
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
		if (x < 750) {
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
		} else if (x == 750) {
			PATH_ACTUAL_CKN = Constants.PATH_CKN_FRONT;
			counterSteps = 0;
		} else  if (x > 320) {
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
