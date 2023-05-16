package test.simulationmodel;

import simulationmodels.RandomWalk;

public class TestRandomWalk {

    public static void main(String[] args) {
        RandomWalk myRandomWalk = new RandomWalk();
        int aux = 0;
        int actualStep = 0;
        for (int i = 0; i < 128; i++) {
            actualStep = myRandomWalk.generateStep();
            System.out.println(actualStep);
            if (actualStep == 0){
                aux-=1;
            }else {
                aux +=1;
            }
            System.out.println("El total es : " + aux);
        }
    }
}
