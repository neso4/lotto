package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class Computer {

    private Lotto lotto;
    private Bonus bonus;

    public void drawRandomNumber(User user, Lottos lottos) {
        int money = user.getMoney();
        for (int drawLotto = 0; drawLotto < money / 1000; drawLotto++) {
            lotto = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            bonus = new Bonus(lotto);
            lottos.collectLottoNumbers(user, lotto, bonus);
        }
    }
}
