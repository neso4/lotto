package Model;

public class PurchaseAmount {
    private int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException(
                    "로또 구입 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    public int get() {
        return purchaseAmount;
    }
}
