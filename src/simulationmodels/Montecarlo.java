package simulationmodels;

import generationrandomnumbers.MultiplicativeCongruence;

import java.util.List;

public class Montecarlo {

    public int index;
    private List<Double> randomNumberList;

    public Montecarlo(){
        randomNumberList = new MultiplicativeCongruence().generateNumbers(5,7,10,128);
    }


    public double calculateProbabilityWolfOne(int level){
        return  0.9 - (0.15*(level-1)) ;
    }

    public Double calculateProbabilityWolfTwo(int level) {
        return level <=2 ? 0.1 +  (0.15 * (level-1)) : 0.1 * level;
    }

    public Double calculateProbabilityWolfThree(int level) {
        return level  < 2 ? 0 : (0.1 * (level-2)) ;
    }

    public int calculateMontecarForLevel(int level){
        index+=1;
        int result  = 0;
        if(randomNumberList.get(index)< calculateProbabilityWolfOne(level)){
            result = 1;
        }else if(randomNumberList.get(index)< calculateProbabilityWolfTwo(level) + calculateProbabilityWolfOne(level)){
            result = 2;
        }else if(randomNumberList.get(index)<  calculateProbabilityWolfTwo(level) + calculateProbabilityWolfOne(level) + calculateProbabilityWolfThree(level)){
            result = 3;
        }
        return result;
    }

}
