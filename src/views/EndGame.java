package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class EndGame extends JPanel{
	private static final long serialVersionUID = 1L;
	private int henX, henY, magoX, magoY, chickenX, chickenY;
	private String infoStory, infoStoryTwo, pathHen, pathMago, pathChicken;
	private Graphics2D g2;
	
	public EndGame() {
		initVariables();
		setBackground(Color.BLACK);
	}

	private void initVariables() {
		henX = 450;
		henY = 525;
		magoX = 650;
		magoY = 525;
		chickenX = 600;
		chickenY = 525;
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
		g2.drawImage(img, 200, 25, 1000, 650, null);
		
		Image hen = new ImageIcon(getClass().getResource(pathHen)).getImage();
		g2.drawImage(hen, henX, henY, 90, 90, null);
		
		Image mago = new ImageIcon(getClass().getResource(pathMago)).getImage();
		g2.drawImage(mago, magoX, magoY, 110, 140, null);
		
		Image chicken = new ImageIcon(getClass().getResource(pathChicken)).getImage();
		g2.drawImage(chicken, chickenX, chickenY, 90, 90, null);
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.drawString(infoStory, 200, 700);
		g2.drawString(infoStoryTwo, 200, 720);
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
