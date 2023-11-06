package lotto.model;

public class LottoSeller {
    private final LottoSellingPolicy lottoSellingPolicy = new LottoSellingPolicy();
    private final LottosGenerator lottosGenerator = new LottosGenerator();

    public Lottos sell(int purchasingMoney) {
        int countLottos = lottoSellingPolicy.calcuateLottoCount(purchasingMoney);
        return lottosGenerator.generate(countLottos);
    }
}
