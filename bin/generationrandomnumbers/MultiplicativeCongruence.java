package generationrandomnumbers;

import java.util.ArrayList;
import java.util.List;

public class MultiplicativeCongruence {

    // numbersList.get_list_xi()[i] / (mym - 1)
    public List<Double> generateNumbers(int firstX,int t, int g, int quantityNumbers ){
        List<Double> listRi = new ArrayList<>();
        double mya = 8*t+3;
        double mym = Math.pow(2,g);
        double myx = (mya * firstX) % mym;
        for (int i = 0; i < quantityNumbers; i++) {
            listRi.add(myx/(mym-1));
            myx = (mya * myx) % mym;
        }
        return listRi;
    }

}
