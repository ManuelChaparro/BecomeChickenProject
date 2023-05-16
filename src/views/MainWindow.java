package views;

import java.awt.CardLayout;
import javax.swing.*;
import models.Game;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private static final String TITLE_WINDOW = "Become Chicken";
	private static final String PATH_ICON = "/img/iconChicken!.png";
	
	private JPanel cards;
	private MainPanel mainPanel;
	private IntroGame intro;
	private OptionPanel option;
	private GameOver gameOver;
	private EndGame endGame;

	public MainWindow() {
		intro = new IntroGame();
		option = new OptionPanel();
		mainPanel = new MainPanel();
		gameOver = new GameOver();
		endGame = new EndGame();
		cards = new JPanel(new CardLayout());
		cards.add(intro, "Intro");
		cards.add(option, "Option");
		cards.add(mainPanel, "MainPanel");
		cards.add(gameOver, "GameOver");
		cards.add(endGame, "EndGame");
		add(cards);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE_WINDOW);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setIconImage(new ImageIcon(getClass().getResource(PATH_ICON)).getImage());
		setVisible(true);
	}
	
	public void changePanel() {
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.next(cards);
	}


	public void initFirstAct() {
		intro.initFirstAct();	
	}

	public void initSecondAct() {
		intro.initSecondAct();
	}

	public void initThirdAct() {
		intro.initThirdAct();
	}

	public void initFourAct() {
		intro.initFourAct();	
	}

	public void initFiveAct() {
		intro.initFiveAct();
	}

	public void optionInitGame() {
		option.optionInitGame();
		gameOver.optionInitGame();
	}
	
	public void optionLoadGame(String string) {
		option.optionLoadGame(string);
	}

	public void optionExitGame() {
		option.optionExitGame();
	}

	public void setLevel(int level) {
		mainPanel.setLevel(level);
		
	}

	public void setIconMusic(boolean music) {
		option.setIconMusic(music);	
	}

	public void exit() {
		gameOver.exit();
		
	}

	public void changeCardLayout(String string) {
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, string);
	}

	public void setGame(Game game) {
		mainPanel.setGame(game);
	}
	
	public void endFourAct() {
		endGame.endFourAct();	
	}

	public void endThirdAct() {
		endGame.endThirdAct();	
	}

	public void endSecondAct() {
		endGame.endSecondAct();	
	}

	public void endFirstAct() {
		endGame.endFirstAct();		
	}

	public void loadGame() {
		gameOver.loadGame();
	}
}
