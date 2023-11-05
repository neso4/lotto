package lotto.domain;

import java.util.List;

public class LottoBuyer {

    private final int purchaseAmount;
    private final List<Lotto> lottoTickets;

    public LottoBuyer(int purchaseAmount, List<Lotto> lottoTickets) {
        this.purchaseAmount = purchaseAmount;
        this.lottoTickets = lottoTickets;
    }

    public List<Lotto> getLottoTickets() {
        return lottoTickets;
    }

    public int getPurchaseQuantity () {
        return lottoTickets.size();
    }
}
