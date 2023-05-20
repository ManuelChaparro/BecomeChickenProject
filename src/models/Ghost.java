package models;

import java.awt.Rectangle;

import views.Constants;

public class Ghost {
	
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private int counterSteps, counterStepsActive;
	private int counterDown;
	private boolean moveDown;

	public Ghost(String path, int randomPosition) {
		x = randomPosition;
		y = 200;
		width = 100;
		height = 100;
		this.path = path;
		moveDown = true;
	}


	public void setX(int x) {
		this.x = x;
	}
	
	public void setMoveDown() {
		moveDown = true;
	}
	
	public boolean getMoveDown() {
		return moveDown;
	}
	
	public void moveDown() {
		counterDown += 16;
		if (530 > counterDown) {
			y += 10;
		} else if (530 < counterDown) {
			y -= 10;
			if (y == 200) {				
				counterDown = 0;
				moveDown = false;
				
			}
		}
	}
	
	public void moveRight() {
		x += 1;
	}

	public void moveLeft(){
		x -= 1;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	public void setCounterSteps() {
		counterSteps = 0;
	}
	
	public void ghostMovement(int time) {
		moveSpriteFront();
		moveDown = true;
		if (time >= 10000 && time <= 20000 && moveDown == true) {
			moveDown();
		}
	}

	public void moveSpriteFront() {
		counterSteps += 1;
		if (counterSteps == 40) {
			path = Constants.PATH_GHOST_F_1;
		}
		if (counterSteps == 80) {
			path = Constants.PATH_GHOST_F_2;
		}
		if (counterSteps == 120) {
			path = Constants.PATH_GHOST_F_3;
			counterSteps = 0;
		}
	}
	
	public void moveSpriteActive() {
		counterStepsActive += 1;
		if (counterStepsActive == 40) {
			path = Constants.PATH_GHOST_A_1;
		}
		if (counterStepsActive == 80) {
			path = Constants.PATH_GHOST_A_2;
		}
		if (counterStepsActive == 120) {
			path = Constants.PATH_GHOST_A_3;
			counterStepsActive = 0;
		}
		
	}

}
