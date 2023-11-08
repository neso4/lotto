package lotto.model.service;

import java.util.List;
import lotto.model.domain.Lotto;
import lotto.model.dto.BonusNumber;
import lotto.model.dto.LottoResult;
import lotto.model.dto.PurchaseMoney;
import lotto.model.dto.WinningNumbers;

public class LottoService {

    private final LottoGenerator lottoGenerator;
    private final LottoChecker lottoChecker;

    public LottoService(LottoGenerator lottoGenerator, LottoChecker lottoChecker) {
        this.lottoGenerator = lottoGenerator;
        this.lottoChecker = lottoChecker;
    }

    public List<Lotto> purchaseLotto(PurchaseMoney purchaseMoney) {
        return lottoGenerator.purchaseLotto(purchaseMoney);
    }

    public LottoResult findWinningLottos(List<Lotto> lottos, WinningNumbers winningNumber, BonusNumber bonusNumber) {
        return lottoChecker.findWinningLottos(lottos, winningNumber, bonusNumber);
    }

}
