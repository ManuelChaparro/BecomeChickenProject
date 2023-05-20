package views;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class TestPantalla {
    public static void main(String[] args) {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = environment.getDefaultScreenDevice();
        Dimension dimension = new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());

        int anchoPantalla = (int) dimension.getWidth();
        int altoPantalla = (int) dimension.getHeight();

        System.out.println("Resolución de pantalla: " + anchoPantalla + "x" + altoPantalla);
    }
}
