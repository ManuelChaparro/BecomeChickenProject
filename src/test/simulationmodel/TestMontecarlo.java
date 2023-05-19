package test.simulationmodel;

import simulationmodels.Montecarlo;

import java.sql.Array;
import java.util.ArrayList;


public class TestMontecarlo {
    Montecarlo myMontecarlo = new Montecarlo();

    public TestMontecarlo(){
        TestMontecarlo tm = new TestMontecarlo();
        tm.testAllLevel();
        tm.testWolfProbability();
    }


    private void testWolfProbability(){
        ArrayList<double[]> data = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            double[] vector = new double[3];
            vector[0] = myMontecarlo.calculateProbabilityWolfOne(i);
            vector[1] = myMontecarlo.calculateProbabilityWolfTwo(i);
            vector[2] = myMontecarlo.calculateProbabilityWolfThree(i);
        }
    }

    private void testLevel(int level){
        for (int i = 0; i < 20; i++) {
            System.out.println(myMontecarlo.calculateMontecarForLevel(level));
        }
    }

    private void testAllLevel(){
        System.out.println("Nivel 1");
        testLevel(1);
        System.out.println("Nivel 2");
        testLevel(2);
        System.out.println("Nivel 3");
        testLevel(3);
        System.out.println("Nivel 4");
        testLevel(4);
    }
}
