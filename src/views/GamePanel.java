package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.*;
import models.Game;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Game game;
	private Image iconTutorial;
	private int stage, width, height, limitWidth, wallpaperX;
	
	public GamePanel() {
		setPreferredSize(new Dimension(1000, 550));
		setBackground(Color.BLACK);
		initVariables();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		height = (int) (screenSize.height * 0.9);
		width = (height*11482)/824;
		limitWidth = -(width-screenSize.width);
	}

	private void initVariables() {
		game = new Game(false);
	}

	public void setWallpaperX(int x){
		wallpaperX -= x;
	}

	public boolean moveWallpaper(int x){
		if(wallpaperX >= limitWidth){
			this.wallpaperX -= x;
			return true;
		}else{
			return false;
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);

		Toolkit.getDefaultToolkit().sync();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Image wallpaper = wallpaper = new ImageIcon(getClass()
				.getResource(Constants.WALLPAPER_WORLD)).getImage();;
		g2.drawImage(wallpaper, wallpaperX, 0, width, this.height, null);
		
		Image imgChicken = new ImageIcon(getClass().getResource(game.getChicken().getPath())).getImage();
		g2.drawImage(imgChicken, game.getChicken().getX(), game.getChicken().getY(),
				game.getChicken().getWidth(), game.getChicken().getHeight(), null);
		
		if (game.isStageWizard() && game.getWizard().isShow()) {
			g2.drawImage(new ImageIcon(getClass().getResource(game.getWizard().getPath())).getImage(),
					game.getWizard().getX(), game.getWizard().getY(),
					game.getWizard().getWidth(), game.getWizard().getHeight(), null);
		}
		
		if (game.isStageWolfs()) {
			for (int id = 0; id < game.getQuantityWolfs(); id++) {
				Image imgWolf = new ImageIcon(getClass().getResource(game.getWolfs().get(id).getPath())).getImage();
				g2.drawImage(imgWolf, game.getWolfs().get(id).getX(), game.getWolfs().get(id).getY(),
						game.getWolfs().get(id).getWidth(), game.getWolfs().get(id).getHeight(), null);

			}
		}
		
		if (game.getVisibleGhost() && game.isMoveGhost() && game.isStageGhosts()) {
			for (int id = 0; id <= game.getCounterGhosts(); id++) {
				Image imgGhost = new ImageIcon(getClass().getResource(game.getGhosts().get(id).getPath())).getImage();
				g2.drawImage(imgGhost, game.getGhosts().get(id).getX(), game.getGhosts().get(id).getY(),
						game.getGhosts().get(id).getWidth(), game.getGhosts().get(id).getHeight(), null);
			}
		}
		
		if (game.isMusic()) {
			Image imgMusic = new ImageIcon(getClass().getResource(Constants.PATH_ICON_MUSIC)).getImage();
			g2.drawImage(imgMusic, 1300, 20, 50, 50, null);
		}
		
		if (game.isAutosave()) {
			Image imgMusic = new ImageIcon(getClass().getResource(Constants.PATH_ICON_AUTOSAVE)).getImage();
			g2.drawImage(imgMusic, 1240, 20, 50, 50, null);
		}
		
		if (game.getScreenShot()) {
			Image imgMusic = new ImageIcon(getClass().getResource(Constants.PATH_ICON_SCREENSHOT)).getImage();
			g2.drawImage(imgMusic, 1140, 20, 70, 50, null);
		}
		
		if (game.isShowIceCreams()) {
			Image img = new ImageIcon(getClass().getResource(Constants.PATH_ICECREAM)).getImage();
			g2.drawImage(img, game.getIceCream().getX(), game.getIceCream().getY(),
					game.getIceCream().getWidth(), game.getIceCream().getHeight(), null);
		}
		
		if (game.isTutorial()) {
			if (game.getStage() == 1 && game.isStageWolfs()) {
				tutoWolfs(g2);
			}else if(game.getStage() == 1 && game.isStageGhosts()) {
				tutoGhosts(g2);
			}else if(game.getStage() == 2 && game.isStageWolfs()) {
				tutoFast(g2);
			}else if(game.getStage() == 2 && game.isStageGhosts()) {
				tutoInmunity(g2);
			}
		}
		repaint();
	}

	private void tutoInmunity(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 45, 1100, 130);
		g2.fillOval(1040, 45, 130, 130);
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_INMUNITY, 10, 80);
		g2.drawString(Constants.TUTORIAL_INMUNITY_INFO_2, 10, 120);
		g2.drawString(Constants.TUTORIAL_INMUNITY_INFO, 10, 160);
		
		iconTutorial = new ImageIcon(getClass().getResource(Constants.PATH_INMUNITY)).getImage();
		g2.drawImage(iconTutorial, 1070, 60, 100, 100, null);
	}

	private void tutoFast(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 45, 1100, 130);
		g2.fillOval(1040, 45, 130, 130);
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_FAST, 10, 80);
		g2.drawString(Constants.TUTORIAL_FAST_INFO_2, 10, 120);
		g2.drawString(Constants.TUTORIAL_FAST_INFO, 10, 160);
		
		iconTutorial = new ImageIcon(getClass().getResource(Constants.PATH_FAST)).getImage();
		g2.drawImage(iconTutorial, 1070, 60, 60, 100, null);
	}

	private void tutoGhosts(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 45, 1100, 130);
		g2.fillOval(1040, 45, 130, 130);
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_TITLE, 10, 80);
		g2.drawString(Constants.TUTORIAL_MOVE, 10, 120);
		g2.drawString(Constants.TUTORIAL_MOVE_SECOND, 10, 160);
	}

	private void tutoWolfs(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 45, 1100, 130);
		g2.fillOval(1040, 45, 130, 130);
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_TITLE, 10, 80);
		g2.drawString(Constants.TUTORIAL_JUMP, 10, 120);
		g2.drawString(Constants.TUTORIAL_JUMP_MOVE, 10, 160);
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.drawString(Constants.SKIP_TUTORIAL, 10, 30);
		
		iconTutorial = new ImageIcon(getClass().getResource(Constants.PATH_ICECREAM)).getImage();
		g2.drawImage(iconTutorial, 1070, 60, 100, 100, null);
	}
	
	public void setGame(Game game) {
		this.game = game;
	}


	public void setLevel(int level) {
		this.stage = level;
	}
}







