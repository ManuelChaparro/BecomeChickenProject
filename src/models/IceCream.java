package models;

import java.awt.*;

public class IceCream {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	public IceCream(int x, int y, int width, int height) {
		this.x = resizeX(x);
		this.y = resizeY(y);
		this.width = resizeX(width);
		this.height = resizeY(height);
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = resizeX(x);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = resizeY(y);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}
