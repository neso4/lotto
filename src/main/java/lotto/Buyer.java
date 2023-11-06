package lotto;

import java.util.ArrayList;
import java.util.List;

public class Buyer {

    private static int num;
    private List<List<Integer>> lottoCollection = new ArrayList<>();

    public Buyer(int num) {
        this.num = num;
    }

    public void setLottoCollection(List<Integer> lotto){
        lottoCollection.add(lotto);
    }

    public List<Integer> getLottoCollection(int num){
        return lottoCollection.get(num);
    }
}
