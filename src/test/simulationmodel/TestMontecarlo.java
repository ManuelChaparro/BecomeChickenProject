package test.simulationmodel;

import simulationmodels.Montecarlo;


public class TestMontecarlo {
    Montecarlo myMontecarlo = new Montecarlo();
    private void testWolfProbability(){
        for (int i = 1; i <= 5; i++) {
            System.out.println("Nivel " + i );
            System.out.println(myMontecarlo.calculateProbabilityWolfOne(i));
            System.out.println(myMontecarlo.calculateProbabilityWolfTwo(i));
            System.out.println(myMontecarlo.calculateProbabilityWolfThree(i));
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

    public static void main(String[] args) {
        TestMontecarlo tm = new TestMontecarlo();

        tm.testWolfProbability();
    }
}