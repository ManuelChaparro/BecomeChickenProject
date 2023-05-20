package models;

import java.awt.*;

/**
 * Clase usada para redimensionar el juego y adaptarlo a pantalla
 */
public class Resizor {

    GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice device = environment.getDefaultScreenDevice();
    private Dimension thisScreen = new Dimension(device.getDisplayMode().getWidth(), device.getDisplayMode().getHeight());

    public int updDateX(int value){
        int xAnterior = (int) value;
        double escalaX = (double) thisScreen.width / 1366;
        int nuevoX = (int) (xAnterior * escalaX);

        return nuevoX;
    }

    public int updDateY(int value){
        int yAnterior = (int) value;
        double escalaY = (double) (thisScreen.height / 768);
        int nuevoY = (int) (yAnterior * (escalaY * 1.3));

        return nuevoY;
    }

    public Dimension getTrueScreeenSize(){
        return thisScreen;
    }
}
