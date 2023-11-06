package lotto.domain;

public class Money {

    public static final Money ZERO = new Money(0);

    private static final Integer MINIMUM_AMOUNT = 1000;
    private final Integer money;
    private Money(Integer money) {
        this.money = money;
    }

    public Integer calcBillCount() {
        return money / MINIMUM_AMOUNT;
    }

    public Double calcProfitRate(Money money) {
        return (this.money / (double) money.money) * 100;
    }

    public Money sum(Money operand) {
        return new Money(money + operand.money);
    }

    public static Money of(Integer money) {
        validateRemainMoney(money);
        validateMinimumAmount(money);
        return new Money(money);
    }

    public Integer getMoney() {
        return money;
    }

    private static void validateMinimumAmount(Integer money) {
        if (isMin(money)) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요.");
        }
    }

    private static boolean isMin(Integer money) {
        return money < MINIMUM_AMOUNT;
    }

    private static void validateRemainMoney(Integer money) {
        if (hasRemain(money)) {
            throw new IllegalArgumentException("천원 단위로 입력해주세요.");
        }
    }

    private static boolean hasRemain(Integer money) {
        return money % MINIMUM_AMOUNT != 0;
    }
}
