package lotto.domain;

import java.util.List;
import java.util.Map;
import lotto.exception.ExceptionMessage;

public class LottoOrder {
    private final static int BASIC_LOTTO_PRICE = 1000;
    private final static int PERCENT = 100;
    private final Long purchasePrice;
    private final PurchaseLotto purchaseLotto;

    public LottoOrder(Long purchasePrice, PurchaseLotto purchaseLotto) {
        validatePrice(purchasePrice);

        this.purchasePrice = purchasePrice;
        this.purchaseLotto = purchaseLotto;
    }

    public static LottoOrder orderAutoLotto(long purchasePrice) {
        long lottoCount = Math.floorDiv(purchasePrice, BASIC_LOTTO_PRICE);
        PurchaseLotto purchaseLotto = PurchaseLotto.purchaseAutoLotto(lottoCount);

        return new LottoOrder(purchasePrice, purchaseLotto);
    }

    public double calculateGainRatio(WinningLotto winningLotto) {
        long totalWinningPrice = this.purchaseLotto.calculateTotalWinningPrice(winningLotto);

        return ((double) totalWinningPrice / this.purchasePrice) * PERCENT;
    }

    public List<Long> calculateCountByAllRank(WinningLotto winningLotto) {
        Map<Rank, Long> countByWinningRank = this.purchaseLotto.calculateCountByWinningRank(winningLotto);
        List<Rank> allRank = Rank.getAllRank();

        return allRank.stream()
                .map(r -> countByWinningRank.getOrDefault(r, 0L))
                .toList();
    }

    public List<List<Integer>> getLottoNumbers() {
        return this.purchaseLotto.getAllLottoNumber();
    }

    public int getLottoCount() {
        return this.purchaseLotto.getAmount();
    }

    private void validatePrice(Long purchasePrice) {
        if (purchasePrice == 0 || Math.floorMod(purchasePrice, BASIC_LOTTO_PRICE) != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_PURCHASE_PRICE_UNIT.getDesc());
        }
    }

}
