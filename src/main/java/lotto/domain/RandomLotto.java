package lotto.domain;
import java.util.*;
public class RandomLotto {
    private static List<List<Integer>> lottoLists;

    public RandomLotto(int numbersOfLotto){
        lottoLists = new ArrayList<>();
    }

    public void addLottoLists(List<Integer> lottoList){
        lottoLists.add(lottoList);
    }

    public List<List<Integer>> getLottoLists(){
        return lottoLists;
    }
}
