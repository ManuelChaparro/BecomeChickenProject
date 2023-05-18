package test.generationnumbers;

import generationrandomnumbers.MultiplicativeCongruence;

import java.util.List;

public class TestMultiplicative {

    public static void main(String[] args) {
        MultiplicativeCongruence myMontecarlo = new MultiplicativeCongruence();
        // List<Double> testList = myMontecarlo.generateNumbers(2,4,12,128);
        List<Double> testList = myMontecarlo.generateNumbers(5,7,10,128);
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }
    }
}
