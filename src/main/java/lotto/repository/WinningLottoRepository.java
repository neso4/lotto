package lotto.repository;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;

public class WinningLottoRepository {
    private Lotto lotto;
    private BonusNumber bonusNumber;

    public WinningLottoRepository(Lotto lotto, BonusNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottoRepository of(Lotto lotto , BonusNumber bonusNumber){
        return new WinningLottoRepository(lotto,bonusNumber);
    }
}
