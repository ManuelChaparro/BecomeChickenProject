package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.*;
import models.Game;
import models.Resizor;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Game game;
	private Image iconTutorial;
	private int stage, width, height;

	private Resizor resizor = new Resizor();
	
	public GamePanel() {
		setPreferredSize(new Dimension(resizor.updDateX(1000), resizor.updDateY(550)));
		setBackground(Color.BLACK);
		initVariables();
		width = resizor.getTrueScreeenSize().width;
		height = (int) (resizor.getTrueScreeenSize().height * 0.9);
	}

	private void initVariables() {
		game = new Game(false);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Image wallpaper = null;
		if (stage >= 1 && stage < 3){
			wallpaper = new ImageIcon(getClass()
					.getResource(Constants.PATH_WALLPAPER_ONE)).getImage();
		}else if (stage >= 3 && stage < 6){
			wallpaper = new ImageIcon(getClass()
					.getResource(Constants.PATH_WALLPAPER_TWO)).getImage();
		}else if (stage >= 6 && stage < 10){
			wallpaper = new ImageIcon(getClass()
					.getResource(Constants.PATH_WALLPAPER_THREE)).getImage();
		}else{
			wallpaper = new ImageIcon(getClass()
					.getResource(Constants.PATH_WALLPAPER_FINAL)).getImage();
		}
		g2.drawImage(wallpaper, 0, 0, this.width, this.height, null);
		
		Image imgChicken = new ImageIcon(getClass().getResource(game.getChicken().getPath())).getImage();
		g2.drawImage(imgChicken, game.getChicken().getX(), game.getChicken().getY(),
				game.getChicken().getWidth(), game.getChicken().getHeight(), null);
		
		if (game.isStageWizard() && game.getWizard().isShow()) {
			g2.drawImage(new ImageIcon(getClass().getResource(game.getWizard().getPath())).getImage(),
					game.getWizard().getX(), game.getWizard().getY(),
					game.getWizard().getWidth(), game.getWizard().getHeight(), null);
		}
		
		if (game.isStageWolfs()) {
			for (Integer id : game.getWolfs().keySet()) {
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
			g2.drawImage(imgMusic, resizor.updDateX(1300), resizor.updDateY(20), resizor.updDateX(50), resizor.updDateY(50), null);
		}
		
		if (game.isAutosave()) {
			Image imgMusic = new ImageIcon(getClass().getResource(Constants.PATH_ICON_AUTOSAVE)).getImage();
			g2.drawImage(imgMusic, resizor.updDateX(1240), resizor.updDateY(20), resizor.updDateX(50), resizor.updDateY(50), null);
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
		g2.fillRect(0, resizor.updDateY(45), resizor.updDateX(1100), resizor.updDateY(130));
		g2.fillOval(resizor.updDateX(1040), resizor.updDateY(45), resizor.updDateX(130), resizor.updDateY(130));
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_INMUNITY, resizor.updDateX(10), resizor.updDateY(80));
		g2.drawString(Constants.TUTORIAL_INMUNITY_INFO_2, resizor.updDateX(10), resizor.updDateY(120));
		g2.drawString(Constants.TUTORIAL_INMUNITY_INFO, resizor.updDateX(10), resizor.updDateY(160));
		
		iconTutorial = new ImageIcon(getClass().getResource(Constants.PATH_INMUNITY)).getImage();
		g2.drawImage(iconTutorial, resizor.updDateX(1070), resizor.updDateY(60), resizor.updDateX(100), resizor.updDateY(100), null);
	}

	private void tutoFast(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, resizor.updDateY(45), resizor.updDateX(1100), resizor.updDateY(130));
		g2.fillOval(resizor.updDateX(1040), resizor.updDateY(45), resizor.updDateX(130), resizor.updDateY(130));
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_FAST, resizor.updDateX(10), resizor.updDateY(80));
		g2.drawString(Constants.TUTORIAL_FAST_INFO_2, resizor.updDateX(10), resizor.updDateY(120));
		g2.drawString(Constants.TUTORIAL_FAST_INFO, resizor.updDateX(10), resizor.updDateY(160));
		
		iconTutorial = new ImageIcon(getClass().getResource(Constants.PATH_FAST)).getImage();
		g2.drawImage(iconTutorial, resizor.updDateX(1070), resizor.updDateY(60), resizor.updDateX(60), resizor.updDateY(100), null);
	}

	private void tutoGhosts(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, resizor.updDateY(45), resizor.updDateX(1100), resizor.updDateY(130));
		g2.fillOval(resizor.updDateX(1040), resizor.updDateY(45), resizor.updDateX(130), resizor.updDateY(130));
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_TITLE, resizor.updDateX(10), resizor.updDateY(80));
		g2.drawString(Constants.TUTORIAL_MOVE, resizor.updDateX(10), resizor.updDateY(120));
		g2.drawString(Constants.TUTORIAL_MOVE_SECOND, resizor.updDateX(10), resizor.updDateY(160));
	}

	private void tutoWolfs(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, resizor.updDateY(45), resizor.updDateX(1100), resizor.updDateY(130));
		g2.fillOval(resizor.updDateX(1040), resizor.updDateY(45), resizor.updDateX(130), resizor.updDateY(130));
		
		g2.setFont(Constants.FONT_DEFAULT);
		g2.setColor(Color.BLACK);
		g2.drawString(Constants.TUTORIAL_TITLE, resizor.updDateX(10), resizor.updDateY(80));
		g2.drawString(Constants.TUTORIAL_JUMP, resizor.updDateX(10), resizor.updDateY(120));
		g2.drawString(Constants.TUTORIAL_JUMP_MOVE, resizor.updDateX(10), resizor.updDateY(160));
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.drawString(Constants.SKIP_TUTORIAL, resizor.updDateX(10), resizor.updDateY(30));
		
		iconTutorial = new ImageIcon(getClass().getResource(Constants.PATH_ICECREAM)).getImage();
		g2.drawImage(iconTutorial, resizor.updDateX(1070), resizor.updDateY(60), resizor.updDateX(100), resizor.updDateY(100), null);
	}
	
	public void setGame(Game game) {
		this.game = game;
	}


	public void setLevel(int level) {
		this.stage = level;
	}
}







