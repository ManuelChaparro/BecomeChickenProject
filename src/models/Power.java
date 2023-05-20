package models;

import java.awt.Rectangle;

public class Power {
	
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;

	public Power(String path) {
		x = resizeX(20);
		y = resizeY(530);
		width = resizeX(80);
		height = resizeY(80);
		this.path = path;
	}

	public int resizeX(int value){
		return new Resizor().updDateX(value);
	}

	public int resizeY(int value){
		return new Resizor().updDateX(value);
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
		return new Rectangle(x + 18, y + 25, width - 35, height - 25);
	}
	
	
	

}
