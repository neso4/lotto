package lotto.domain;

import java.util.List;

public class CalculateLotto {
        private final Lotto lotto;
        private final int bonusNumber;
        public CalculateLotto(Lotto lotto, int bonusNumber) {
                this.lotto = lotto;
                this.bonusNumber = bonusNumber;
        }
        public WinLotto match(Lotto randomNumbers){
                int countOfMatch = randomNumbers.countMatch(lotto);
                boolean matchBonus = randomNumbers.isContainNumber(bonusNumber);
                System.out.println(matchBonus);
                return WinLotto.findWin(countOfMatch,matchBonus);
        }
        public Lotto getLotto() {
                return lotto;
        }
}
