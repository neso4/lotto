package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lotto.constant.NumberConstant;

public class LottoMachine {
    private List<Lotto> lottos;

    public LottoMachine(int money){
        int count = getLottoCount(money);
        this.lottos = createLottos(count);
    }

    public List<Lotto> getLottos(){
        return lottos;
    }

    //로또 생성
    private List<Lotto> createLottos(int count){
        List<Lotto> newLottos = new ArrayList<>();
        for(int i = NumberConstant.DEFAULT_VALUE; i < count; i++){
            List<Integer> numbers = sortNumbers(makeRandomNumbers());
            Lotto lotto = new Lotto(numbers);
            newLottos.add(lotto);
        }
        return newLottos;
    }

    private int getLottoCount(int money){
        return money / NumberConstant.LOTTO_PRICE;
    }

    private List<Integer> makeRandomNumbers(){
        return Randoms.pickUniqueNumbersInRange(NumberConstant.MIN_NUMBER, NumberConstant.MAX_NUMBER, NumberConstant.LOTTO_RANGE);
    }

    private List<Integer> sortNumbers(List<Integer> numbers){
        Collections.sort(numbers);
        return numbers;
    }
}
