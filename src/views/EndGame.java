package views;

import models.Resizor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class EndGame extends JPanel {
	private static final long serialVersionUID = 1L;
	private int henX, henY, magoX, magoY, chickenX, chickenY;
	private String infoStory, infoStoryTwo, pathHen, pathMago, pathChicken;
	private Graphics2D g2;
	Resizor resizor = new Resizor();
	
	public EndGame() {
		initVariables();
		setBackground(Color.BLACK);
	}

	private void initVariables() {
		henX = resizor.updDateX(450);
		henY = resizor.updDateY(525);
		magoX = resizor.updDateX(650);
		magoY = resizor.updDateY(525);
		chickenX = resizor.updDateX(600);
		chickenY = resizor.updDateY(525);
		infoStory = "";
		infoStoryTwo = "";
		pathHen = Constants.PATH_HEN;
		pathMago = Constants.PATH_NOTHING;
		pathChicken = Constants.PATH_NOTHING;
	}
	
	public void paint(Graphics g) {
		g2= (Graphics2D) g;
		super.paint(g2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Image img = new ImageIcon(getClass().getResource(Constants.PATH_WALLPAPER_FINAL)).getImage();
		g2.drawImage(img, resizor.updDateX(200), resizor.updDateY(25), resizor.updDateX(1000), resizor.updDateY(650), null);
		
		Image hen = new ImageIcon(getClass().getResource(pathHen)).getImage();
		g2.drawImage(hen, henX, henY, resizor.updDateX(90), resizor.updDateY(90), null);
		
		Image mago = new ImageIcon(getClass().getResource(pathMago)).getImage();
		g2.drawImage(mago, magoX, magoY, resizor.updDateX(110), resizor.updDateY(140), null);
		
		Image chicken = new ImageIcon(getClass().getResource(pathChicken)).getImage();
		g2.drawImage(chicken, chickenX, chickenY, resizor.updDateX(90), resizor.updDateY(90), null);
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.drawString(infoStory, resizor.updDateX(200), resizor.updDateY(700));
		g2.drawString(infoStoryTwo, resizor.updDateX(200), resizor.updDateY(720));
		repaint();
	}

	private void clearPanel() {
		pathChicken = Constants.PATH_NOTHING;
		pathMago = Constants.PATH_NOTHING;
		pathHen = Constants.PATH_NOTHING;
		infoStory = Constants.STORY_FOUR;
		infoStoryTwo = "";
		
	}

	public void startRight() {
		clearPanel();
	}
	
	public void startLeft() {
		clearPanel();
	}

	public void endFirstAct() {
		pathChicken = Constants.PATH_CKN_FRONT;
		pathMago = Constants.PATH_NOTHING;
		pathHen = Constants.PATH_NOTHING;
		infoStory = Constants.END_GAME_ONE;
	}

	public void endSecondAct() {
		pathChicken = Constants.PATH_NOTHING;
		pathMago = Constants.PATH_WIZARD_F_1;
		pathHen = Constants.PATH_NOTHING;
		infoStory = Constants.END_GAME_TWO;
	}

	public void endThirdAct() {
		pathChicken = Constants.PATH_NOTHING;
		pathMago = Constants.PATH_WIZARD_F_1;
		pathHen = Constants.PATH_HEN;
		infoStory = Constants.END_GAME_THREE;
	}

	public void endFourAct() {
		pathChicken = Constants.PATH_CKN_FRONT;
		pathMago = Constants.PATH_NOTHING;
		pathHen = Constants.PATH_HEN;
		infoStory = Constants.END_GAME_FOUR;
	}
}
