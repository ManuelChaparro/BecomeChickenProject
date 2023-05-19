package test.simulationmodel;

import simulationmodels.RandomWalk;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestRandomWalk {

    public static void main(String[] args) {
        RandomWalk myRandomWalk = new RandomWalk();
        List<Integer> list =  myRandomWalk.generateStep(0,1000,5000,1,250);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
