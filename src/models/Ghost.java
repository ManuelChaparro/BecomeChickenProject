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
		x = resizeX(randomPosition);
		y = resizeY(200);
		width = resizeX(100);
		height = resizeY(100);
		this.path = path;
		moveDown = true;
	}

	public int resizeX(int value){
		return new Resizor().updDateX(value);
	}

	public int resizeY(int value){
		return new Resizor().updDateX(value);
	}
	
	public void setMoveDown() {
		moveDown = true;
	}
	
	public boolean getMoveDown() {
		return moveDown;
	}
	
	public void moveDown() {
		counterDown += 16;
		if (resizeY(530) > counterDown) {
			y += 10;
		} else if (resizeY(530) < counterDown) {
			y -= 10;
			if (y == 200) {				
				counterDown = 0;
				moveDown = false;
				
			}
		}
	}
	
	public void moveRight() {
		if (x < resizeX(870)) {
			x += 6;
			counterSteps += 1;
			if (counterSteps == 5) {
				path = Constants.PATH_GHOST_R_1;
			}
			if (counterSteps == 10) {
				path = Constants.PATH_GHOST_R_2;
			}
			if (counterSteps == 15) {
				path = Constants.PATH_GHOST_R_3;
				counterSteps = 0;
			}
		}
	}
	
	public void moveLeft() {
		if (x > -(resizeX(200))) {
			x -= 1;
			counterSteps += 1;
			if (counterSteps == 20) {
				path = Constants.PATH_GHOST_L_1;
			}
			if (counterSteps == 40) {
				path = Constants.PATH_GHOST_L_2;
			}
			if (counterSteps == 60) {
				path = Constants.PATH_GHOST_L_3;
				counterSteps = 0;
			}
		}
	}

	public void setX(int x) {
		this.x = resizeX(x);
	}

	public void setY(int y) {
		this.y = resizeY(y);
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
		this.width = resizeX(width);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = resizeY(height);
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
