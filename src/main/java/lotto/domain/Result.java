package lotto.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private Map<Rank, Integer> result;

    public Result(HashMap<Rank, Integer> result){
        for(Rank rank : Rank.values()){
            result.put(rank,0);
        }
        this.result = result;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public void compare(List<Integer> lottoNumbers, List<Integer> winningNumber, Integer bonusNumber) {
        Integer count = 0;
        boolean checkBonusNumber = lottoNumbers.contains(bonusNumber);
        for(Integer lottoNumber : lottoNumbers){
            if(winningNumber.contains(lottoNumber) || lottoNumber.equals(bonusNumber)){
                count++;
            }
        }
        Rank keyRank = Rank.valueOf(count, checkBonusNumber);
        this.result.put(keyRank,this.result.get(keyRank)+1);
    }
}
