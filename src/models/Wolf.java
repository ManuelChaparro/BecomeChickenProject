package models;

import java.awt.*;

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

	public Wolf(String path, int quality) {
		validationJump = false;
		x = resizeX(1100);
		y = resizeY(530);
		width = resizeX(110);
		height = resizeY(80);
		timerJump = 0;
		this.path = path;
		this.quality = quality;
		createPowers();
	}

	public Dimension getScreenSize(){
		return new Resizor().getTrueScreeenSize();
	}

	public int resizeX(int value){
		return new Resizor().updDateX(value);
	}

	public int resizeY(int value){
		return new Resizor().updDateX(value);
	}
	
	public void setSpeed(int speed) {
		this.SPEED = resizeX(speed);
	}

	private void createPowers() {
		if (quality == 4) {
			SPEED = 3;
		}else if(quality == 8){
			SPEED = 5;
		}else if(quality == 12){
			SPEED = 5;
		}
		else if(quality == 16){
			SPEED = 7;
		}
		else if(quality == 20){
			SPEED = 8;
		}
		else if(quality == 24){
			SPEED = 7;
		}
		else if(quality == 28){
			SPEED = 9;
		}
		else if(quality == 30){
			SPEED = 8;
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
		this.x = resizeX(x);
	}
	
	public void moveLeft() {
		if (x > -(width)) {
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
		if (x < resizeX(1500)) {
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
				if (y == resizeY(530)) {
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
