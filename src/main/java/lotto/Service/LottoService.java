package lotto.Service;

import static lotto.Util.Constants.LOTTO_PRICE_UNIT;
import static lotto.Util.Constants.PERCENT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.Domain.BonusNumber;
import lotto.Domain.Lotto;
import lotto.Domain.Rank;
import lotto.Domain.User;
import lotto.Domain.WinningLotto;
import lotto.Domain.WinningResult;
import lotto.Util.LottoNumGenerator;

public class LottoService {
    private User user;
    private WinningLotto winningLotto;
    private WinningResult winningResult;

    public List<Lotto> purchaseLotto(int money) {
        int count = countNumberOfPurchase(money);
        List<Lotto> lottos = publishLotto(count);
        user = new User(money, lottos);
        return lottos;
    }

    public void createWinningLotto(List<Integer> winningNumbers, int bonusNum) {
        Lotto winningLotto = new Lotto(winningNumbers);
        BonusNumber bonusNumber = new BonusNumber(bonusNum);
        this.winningLotto = new WinningLotto(winningLotto, bonusNumber);
    }

    public WinningResult createResult() {
        Map<Rank, Integer> result = new HashMap<>();
        for (Lotto lotto : user.getLottos()) {
            Rank rank = winningLotto.getRankOf(lotto);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        }
        winningResult = new WinningResult(result);
        return winningResult;
    }

    public double getRevenue() {
        double investMoney = user.getMoney();
        double income = winningResult.getIncome();
        return (income / investMoney) * PERCENT;
    }

    private int countNumberOfPurchase(int money) {
        return money / LOTTO_PRICE_UNIT;
    }

    private List<Lotto> publishLotto(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoNumGenerator.generate()));
        }
        return lottos;
    }
}
