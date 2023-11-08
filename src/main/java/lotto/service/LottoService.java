package lotto.service;

import lotto.domain.*;

import java.util.List;

public class LottoService {
    public User buyLottoTicket(long buyAmount) {
        Money money = new Money(buyAmount);
        LottoTicket lottoTicket = new LottoTicket(money.getNumberOfPurchase());
        return new User(money, lottoTicket);
    }

    public Lotto getWinningNumbers(List<Integer> winningNumbers) {
        return new Lotto(winningNumbers);
    }

    public WinningLottoNumbers getBonusNumber(Lotto winningNumbers, int bonusNumber) {
        return new WinningLottoNumbers(winningNumbers, bonusNumber);
    }

    public LottoResult calculateLottoResult(User user, WinningLottoNumbers winningLottoNumbers) {
        List<Lotto> userLottoTicket = user.getLottoTicket();
        return new LottoResult(userLottoTicket, winningLottoNumbers);
    }

    public double calculateReturnRate(User user, LottoResult lottoResult) {
        long totalPrize = lottoResult.calculateTotalPrize();
        Money userBuyAmount = user.getBuyAmount();
        return userBuyAmount.getReturnRate(totalPrize);
    }
}