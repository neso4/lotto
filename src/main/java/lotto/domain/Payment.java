package lotto.domain;

public class Payment {

    private final int payment;

    public Payment(int payment) {
        validate(payment);
        this.payment = payment;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 돈은 음수가 될 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public double calculateProfitPercentage(long totalPrize) {
        return Math.round(((double) totalPrize / payment) * 100 * 100) / 100.0;
    }

    public int countAffortable(int targetPrice) {
        return payment / targetPrice;
    }
}
