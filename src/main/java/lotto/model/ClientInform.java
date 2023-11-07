package lotto.model;

import lotto.constant.LottoNumConstant;
import lotto.constant.LottoRateConstant;

public class ClientInform {
    // 사용자의 구매 금액과 로또 수익금 저장객체
    private PurchaseAmount purchaseAmount;
    private LottoWinningReward lottoWinningReward;

    private ClientInform(int purchaseAmount) throws IllegalArgumentException {
        this.purchaseAmount = PurchaseAmount.getInstance(purchaseAmount);
        this.lottoWinningReward = LottoWinningReward.getInstance(LottoNumConstant.ZERO.getValue());
    }

    public static ClientInform getInstance(int purchaseAmount) throws IllegalArgumentException {
        return new ClientInform(purchaseAmount);
    }

    public int getLottoNum() {
        return purchaseAmount.getLottoNum();
    }

    public void addWinningReward(int winningReward) {
        lottoWinningReward.addWinningReward(winningReward);
    }


    public int getWinningReward() {
        return lottoWinningReward.getLottoWinningAmount();
    }
    public int getPurchase() {
        return purchaseAmount.getPurchaseAmount();
    }

    public double getLottoRate() {
        return ((double) getWinningReward() / getPurchase()) * 100.0;
    }
}
