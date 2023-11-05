package lotto.service;

import lotto.domain.*;

import java.util.List;

public class LottoService {
    public User buyLottoTicket(int buyAmount) {
        Money money = new Money(buyAmount);
        LottoTicket lottoTicket = new LottoTicket(money.getNumberOfPurchase());
        return new User(money, lottoTicket);
    }

    public LottoResult calculateLottoResult(User user, List<Integer> winningNumbers, int bonusNumber) {
        WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(winningNumbers, bonusNumber);
        List<Lotto> userLottoTicket = user.getLottoTicket();
        return new LottoResult(userLottoTicket, winningLottoNumbers);
    }

    public double calculateReturnRate(User user, LottoResult lottoResult) {
        double totalPrize = lottoResult.calculateTotalPrize();
        Money userBuyAmount = user.getBuyAmount();
        return userBuyAmount.getReturnRate(totalPrize);
    }
}