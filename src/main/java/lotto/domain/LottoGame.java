package lotto.domain;

import java.util.List;
import lotto.domain.entity.Purchase;
import lotto.domain.entity.WinningTicket;

public class LottoGame extends IndexModel {

    private final Purchase purchase;
    private final WinningTicket winningTicket;
    private final WinningResult winningResult;

    private LottoGame(Purchase purchase,
                      WinningTicket winningTicket,
                      WinningResult winningResult) {
        this.purchase = purchase;
        this.winningTicket = winningTicket;
        this.winningResult = winningResult;
    }

    public static LottoGame create(Purchase purchase,
                                   WinningTicket winningTicket,
                                   WinningResult winningResult) {
        return new LottoGame(purchase, winningTicket, winningResult);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<List<Integer>> getPurchaseLottos() {
        return purchase.getLottos();
    }

    public List<Integer> getRankings() {
        return winningResult.getRankings();
    }

    public double getRateOfReturn() {
        return winningResult.getRateOfReturn();
    }
}
