package models;

import java.awt.Rectangle;
import views.Constants;

public class Wolf {

	private String path;
	private int SPEED = 0;
	private int x;
	private int y;
	private int width;
	private int height;
	private int counterSteps;
	private int quality;
	private int counterJump;
	private int delayJump;
	private int timerJump;
	private boolean validationJump;

	public Wolf(String path) {
		validationJump = false;
		x = 1100;
		y = 530;
		width = 110;
		height = 80;
		timerJump = 0;
		this.path = path;
		createPowers();
	}
	
	public void setSpeed(int speed) {
		this.SPEED = speed;
	}

	private void createPowers() {
		switch (quality){
			case 1:
				SPEED = 4;
				break;
			case 2:
				SPEED = 8;
				break;
			case 3:
				SPEED = 20;
				break;
		}
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
		createPowers();
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void moveLeft() {
		if (x > -200) {
			x -= SPEED;
			counterSteps += 1;
			if (counterSteps == 20) {
				path = Constants.PATH_WOLF_L_1;
			}
			if (counterSteps == 40) {
				path = Constants.PATH_WOLF_L_2;
			}
			if (counterSteps == 60) {
				path = Constants.PATH_WOLF_L_3;
				counterSteps = 0;
			}
		}
	}
	
	public void moveRight() {
		if (x < 1500) {
			x += SPEED;
			counterSteps += 1;
			if (counterSteps == 20) {
				path = Constants.PATH_WOLF_R_1;
			}
			if (counterSteps == 40) {
				path = Constants.PATH_WOLF_R_2;
			}
			if (counterSteps == 60) {
				path = Constants.PATH_WOLF_R_3;
				counterSteps = 0;
			}
		}
	}
	
	public void moveUp() {
		timerJump++;
		if (delayJump == timerJump || validationJump) {
			validationJump = true;
			counterJump += 7;
			if (Constants.TOP_JUMP > counterJump) {
				y -= 8;
			} else if (Constants.TOP_JUMP < counterJump) {
				y += 8;
				if (y == 530) {				
					counterJump = 0;
					timerJump = 0;
					validationJump = false;
				}
			}
		}
	}

	public int getSPEED() {
		return SPEED;
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
		return new Rectangle(x, y + 20, width, height);
	}

	public void setDelayJump(int i) {
		delayJump = i;	
	}
}
