package views;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenShot {
	
	public static void shot(String Nombre, int x, int y, int width, int height) throws AWTException, IOException {
		Rectangle position = new Rectangle(x, y, width, height);
		BufferedImage captura = new Robot().createScreenCapture(position);
		File file = new File("screenshots/" + Nombre + ".jpg");
		ImageIO.write(captura, "jpg", file);
	}
}