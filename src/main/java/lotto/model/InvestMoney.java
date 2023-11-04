package lotto.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class InvestMoney {
    private static final String NEGATIVE_MONEY_EXCEPTION = "사용자가 투입한 금액에서 음수는 입력할 수 없습니다.";
    private static final String ZERO_MONEY_EXCEPTION_FORMAT = "사용자가 투입한 금액에서 %d원은 입력할 수 없습니다.";
    private static final String MAX_MONEY_EXCEPTION_FORMAT = "사용자가 투입한 금액에서 %,d원 이하로 입력해주세요.";
    private static final int ZERO = 0;
    private static final int MAX_INVESTMENT = 100_000_000;
    private static final int PERCENT_MULTIPLIER = 100;

    private final int money;

    private InvestMoney(int money, LottoPrice lottoPrice) {
        validate(money);
        validateDivisibilityByLottoPrice(money, lottoPrice);
        this.money = money;
    }

    private void validate(int money) {
        validatePositive(money);
        validateNotZero(money);
        validateBelowMaximumLimit(money);
    }

    private void validatePositive(int money) {
        if (money < ZERO) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_EXCEPTION);
        }
    }

    private void validateNotZero(int money) {
        if (money == ZERO) {
            String exceptionMessage = String.format(ZERO_MONEY_EXCEPTION_FORMAT, ZERO);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void validateBelowMaximumLimit(int money) {
        if (money > MAX_INVESTMENT) {
            String exceptionMessage = String.format(MAX_MONEY_EXCEPTION_FORMAT, MAX_INVESTMENT);
            throw new IllegalArgumentException(exceptionMessage);
        }
    }

    private void validateDivisibilityByLottoPrice(int money, LottoPrice lottoPrice) {
        lottoPrice.validateDivisibility(money);
    }

    public static InvestMoney of(int money, LottoPrice lottoPrice) {
        return new InvestMoney(money, lottoPrice);
    }

    public double calculateProfitPercentage(TotalWinningMoney winningMoney) {
        BigDecimal divided = winningMoney.divide(money);
        BigDecimal value = BigDecimal.valueOf(PERCENT_MULTIPLIER);
        return divided.multiply(value)
                .doubleValue();
    }

    public int calculateLottoPurchaseCount(int lottoPrice) {
        return money / lottoPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvestMoney investMoney = (InvestMoney) o;
        return money == investMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
