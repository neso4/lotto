package lotto;

import java.util.Comparator;
import java.util.List;

import static lotto.UI.*;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoGame {
    private final List<Lotto> lottos;
    private final List<Integer> winningNumber;
    private int bonusNumber;

    public LottoGame() {
        int money = getMoney();
        this.lottos = publishLottos(money);
        this.winningNumber = getWinningNumber();
        this.bonusNumber = getBonusNumber();
        checkLottos();
    }

    private List<Lotto> publishLottos(int money){
        int amount = money / 1000;
        for (int i = 0; i < amount; i++){
            Lotto lotto = new Lotto(pickUniqueNumbers());
            lottos.add(lotto);
        }
        return null;
    }

    private List<Integer> pickUniqueNumbers(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        numbers.sort(Comparator.naturalOrder());
        return numbers;
    }

    private void checkLottos(){

    }

}
