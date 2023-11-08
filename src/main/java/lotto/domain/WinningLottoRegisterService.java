package lotto.domain;

import java.util.List;

public class WinningLottoRegisterService {
    private WinningLotto winningLotto;

    public WinningLottoRegisterService() {
        this.winningLotto = new WinningLotto();
    }

    public WinningLotto registerNumbers(List<Integer> numbers) {
        winningLotto.saveNumbers(numbers);
        return winningLotto;
    }

    public WinningLotto registerBonusNumber(int bonusNumber) {
        winningLotto.saveBonusNumber(bonusNumber);
        return winningLotto;
    }
}
