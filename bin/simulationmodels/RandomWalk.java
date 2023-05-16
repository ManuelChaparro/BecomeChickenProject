package simulationmodels;

import generationrandomnumbers.MultiplicativeCongruence;
import java.util.List;

public class    RandomWalk {

    private int index;
    private int quantityNumbers;
    private List<Double> randomNumberList;

    public RandomWalk(){
        this.quantityNumbers = quantityNumbers;
        this.randomNumberList = new MultiplicativeCongruence().generateNumbers(2,4,12,128);
    }

    public int generateStep(){
        index+=1;
        int result  = 0;
        if (index < 128){
            if(randomNumberList.get(index)<0.75){
                result = 1;
            }
        }else{
            result = 1;
        }
        return result;
    }

}
