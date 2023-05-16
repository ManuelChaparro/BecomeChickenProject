package models;

import java.awt.Rectangle;

public class Power {
	
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;

	public Power(String path) {
		x = 20;
		y = 530;
		width = 80;
		height = 80;
		this.path = path;
	}

	public void setX(int x) {
		this.x = x;
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
		return new Rectangle(x + 18, y + 25, width - 35, height - 25);
	}
	
	
	

}
