package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.*;

public class IntroGame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int henX, henY, magoX, magoY, wolfX, wolfY, chickenX, chickenY;
	private String infoStory, infoStoryTwo, pathHen, pathMago, pathWolf, pathChicken;
	private Graphics2D g2;
	
	public IntroGame() {
		initVariables();
		setBackground(Color.BLACK);
	}

	private void initVariables() {
		henX = 450;
		henY = 525;
		magoX = 650;
		magoY = 525;
		wolfX = 800;
		wolfY = 525;
		chickenX = 450;
		chickenY = 525;
		infoStory = "";
		infoStoryTwo = "";
		pathHen = Constants.PATH_HEN;
		pathMago = Constants.PATH_NOTHING;
		pathWolf = Constants.PATH_NOTHING;
		pathChicken = Constants.PATH_NOTHING;
	}
	
	public void paint(Graphics g) {
		g2= (Graphics2D) g;
		super.paint(g2);
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Image img = new ImageIcon(getClass().getResource(Constants.PATH_WALL_INIT)).getImage();
		g2.drawImage(img, 200, 25, 1000, 650, null);
		
		Image hen = new ImageIcon(getClass().getResource(pathHen)).getImage();
		g2.drawImage(hen, henX, henY, 90, 90, null);
		
		Image mago = new ImageIcon(getClass().getResource(pathMago)).getImage();
		g2.drawImage(mago, magoX, magoY, 110, 140, null);
		
		Image wolf = new ImageIcon(getClass().getResource(pathWolf)).getImage();
		g2.drawImage(wolf, wolfX, wolfY, 130, 90, null);
		
		Image chicken = new ImageIcon(getClass().getResource(pathChicken)).getImage();
		g2.drawImage(chicken, chickenX, chickenY, 90, 90, null);
		
		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.drawString(infoStory, 200, 700);
		g2.drawString(infoStoryTwo, 200, 720);
		g2.drawString("Pulsa 'W' 'A' 'D' para omitir...", 200, 20);
		repaint();
	}

	private void clearPanel() {
		pathChicken = Constants.PATH_NOTHING;
		pathMago = Constants.PATH_NOTHING;
		pathWolf = Constants.PATH_NOTHING;
		pathHen = Constants.PATH_NOTHING;
		infoStory = Constants.STORY_FOUR;
		infoStoryTwo = "";
		
	}
	
	public void initFiveAct() {
		pathChicken = Constants.PATH_CKN_R_1;
		infoStory = Constants.STORY_FIVE;
	}

	public void initFourAct() {
		pathMago = Constants.PATH_NOTHING;
		pathWolf = Constants.PATH_NOTHING;
		pathHen = Constants.PATH_NOTHING;
		infoStory = Constants.STORY_FOUR;
		infoStoryTwo = "";
	}

	public void initThirdAct() {
		pathMago = Constants.PATH_WIZARD_L_1;
		pathWolf = Constants.PATH_WOLF_L_1;
		infoStory = Constants.STORY_THREE;
		infoStoryTwo = Constants.STORY_THREE_2;
	}

	public void initSecondAct() {
		infoStory = Constants.STORY_TWO;
		infoStoryTwo = "";
	}

	public void initFirstAct() {
		infoStory = Constants.STORY_ONE;
		infoStoryTwo = Constants.STORY_ONE_2;
		
	}

	public void startRight() {
		clearPanel();
	}
	
	public void startLeft() {
		clearPanel();
	}
}
