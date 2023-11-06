package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class LottoGame {
    public final static int LOTTO_PRICE = 1000;

    private LottoGame() {
    }

    public static void play() {
        int purchaseMoney = Input.askHowManyLottoPurchase();

        List<Lotto> lottos = buyLotto(purchaseMoney/LOTTO_PRICE);
        User user = new User(lottos);
        Output.printLottos(lottos);

        Lotto winningLotteryNumbers = new Lotto(Input.askTheWinningLotteryNumbers());
        int bonusNumber = Input.askBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningLotteryNumbers, bonusNumber);


    }

    private static List<Lotto> buyLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto);
        }

        return lottos;
    }
}
