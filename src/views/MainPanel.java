package views;

import java.awt.*;
import javax.swing.*;

import models.Game;

public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private StatusPanel statusPanel;
	private GamePanel gamePanel;
	
	public MainPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		initComponents();
	}

	private void initComponents() {
		statusPanel = new StatusPanel();
		gamePanel = new GamePanel();
		
		add(statusPanel, BorderLayout.SOUTH);
		add(gamePanel, BorderLayout.CENTER);
	}

	public void setLevel(int level) {
		statusPanel.setLevel(level);
		gamePanel.setLevel(level);
	}

	public void setGame(Game game) {
		gamePanel.setGame(game);
		statusPanel.setGame(game);
	}

	public void setWallpaperX(int x){
		gamePanel.setWallpaperX(x);
	}

	public boolean moveWallpaper(int x){
		return gamePanel.moveWallpaper(x);
	}
}