package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.controller.NumberPickingStrategy;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lotto createLotto(NumberPickingStrategy numberPickingStrategy) {
        Lotto lotto = new Lotto(makeLottoNumbers(numberPickingStrategy));
        lottos.add(lotto);
        return lotto;
    }

    private List<Integer> makeLottoNumbers(NumberPickingStrategy numberPickingStrategy) {
        List<Integer> numbers = numberPickingStrategy.pickLottoNumbers();
        Collections.sort(numbers);
        return numbers;
    }

    public Ranks calRanksWithWinningNumbers(List<Integer> numbers, int bonusNumber) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos) {
            ranks.add(Rank.calcRank(
                    lotto.calcCorrectNumbers(numbers),
                    lotto.containsBonusNumber(bonusNumber))
            );
        }
        return new Ranks(ranks);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
