package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class LottoProvider {

    public List<Lotto> lottoGenerate(int each) {
        return LottoNumberCreator.myLottoNumbersOf(each);
    }

    public WinningLotto winLottoGenerate(List<Integer> numbers, int bonusNumber) {
        return WinningLotto.of(numbers, bonusNumber);
    }

}
