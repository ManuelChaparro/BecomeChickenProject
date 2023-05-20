package models;

import java.awt.*;

import views.Constants;

public class Wizard {

	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private int counterSteps;
	private int counterFront;
	private int counterJump;
	private boolean horizontalMove;
	private boolean show;

	public Wizard(String path) {
		x = resizeX(1200);
		y = resizeY(470);
		width = resizeX(110);
		height = resizeY(130);
		this.path = path;
		horizontalMove = false;
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
			x += 10;
			counterSteps += 1;
			if (counterSteps == 5) {
				path = Constants.PATH_WIZARD_R_1;
			}
			if (counterSteps == 10) {
				path = Constants.PATH_WIZARD_R_2;
			}
			if (counterSteps == 15) {
				path = Constants.PATH_WIZARD_R_3;
				counterSteps = 0;
			}
		}
	}

	public void moveLeft() {
		if (x > 0) {
			x -= 10;
			counterSteps += 1;
			if (counterSteps == 5) {
				path = Constants.PATH_WIZARD_L_1;
			}
			if (counterSteps == 10) {
				path = Constants.PATH_WIZARD_L_2;
			}
			if (counterSteps == 15) {
				path = Constants.PATH_WIZARD_L_3;
				counterSteps = 0;
			}
		}
	}

	public boolean moveUp() {
		counterJump += 4;
		if (Constants.TOP_JUMP > counterJump) {
			y -= 5;
		} else if (Constants.TOP_JUMP < counterJump) {
			y += 5;
			if (y == resizeY(470)) {
				counterJump = 0;
				return false;
			}
		}
		return true;
	}
	
	public void moveSpriteFront() {
		counterFront += 1;
		if (counterFront == 30) {
			path = Constants.PATH_WIZARD_F_1;
		}
		if (counterFront == 60) {
			path = Constants.PATH_WIZARD_F_2;
		}
		if (counterFront == 90) {
			path = Constants.PATH_WIZARD_F_3;
			counterFront = 0;
		}
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

	public void setX(int x) {
		this.x = resizeX(x);
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

	public boolean isHorizontalMove() {
		return horizontalMove;
	}

	public void setHorizontalMove(boolean horizontalMove) {
		this.horizontalMove = horizontalMove;
	}
	
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Rectangle getRectangle() {
		return new Rectangle(x + 18, y + 25, width - 35, height - 25);
	}
}
