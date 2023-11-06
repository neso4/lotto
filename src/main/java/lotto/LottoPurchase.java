package lotto;

public class LottoPurchase {

    private long purchaseAmount;

    public LottoPurchase(long purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePurchaseAmount(long purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("로또 구입 금액은 " +
                    LottoConstant.LOTTO_TICKET_PRICE +
                    "원 단위여야 합니다.");
        }
    }
}
