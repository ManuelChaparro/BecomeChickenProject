package models;

import java.awt.*;

public class ScreenService {

    Dimension dimension;

    public ScreenService(){
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public int getWidth(double percetange){
        return (int) (dimension.getWidth() * percetange);
    }

    public int getHeight(double percetange){
        return (int) (dimension.getHeight() * percetange);
    }
}
