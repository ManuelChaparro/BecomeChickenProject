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
        int firstX = (int) Math.round(randomNumberList.get(index == 0 ? index*1 :0) * (3 - 1) + 1);
        int myT = (int) Math.round(randomNumberList.get(index * 2) * (5 - 3) + 3);
        int myG = (int) Math.round(randomNumberList.get(index * 3)* (13 - 10) + 10);
        return new MultiplicativeCongruence().generateNumbers(firstX,myT,myG,quantityNumbers);
    }

    public List<Integer> generateStep(int minLimit,int maxLimit, int quantityNumbers,int iceCreamNumber,int initialPosition){
        List<Double> myRandomNumberList = generateNumberList(iceCreamNumber,quantityNumbers);
        List<Integer> result = new ArrayList<>();
        int position = initialPosition;
        for (int i = 0; i < quantityNumbers; i++) {
            if(position > minLimit && position < maxLimit) {
                if (myRandomNumberList.get(i) < 0.5) {
                    position += 25;
                } else {
                    position -= 25;
                }
            }else if(position >= maxLimit){
                position -= 25;
            }else{
                position += 25;
            }
            result.add(position);
        }
        return result;
    }

}