package test.simulationmodel;

import simulationmodels.RandomWalk;

import java.util.ArrayList;

public class TestRandomWalk {

    //Posiciones aleatorias en X y Y para el helado
    ArrayList<Integer> xData = new ArrayList<Integer>();
    ArrayList<Integer> yData = new ArrayList<Integer>();

    public TestRandomWalk(int xInit, int xFinal, int yInit, int yFinal){
        System.out.println("...Cargando datos de caminata aleatoria");
        this.xData = getDataX(xInit, xFinal);
        this.yData = getDataX(yInit, yFinal);
        System.out.println("...Terminada");
    }

    public ArrayList<Integer> getDataX(int xInit, int xFinal){
        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        RandomWalk myRandomWalk = new RandomWalk();
        int aux = 0;
        int actualStep = 0;
        for (int i = 0; i < 9999999; i++) {
            actualStep = myRandomWalk.generateStep();
            if (actualStep == 0){
                aux-=1;
            }else {
                aux +=1;
            }
            toReturn.add(aux);
        }
        return toReturn;
    }

    public int getNextX(){
        return xData.remove(0);
    }

    public int getNextY(){
        return yData.remove(0);
    }
}
