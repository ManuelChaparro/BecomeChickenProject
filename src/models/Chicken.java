package models;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Dimension;
import views.Constants;

public class Chicken {

	private static final int SPEED_NORMAL = 5;
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private int counterSteps;
	private int counterJump;
	private int speed;
	private int power;
	private boolean inmunity;
	private int HEIGHT_Y;

	public Chicken(String path) {
		Dimension screenSize = getScreenSize();
		HEIGHT_Y = (int) (screenSize.height * 0.69);
		x = resizeX(20);
		y = HEIGHT_Y;
		width = resizeX(80);
		height = resizeY(80);
		speed = SPEED_NORMAL;
		power = 0;
		this.path = path;
		inmunity = false;
	}

	public void setX(int x) {
		this.x = resizeX(x);
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

	public void moveRight() {
		if (x < resizeX(1285)) {
			speed += power;
			x += speed;
			speed = SPEED_NORMAL;
			counterSteps += 1;
			if (inmunity) {
				if (counterSteps == 5) {
					path = Constants.PATH_CKNP_R_1;
				}
				if (counterSteps == 10) {
					path = Constants.PATH_CKNP_R_2;
				}
				if (counterSteps == 15) {
					path = Constants.PATH_CKNP_R_3;
					counterSteps = 0;
				}
			} else {
				if (counterSteps == 5) {
					path = Constants.PATH_CKN_R_1;
				}
				if (counterSteps == 10) {
					path = Constants.PATH_CKN_R_2;
				}
				if (counterSteps == 15) {
					path = Constants.PATH_CKN_R_3;
					counterSteps = 0;
				}
			}
		}
	}

	public void moveLeft() {
		if (x > 0) {
			speed += power;
			x -= speed;
			speed = SPEED_NORMAL;
			counterSteps += 1;
			if (inmunity) {
				if (counterSteps == 5) {
					path = Constants.PATH_CKNP_L_1;
				}
				if (counterSteps == 10) {
					path = Constants.PATH_CKNP_L_2;
				}
				if (counterSteps == 15) {
					path = Constants.PATH_CKNP_L_3;
					counterSteps = 0;
				}
			} else {
				if (counterSteps == 5) {
					path = Constants.PATH_CKN_L_1;
				}
				if (counterSteps == 10) {
					path = Constants.PATH_CKN_L_2;
				}
				if (counterSteps == 15) {
					path = Constants.PATH_CKN_L_3;
					counterSteps = 0;
				}
			}
		}
	}

	public boolean moveUp() {
		counterJump += 8;
		if (Constants.TOP_JUMP > counterJump) {
			y -= 12;
		} else if (Constants.TOP_JUMP < counterJump) {
			y += 12;
			if (y == this.HEIGHT_Y) {
				counterJump = 0;
				return false;
			}
		}
		return true;
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
		return new Rectangle(x + 18, y + 25, width - 35, height - 25);
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public boolean isInmunity() {
		return inmunity;
	}

	public void setInmunity(boolean inmunity) {
		this.inmunity = inmunity;
	}
}