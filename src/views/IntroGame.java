package views;

import models.Resizor;

import java.awt.*;

import javax.swing.*;

public class IntroGame extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int henX, henY, magoX, magoY, wolfX, wolfY, chickenX, chickenY;
	private String infoStory, infoStoryTwo, pathHen, pathMago, pathWolf, pathChicken;
	private Graphics2D g2;

	private Resizor resizor = new Resizor();

	public IntroGame() {
		initVariables();
		setBackground(Color.BLACK);
	}

	private void initVariables() {
		henX = resizor.updDateX(450);
		henY = resizor.updDateY(525);
		magoX = resizor.updDateX(650);
		magoY = resizor.updDateY(525);
		wolfX = resizor.updDateX(800);
		wolfY = resizor.updDateY(525);
		chickenX = resizor.updDateX(450);
		chickenY = resizor.updDateY(525);
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
		g2.drawImage(img, resizor.updDateX(200), resizor.updDateY(25), resizor.updDateX(1000), resizor.updDateY(650), null);

		Image hen = new ImageIcon(getClass().getResource(pathHen)).getImage();
		g2.drawImage(hen, henX, henY, resizor.updDateX(90), resizor.updDateY(90), null);

		Image mago = new ImageIcon(getClass().getResource(pathMago)).getImage();
		g2.drawImage(mago, magoX, magoY, resizor.updDateX(110), resizor.updDateY(140), null);

		Image wolf = new ImageIcon(getClass().getResource(pathWolf)).getImage();
		g2.drawImage(wolf, wolfX, wolfY, resizor.updDateX(130), resizor.updDateY(90), null);

		Image chicken = new ImageIcon(getClass().getResource(pathChicken)).getImage();
		g2.drawImage(chicken, chickenX, chickenY, resizor.updDateX(90), resizor.updDateY(90), null);

		g2.setColor(Color.WHITE);
		g2.setFont(Constants.FONT_MIN);
		g2.drawString(infoStory, resizor.updDateX(200), resizor.updDateY(700));
		g2.drawString(infoStoryTwo, resizor.updDateX(200), resizor.updDateY(720));
		g2.drawString("Pulsa 'W' 'A' 'D' para omitir...", resizor.updDateX(200), resizor.updDateY(20));
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

