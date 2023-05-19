package simulationmodels;

import generationrandomnumbers.MultiplicativeCongruence;

import java.util.ArrayList;
import java.util.List;

public class    RandomWalk {

    private List<Double> randomNumberList;


    public RandomWalk(){
        this.randomNumberList = new MultiplicativeCongruence().generateNumbers(2,4,13,4000);
    }

    public List<Double> generateNumberList(int index,int quantityNumbers){
        int firstX = (int) Math.round(randomNumberList.get(index == 0 ? index*1:0) * (3 - 1) + 1);
        int myT = (int) Math.round(randomNumberList.get(index * 2) * (5 - 3) + 3);
        int myG = (int) Math.round(randomNumberList.get(index * 3)* (13 - 10) + 10);
        return new MultiplicativeCongruence().generateNumbers(firstX,myT,myG,quantityNumbers);
    }

    public List<Integer> generateStep(int maxLimit,int minLimit, int quantityNumbers,int iceCreamNumber){
        List<Double> myRandomNumberList = generateNumberList(iceCreamNumber,quantityNumbers);
        List<Integer> result = new ArrayList<>();
        int movement = 0;
        for (int i = 0; i < quantityNumbers; i++) {
            if(myRandomNumberList.get(i)<0.5){
                movement+=1;
            }else{
                movement-=1;
            }
            result.add(movement);
        }
        return result;
    }

}
